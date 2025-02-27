package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

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
