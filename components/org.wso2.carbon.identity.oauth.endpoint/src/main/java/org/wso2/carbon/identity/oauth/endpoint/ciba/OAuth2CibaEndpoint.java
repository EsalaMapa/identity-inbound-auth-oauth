/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth.endpoint.ciba;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.wso2.carbon.identity.oauth.ciba.common.CibaConstants;
import org.wso2.carbon.identity.oauth.ciba.exceptions.CibaClientException;
import org.wso2.carbon.identity.oauth.ciba.exceptions.CibaCoreException;
import org.wso2.carbon.identity.oauth.ciba.model.CibaAuthCodeRequest;
import org.wso2.carbon.identity.oauth.ciba.model.CibaAuthCodeResponse;
import org.wso2.carbon.identity.oauth.client.authn.filter.OAuthClientAuthenticatorProxy;
import org.wso2.carbon.identity.oauth.common.OAuth2ErrorCodes;
import org.wso2.carbon.identity.oauth.common.OAuthConstants;
import org.wso2.carbon.identity.oauth.common.exception.InvalidOAuthClientException;
import org.wso2.carbon.identity.oauth.config.OAuthServerConfiguration;
import org.wso2.carbon.identity.oauth.endpoint.OAuthRequestWrapper;
import org.wso2.carbon.identity.oauth.endpoint.exception.CibaAuthFailureException;
import org.wso2.carbon.identity.oauth.endpoint.exception.InvalidRequestException;
import org.wso2.carbon.identity.oauth.endpoint.util.factory.CibaAuthServiceFactory;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;
import org.wso2.carbon.identity.oauth2.RequestObjectException;
import org.wso2.carbon.identity.oauth2.bean.OAuthClientAuthnContext;
import org.wso2.carbon.identity.oauth2.model.OAuth2Parameters;
import org.wso2.carbon.identity.oauth2.util.OAuth2Util;
import org.wso2.carbon.identity.openidconnect.OIDCRequestObjectUtil;
import org.wso2.carbon.identity.openidconnect.RequestObjectBuilder;
import org.wso2.carbon.identity.openidconnect.RequestObjectValidator;
import org.wso2.carbon.identity.openidconnect.model.RequestObject;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Rest implementation for OAuth2 CIBA endpoint.
 */
@Path("/ciba")
@InInterceptors(classes = OAuthClientAuthenticatorProxy.class)
public class OAuth2CibaEndpoint {

    private static final Log log = LogFactory.getLog(OAuth2CibaEndpoint.class);

    private CibaAuthRequestValidator cibaAuthRequestValidator = new CibaAuthRequestValidator();
    private CibaAuthzHandler cibaAuthzHandler = new CibaAuthzHandler();
    private CibaAuthResponseHandler cibaAuthResponseHandler = new CibaAuthResponseHandler();
    private CibaAuthCodeRequest cibaAuthCodeRequest;
    private CibaAuthCodeResponse cibaAuthCodeResponse;
    private static final String REQUEST_PARAM_VALUE_BUILDER = "request_param_value_builder";

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response ciba(@Context HttpServletRequest request, @Context HttpServletResponse response,
                         MultivaluedMap paramMap) {

        OAuthClientAuthnContext oAuthClientAuthnContext =  getClientAuthnContext(request);

        if (!oAuthClientAuthnContext.isAuthenticated()) {
            if (oAuthClientAuthnContext.getErrorCode() != null) {
                return getErrorResponse(new CibaAuthFailureException(oAuthClientAuthnContext.getErrorCode(),
                        oAuthClientAuthnContext.getErrorMessage()));
            }
            return getErrorResponse(new CibaAuthFailureException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT,
                    "Client authentication required"));
        }

        request = new OAuthRequestWrapper(request, (Map<String, List<String>>) paramMap);

        if (log.isDebugEnabled()) {
            log.debug("Authentication request has hit Client Initiated Back-channel Authentication EndPoint.");
        }

        try {
            // Check whether request has the 'request' parameter.
            checkForRequestParam(request);

            // Capturing authentication request.
            String authRequest = request.getParameter(CibaConstants.REQUEST);

            // Validate authentication request.
            validateAuthenticationRequest(authRequest, oAuthClientAuthnContext.getClientId());

            // Prepare RequestDTO with validated parameters.
            cibaAuthCodeRequest = getCibaAuthCodeRequest(authRequest);

            // Obtain Response from service layer of CIBA.
            cibaAuthCodeResponse = getCibaAuthCodeResponse(cibaAuthCodeRequest);

            // Create an internal authorize call to the authorize endpoint.
            generateAuthorizeCall(request, response, cibaAuthCodeResponse);

            // Create and return Ciba Authentication Response.
            return getAuthResponse(response, cibaAuthCodeResponse);

        } catch (CibaAuthFailureException e) {
            // Returning error response.
            return getErrorResponse(e);
        }
    }

    /**
     * Creates CIBA Authentication Error Response.
     *
     * @param cibaAuthFailureException Ciba Authentication Failed Exception.
     * @return response Authentication Error Responses for AuthenticationRequest.
     */
    private Response getErrorResponse(CibaAuthFailureException cibaAuthFailureException) {

        return cibaAuthResponseHandler.createErrorResponse(cibaAuthFailureException);
    }

    /**
     * Creates CIBA AuthenticationResponse.
     *
     * @param response             Authentication response object.
     * @param cibaAuthCodeResponse CIBA Authentication Request Data Transfer Object.
     * @return Response for AuthenticationRequest.
     */
    private Response getAuthResponse(@Context HttpServletResponse response, CibaAuthCodeResponse cibaAuthCodeResponse) {

        return cibaAuthResponseHandler.createAuthResponse(response, cibaAuthCodeResponse);
    }

    /**
     * Accepts auth code request  and responds with auth code response.
     *
     * @param cibaAuthCodeRequest CIBA Authentication Request Data Transfer Object.
     * @return CibaAuthCodeResponse CIBA Authentication Response Data Transfer Object.
     * @throws CibaAuthFailureException Core exception from CIBA module.
     */
    private CibaAuthCodeResponse getCibaAuthCodeResponse(CibaAuthCodeRequest cibaAuthCodeRequest)
            throws CibaAuthFailureException {

        try {
            cibaAuthCodeResponse = CibaAuthServiceFactory.getCibaAuthService()
                    .generateAuthCodeResponse(cibaAuthCodeRequest);
        } catch (CibaCoreException | CibaClientException e) {
            throw new CibaAuthFailureException(OAuth2ErrorCodes.SERVER_ERROR, "Error while generating " +
                    "authentication response.", e);
        }
        return cibaAuthCodeResponse;
    }

    /**
     * Extracts validated parameters from request and prepare a DTO.
     *
     * @param authRequest CIBA Authentication Request as a String.
     * @throws CibaAuthFailureException CIBA Authentication Failed Exception.
     */
    private CibaAuthCodeRequest getCibaAuthCodeRequest(String authRequest) throws CibaAuthFailureException {

        return cibaAuthRequestValidator.prepareAuthCodeRequest(authRequest);
    }

    /**
     * Extracts validated parameters from request and prepare a DTO.
     *
     * @param request CIBA Authentication Request.
     * @throws CibaAuthFailureException CIBA Authentication Failed Exception.
     */
    private void checkForRequestParam(HttpServletRequest request) throws CibaAuthFailureException {

        if (request.getParameter(CibaConstants.REQUEST) == null) {
            // Mandatory 'request' parameter does not exist.

            if (log.isDebugEnabled()) {
                log.debug("CIBA Authentication Request that hits Client Initiated Authentication Endpoint has " +
                        "no 'request' parameter.");
            }
            throw new CibaAuthFailureException(OAuth2ErrorCodes.INVALID_REQUEST,
                    "missing the mandated parameter : (request)");
        }
    }

    /**
     * Trigger authorize request after building the url.
     *
     * @param cibaAuthCodeResponse AuthorizeRequest Data Transfer Object..
     * @throws CibaAuthFailureException CibaAuthentication related exception.
     */
    private void generateAuthorizeCall(@Context HttpServletRequest request,
                                       @Context HttpServletResponse response,
                                       CibaAuthCodeResponse cibaAuthCodeResponse) throws CibaAuthFailureException {

        //  Internal authorize java call to /authorize end point.
        cibaAuthzHandler.initiateAuthzRequest(cibaAuthCodeResponse, request, response);
        if (log.isDebugEnabled()) {
            log.info("Firing a Authorization request in regard to the request made by client with clientID: "
                    + cibaAuthCodeResponse.getClientId() + " .");
        }
    }

    /**
     * Validate whether Request JWT is in proper formatting.
     *
     * @param authRequest CIBA Authentication Request as a String.
     * @throws CibaAuthFailureException CIBA Authentication Failed Exception.
     */
    private void validateAuthenticationRequest(String authRequest, String clientId) throws CibaAuthFailureException {

        // Validation for the proper formatting of signedJWT.
        cibaAuthRequestValidator.validateRequest(authRequest);

        // Validation for the client.
        cibaAuthRequestValidator.validateClient(authRequest, clientId);

        // Validation for the userHint.
        cibaAuthRequestValidator.validateUserHint(authRequest);

        // Validate Authentication request.
        cibaAuthRequestValidator.validateAuthRequestParams(authRequest);

        try {

            RequestObject requestObject;
            RequestObjectBuilder requestObjectBuilder;
            requestObjectBuilder = OAuthServerConfiguration.getInstance().getRequestObjectBuilders().
                    get(REQUEST_PARAM_VALUE_BUILDER);

            OAuth2Parameters parameters = new OAuth2Parameters();
            parameters.setClientId(clientId);
            parameters.setTenantDomain(getSpTenantDomain(clientId));

            if (requestObjectBuilder == null) {
                String error = "Unable to build the OIDC Request Object";
                throw new CibaAuthFailureException(OAuth2ErrorCodes.SERVER_ERROR, error);
            }
            requestObject = requestObjectBuilder.buildRequestObject(authRequest,
                    parameters);
            RequestObjectValidator requestObjectValidator = OAuthServerConfiguration.getInstance()
                    .getCIBARequestObjectValidator();

            OIDCRequestObjectUtil.validateRequestObjectSignature(parameters, requestObject, requestObjectValidator);

            if (!requestObjectValidator.validateRequestObject(requestObject, parameters)) {
                throw new CibaAuthFailureException(OAuth2ErrorCodes.INVALID_REQUEST, "Invalid parameters " +
                        "found in the Request Object.");

            }
        } catch (InvalidRequestException | RequestObjectException e) {
            if (log.isDebugEnabled()) {
                log.debug(OAuth2ErrorCodes.INVALID_REQUEST, e);
            }
            throw new CibaAuthFailureException(OAuth2ErrorCodes.INVALID_REQUEST, e.getMessage());
        }
    }

    private OAuthClientAuthnContext getClientAuthnContext(HttpServletRequest request) {
        OAuthClientAuthnContext oAuthClientAuthnContext;
        Object oauthClientAuthnContextObj = request.getAttribute(OAuthConstants.CLIENT_AUTHN_CONTEXT);
        if (oauthClientAuthnContextObj instanceof OAuthClientAuthnContext) {
            oAuthClientAuthnContext = (OAuthClientAuthnContext) oauthClientAuthnContextObj;
        } else {
            oAuthClientAuthnContext = new OAuthClientAuthnContext();
            oAuthClientAuthnContext.setAuthenticated(false);
            oAuthClientAuthnContext.setErrorMessage("Client Authentication Failed");
            oAuthClientAuthnContext.setErrorCode(OAuthError.TokenResponse.INVALID_REQUEST);
        }
        return oAuthClientAuthnContext;
    }

    private String getSpTenantDomain(String clientId) throws InvalidRequestException {

        try {
            // At this point we have verified that a valid app exists for the client_id. So we directly get the SP
            // tenantDomain.
            return OAuth2Util.getTenantDomainOfOauthApp(clientId);
        } catch (InvalidOAuthClientException | IdentityOAuth2Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(OAuth2ErrorCodes.INVALID_REQUEST, e);
            }
            throw new InvalidRequestException("Error retrieving Service Provider tenantDomain for client_id: "
                    + clientId, OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ErrorCodes.OAuth2SubErrorCodes
                    .UNEXPECTED_SERVER_ERROR);
        }
    }
}
