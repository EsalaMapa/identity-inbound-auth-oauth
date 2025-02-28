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

package org.wso2.carbon.identity.oauth2.fga.models;

import java.util.HashMap;

/**
 * The {@code AuthzBulkCheckResponse} class is a generic model class for the bulk authorization check response.
 */
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
