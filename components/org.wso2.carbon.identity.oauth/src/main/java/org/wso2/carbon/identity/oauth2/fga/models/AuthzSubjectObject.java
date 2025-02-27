package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

import java.util.Objects;

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
