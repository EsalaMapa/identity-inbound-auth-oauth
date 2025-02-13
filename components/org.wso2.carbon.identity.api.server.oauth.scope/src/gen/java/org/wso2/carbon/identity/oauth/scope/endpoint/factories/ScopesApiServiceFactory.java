package org.wso2.carbon.identity.oauth.scope.endpoint.factories;

import org.wso2.carbon.identity.oauth.scope.endpoint.ScopesApiService;
import org.wso2.carbon.identity.oauth.scope.endpoint.impl.ScopesApiServiceImpl;

public class ScopesApiServiceFactory {

    private static final ScopesApiService SERVICE = new ScopesApiServiceImpl();

    public static ScopesApiService getScopesApi() {
        return SERVICE;
    }
}
