package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

import java.util.Objects;

public class AuthzCheckRequest {

    private final AuthzResourceObject resource;
    private final AuthzActionObject action;
    private final AuthzSubjectObject subject;
    private JSONObject additionalContext;

    public AuthzCheckRequest(AuthzSubjectObject subject, AuthzActionObject action, AuthzResourceObject resource) {

        this.resource = resource;
        this.action = action;
        this.subject = subject;
    }

    public AuthzSubjectObject getSubject() {

        return subject;
    }

    public AuthzResourceObject getResource() {

        return resource;
    }

    public AuthzActionObject getActionObject() {

        return action;
    }

    public JSONObject getAdditionalContext() {

        return additionalContext;
    }

    public void setAdditionalContext(JSONObject additionalContext) {

        this.additionalContext = additionalContext;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthzCheckRequest that = (AuthzCheckRequest) obj;
        if (additionalContext == null && that.additionalContext == null) {
            return true;
        }
        if (additionalContext == null || that.additionalContext == null) {
            return false;
        }
        if (JSONObject.NULL.equals(additionalContext) || JSONObject.NULL.equals(that.additionalContext)) {
            boolean thisAdditionalContextNull = JSONObject.NULL.equals(additionalContext);
            boolean thatAdditionalContextNull = JSONObject.NULL.equals(that.additionalContext);
            return Objects.equals(resource, that.resource) &&
                    Objects.equals(action, that.action) &&
                    Objects.equals(subject, that.subject) &&
                    thisAdditionalContextNull == thatAdditionalContextNull;
        }
        return Objects.equals(resource, that.resource) &&
                Objects.equals(action, that.action) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(additionalContext, that.additionalContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource, action, subject, additionalContext);
    }
}
