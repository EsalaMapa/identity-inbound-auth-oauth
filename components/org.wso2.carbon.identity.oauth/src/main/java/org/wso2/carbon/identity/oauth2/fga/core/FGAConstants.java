package org.wso2.carbon.identity.oauth2.fga.core;

import org.wso2.carbon.identity.core.util.IdentityUtil;

/**
 * Constants for Fine Grained Authorization module.
 */
public class FGAConstants {

    // Authorization engine url configuration
    public static final String FGA_ENGINE_BASE_URL = IdentityUtil.getProperty("FGAEngineConfig.BasePath");
    // Authorization engine pre-shared key configuration
    public static final String FGA_ENGINE_PRE_SHARED_KEY =
            IdentityUtil.getProperty("FGAEngineConfig.Authentication.PreSharedKey");
}
