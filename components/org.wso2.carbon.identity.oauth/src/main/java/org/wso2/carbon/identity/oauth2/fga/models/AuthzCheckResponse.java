package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

public class AuthzCheckResponse {

    private final boolean isAuthorized;
    private JSONObject additionalContext;

    public AuthzCheckResponse(boolean isAuthorized) {

        this.isAuthorized = isAuthorized;
    }

    public void setAdditionalContext(JSONObject additionalContext) {

        this.additionalContext = additionalContext;
    }

    public boolean isAuthorized() {

        return isAuthorized;
    }

    public JSONObject getAdditionalContext() {

        return additionalContext;
    }
}
