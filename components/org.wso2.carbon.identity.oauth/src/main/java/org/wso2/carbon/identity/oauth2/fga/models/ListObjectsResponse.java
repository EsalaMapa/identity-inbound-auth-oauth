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

import java.util.ArrayList;

/**
 * The {@code ListObjectsResponse} class is a generic model class for the list objects response.
 */
public class ListObjectsResponse {

    protected ArrayList<ListObjectsResult> results;
    protected ArrayList<ErrorResponse> errorResults;

    public ListObjectsResponse(ArrayList<ListObjectsResult> results, ArrayList<ErrorResponse> errorResults) {

        this.results = results;
        this.errorResults = errorResults;
    }

    public ArrayList<ListObjectsResult> getResults() {

        return this.results;
    }

    public ArrayList<ErrorResponse> getErrorResults() {

        return this.errorResults;
    }
}
