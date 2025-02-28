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
 * The {@code ListObjectsRequest} class is a generic model class for the list objects request.
 */
public class ListObjectsRequest {

    protected String resultObjectType;
    protected String relation;
    protected String searchObjectType;
    protected String searchObjectId;
    protected JSONObject additionalContext;

    public ListObjectsRequest(String resultObjectType, String relation, String searchObjectType,
                              String searchObjectId) {

        this.resultObjectType = resultObjectType;
        this.relation = relation;
        this.searchObjectType = searchObjectType;
        this.searchObjectId = searchObjectId;
    }

    public void setAdditionalContext(JSONObject additionalContext) {

        this.additionalContext = additionalContext;
    }

    public String getResultObjectType() {

        return resultObjectType;
    }

    public String getRelation() {

        return relation;
    }

    public String getSearchObjectType() {

        return searchObjectType;
    }

    public String getSearchObjectId() {

        return searchObjectId;
    }

    public JSONObject getAdditionalContext() {

        return additionalContext;
    }
}