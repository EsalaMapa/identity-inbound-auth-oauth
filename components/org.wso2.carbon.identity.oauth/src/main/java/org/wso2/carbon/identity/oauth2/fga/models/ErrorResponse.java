package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONArray;

public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private JSONArray errorDetails;

    public ErrorResponse(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public void setErrorDetails(JSONArray errorDetails) {

        this.errorDetails = errorDetails;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public JSONArray getErrorDetails() {

        return errorDetails;
    }
}
