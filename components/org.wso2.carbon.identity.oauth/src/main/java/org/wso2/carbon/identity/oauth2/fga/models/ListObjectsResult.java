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
 * The {@code ListObjectsResult} class is a generic model class for the list objects result returned in a List objects
 * request.
 */
public class ListObjectsResult {

    private String resultObjectId;
    private String resultObjectType;
    private JSONObject additionalContext;

    public ListObjectsResult(String resultObjectId) {
        this.resultObjectId = resultObjectId;
    }

    public void setResultObjectType(String resultObjectType) {
        this.resultObjectType = resultObjectType;
    }

    public void setAdditionalContext(JSONObject additionalContext) {
        this.additionalContext = additionalContext;
    }

    public String getResultObjectId() {
        return resultObjectId;
    }

    public String getResultObjectType() {
        return resultObjectType;
    }

    public JSONObject getAdditionalContext() {
        return additionalContext;
    }
}
