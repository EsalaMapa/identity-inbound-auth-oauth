/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth2.fga.factory;

import org.wso2.carbon.identity.oauth.internal.OAuthComponentServiceHolder;
import org.wso2.carbon.identity.oauth2.fga.services.FGADataManagementInterface;
import org.wso2.carbon.identity.oauth2.fga.services.FGAuthorizationInterface;

/**
 * The {@code FGAEngineHandler} class is a factory class for the FGAuthorizationEngine.
 * It provides the authorization and data management services for Fine Grained Authorization by calling the
 * OSGi service components.
 */
public class FGAEngineHandler {

    private static FGAuthorizationInterface authorizationService;
    private static FGADataManagementInterface dataManagementService;

    /**
     * Returns the authorization service if an authorization engine specific service is active as an OSGi component.
     *
     * @return The authorization service.
     */
    public FGAuthorizationInterface getAuthorizationService() {

        if (authorizationService == null) {
            authorizationService = OAuthComponentServiceHolder.getInstance().getFGAuthorizationService();
        }
        return authorizationService;
    }

    /**
     * Returns the data management service if an authorization engine specific data management service is active as an
     * OSGi component.
     *
     * @return The data management service.
     */
    public FGADataManagementInterface getDataManagementService() {

        if (dataManagementService == null) {
            dataManagementService = OAuthComponentServiceHolder.getInstance().getFGADataManagementService();
        }
        return dataManagementService;
    }
}
