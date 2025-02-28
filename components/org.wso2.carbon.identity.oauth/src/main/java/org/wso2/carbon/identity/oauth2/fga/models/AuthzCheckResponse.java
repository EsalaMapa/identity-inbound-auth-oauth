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

import org.json.JSONObject;

/**
 * The {@code AuthzCheckResponse} class is a generic model class for the authorization check response.
 */
public class AuthzCheckResponse {

    private final boolean isAuthorized;
    private JSONObject additionalContext;

    public AuthzCheckResponse(boolean isAuthorized) {

        this.isAuthorized = isAuthorized;
    }

    public void setAdditionalContext(JSONObject additionalContext) {

        this.additionalContext = additionalContext;
    }

    public boolean isAuthorized() {

        return isAuthorized;
    }

    public JSONObject getAdditionalContext() {

        return additionalContext;
    }
}
