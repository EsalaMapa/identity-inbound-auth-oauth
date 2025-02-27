package org.wso2.carbon.identity.oauth2.fga.models;

import java.util.ArrayList;

public class ListObjectsResponse {

    protected ArrayList<ListObjectsResult> results;
    protected ArrayList<ErrorResponse> errorResults;

    public ListObjectsResponse(ArrayList<ListObjectsResult> results, ArrayList<ErrorResponse> errorResults) {

        this.results = results;
        this.errorResults = errorResults;
    }

    public ArrayList<ListObjectsResult> getResults() {

        return this.results;
    }

    public ArrayList<ErrorResponse> getErrorResults() {

        return this.errorResults;
    }
}
