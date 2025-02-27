package org.wso2.carbon.identity.oauth2.fga.models;

import java.util.ArrayList;

public class AuthzBulkCheckRequest {

    protected ArrayList<AuthzCheckRequest> items;

    public AuthzBulkCheckRequest(ArrayList<AuthzCheckRequest> items) {

        this.items = items;
    }

    public ArrayList<AuthzCheckRequest> getItems() {

        return items;
    }
}
