package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

import java.util.Objects;

public class AuthzActionObject {

    private String action;
    private JSONObject properties;

    public AuthzActionObject(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
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
        AuthzActionObject that = (AuthzActionObject) obj;
        if (properties == null && that.properties == null) {
            return true;
        }
        if (properties == null || that.properties == null) {
            return false;
        }
        if (JSONObject.NULL.equals(properties) || JSONObject.NULL.equals(that.properties)) {
            boolean thisPropertiesNull = JSONObject.NULL.equals(properties);
            boolean thatPropertiesNull = JSONObject.NULL.equals(that.properties);
            return action.equals(that.action) && thisPropertiesNull && thatPropertiesNull;
        }
        return action.equals(that.action) &&
                properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, properties);
    }
}
