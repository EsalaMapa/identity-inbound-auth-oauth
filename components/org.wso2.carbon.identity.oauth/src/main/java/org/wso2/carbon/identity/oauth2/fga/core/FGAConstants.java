package org.wso2.carbon.identity.oauth2.fga.core;

import org.wso2.carbon.identity.core.util.IdentityUtil;

public class FGAConstants {
    
    public static final String FGA_ENGINE_BASE_URL = IdentityUtil.getProperty("FGAEngineConfig.BasePath");
    public static final String FGA_ENGINE_PRE_SHARED_KEY =
            IdentityUtil.getProperty("FGAEngineConfig.Authentication.PreSharedKey");
}
