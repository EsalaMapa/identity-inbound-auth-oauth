/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.oauth2.fga.services;

import org.wso2.carbon.identity.oauth2.fga.FGAuthorizationException;
import org.wso2.carbon.identity.oauth2.fga.models.AuthzBulkCheckRequest;
import org.wso2.carbon.identity.oauth2.fga.models.AuthzBulkCheckResponse;
import org.wso2.carbon.identity.oauth2.fga.models.AuthzCheckRequest;
import org.wso2.carbon.identity.oauth2.fga.models.AuthzCheckResponse;
import org.wso2.carbon.identity.oauth2.fga.models.ListObjectsRequest;
import org.wso2.carbon.identity.oauth2.fga.models.ListObjectsResponse;

/**
 * The {@code FGAuthorizationInterface} interface contains the methods for access evaluation.
 * <p>
 *     Implementations of this interface should provide the following functionalities:
 *     <ul>
 *         <li>Check authorization for a given request</li>
 *         <li>Check authorization for a list of requests</li>
 *         <li>List Resources for a given list request</li>
 *         <li>List Subjects for a given list request</li>
 *     </ul>
 *     Implementations can be specific to a particular authorization engine and use the engine's API to perform the
 *     above functionalities. Note that the request models and response models given and returned are generic so you may
 *     need to convert them to the specific models of the authorization engine you are using.
 * </p>
 */
public interface FGAuthorizationInterface {

    /**
     * Check authorization for a given request.
     *
     * @param authzCheckRequest The request to check authorization.
     * @return The response of the authorization check.
     * @throws FGAuthorizationException If an error occurs while checking authorization.
     */
    AuthzCheckResponse checkAuthorization(AuthzCheckRequest authzCheckRequest) throws FGAuthorizationException;

    /**
     * Check authorization for a list of requests.
     *
     * @param authzBulkCheckRequest The list of requests to check authorization.
     * @return The response of the authorization check.
     * @throws FGAuthorizationException If an error occurs while checking authorization.
     */
    AuthzBulkCheckResponse bulkCheckAuthorization
            (AuthzBulkCheckRequest authzBulkCheckRequest) throws FGAuthorizationException;

    /**
     * List Resources for a given list request.
     *
     * @param listObjectsRequest The request to list resources.
     * @return The response of the list resources request.
     * @throws FGAuthorizationException If an error occurs while listing resources.
     */
    ListObjectsResponse listResources(ListObjectsRequest listObjectsRequest) throws FGAuthorizationException;

    /**
     * List Subjects for a given list request.
     *
     * @param listObjectsRequest The request to list subjects.
     * @return The response of the list subjects request.
     * @throws FGAuthorizationException If an error occurs while listing subjects.
     */
    ListObjectsResponse listSubjects(ListObjectsRequest listObjectsRequest) throws FGAuthorizationException;
}
