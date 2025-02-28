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

import java.util.Objects;

/**
 * The {@code AuthzSubjectObject} class represents a subject object in an evaluation request.
 */
public class AuthzSubjectObject {

    private String subjectType;
    private String subjectId;
    private JSONObject properties;

    public AuthzSubjectObject(String subjectType, String subjectId) {
        this.subjectType = subjectType;
        this.subjectId = subjectId;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public JSONObject getProperties() {

        return properties;
    }

    public void setProperties(JSONObject properties) {

        this.properties = properties;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthzSubjectObject that = (AuthzSubjectObject) obj;
        if (properties == null && that.properties == null) {
            return true;
        }
        if (properties == null || that.properties == null) {
            return false;
        }
        if (JSONObject.NULL.equals(properties) || JSONObject.NULL.equals(that.properties)) {
            boolean thisPropertiesNull = JSONObject.NULL.equals(properties);
            boolean thatPropertiesNull = JSONObject.NULL.equals(that.properties);
            return subjectType.equals(that.subjectType) &&
                    subjectId.equals(that.subjectId) && thisPropertiesNull == thatPropertiesNull;
        }
        return subjectType.equals(that.subjectType) &&
                subjectId.equals(that.subjectId) &&
                properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectType, subjectId, properties);
    }
}
