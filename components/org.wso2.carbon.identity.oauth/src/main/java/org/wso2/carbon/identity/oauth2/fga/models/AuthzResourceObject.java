package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

import java.util.Objects;

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
