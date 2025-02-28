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
 * The {@code AuthzResourceObject} class is a generic model for an authorization resource object used in an evaluation
 * request.
 */
public class AuthzResourceObject {

    private String resourceObjectType;
    private String resourceObjectId;
    private JSONObject properties;

    public AuthzResourceObject(String resourceObjectType, String resourceObjectId) {
        this.resourceObjectType = resourceObjectType;
        this.resourceObjectId = resourceObjectId;
    }

    public void setProperties(JSONObject properties) {
        this.properties = properties;
    }

    public String getResourceObjectType() {
        return resourceObjectType;
    }

    public String getResourceObjectId() {
        return resourceObjectId;
    }

    public JSONObject getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthzResourceObject that = (AuthzResourceObject) obj;
        if (properties == null && that.properties == null) {
            return true;
        }
        if (properties == null || that.properties == null) {
            return false;
        }
        if (JSONObject.NULL.equals(properties) || JSONObject.NULL.equals(that.properties)) {
            boolean thisPropertiesNull = JSONObject.NULL.equals(properties);
            boolean thatPropertiesNull = JSONObject.NULL.equals(that.properties);
            return resourceObjectType.equals(that.resourceObjectType) &&
                    resourceObjectId.equals(that.resourceObjectId) &&
                    thisPropertiesNull == thatPropertiesNull;
        }
        return resourceObjectType.equals(that.resourceObjectType) &&
                resourceObjectId.equals(that.resourceObjectId) &&
                properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceObjectType, resourceObjectId, properties);
    }
}
