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

import org.json.JSONArray;

/**
 * The {@code ErrorResponse} class is a generic model class for an error response from an authorization engine.
 */
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private JSONArray errorDetails;

    public ErrorResponse(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public void setErrorDetails(JSONArray errorDetails) {

        this.errorDetails = errorDetails;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public JSONArray getErrorDetails() {

        return errorDetails;
    }
}
