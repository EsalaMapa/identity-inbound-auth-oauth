package org.wso2.carbon.identity.oauth2.fga.models;

import java.util.HashMap;

public class AuthzBulkCheckResponse {

    private HashMap<AuthzCheckRequest, AuthzCheckResponse> results;
    private HashMap<AuthzCheckRequest, ErrorResponse> errorResults;

    public AuthzBulkCheckResponse(HashMap<AuthzCheckRequest, AuthzCheckResponse> results, HashMap<AuthzCheckRequest,
            ErrorResponse> errorResults) {

        this.results = results;
        this.errorResults = errorResults;
    }

    public HashMap<AuthzCheckRequest, AuthzCheckResponse> getResults() {

        return this.results;
    }

    public HashMap<AuthzCheckRequest, ErrorResponse> getErrorResults() {

        return this.errorResults;
    }
}
