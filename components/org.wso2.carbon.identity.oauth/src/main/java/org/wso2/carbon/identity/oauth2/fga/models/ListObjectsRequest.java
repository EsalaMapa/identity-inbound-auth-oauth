package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

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