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

package org.wso2.carbon.identity.oauth2.fga.models;

import org.json.JSONObject;

import java.util.Objects;

/**
 * The {@code AuthzCheckRequest} class is a generic model for an authorization check request body.
 * This follows the AuthZen evaluation API model.
 */
public class AuthzCheckRequest {

    private final AuthzResourceObject resource;
    private final AuthzActionObject action;
    private final AuthzSubjectObject subject;
    private JSONObject additionalContext;

    public AuthzCheckRequest(AuthzSubjectObject subject, AuthzActionObject action, AuthzResourceObject resource) {

        this.resource = resource;
        this.action = action;
        this.subject = subject;
    }

    public AuthzSubjectObject getSubject() {

        return subject;
    }

    public AuthzResourceObject getResource() {

        return resource;
    }

    public AuthzActionObject getActionObject() {

        return action;
    }

    public JSONObject getAdditionalContext() {

        return additionalContext;
    }

    public void setAdditionalContext(JSONObject additionalContext) {

        this.additionalContext = additionalContext;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthzCheckRequest that = (AuthzCheckRequest) obj;
        if (additionalContext == null && that.additionalContext == null) {
            return true;
        }
        if (additionalContext == null || that.additionalContext == null) {
            return false;
        }
        if (JSONObject.NULL.equals(additionalContext) || JSONObject.NULL.equals(that.additionalContext)) {
            boolean thisAdditionalContextNull = JSONObject.NULL.equals(additionalContext);
            boolean thatAdditionalContextNull = JSONObject.NULL.equals(that.additionalContext);
            return Objects.equals(resource, that.resource) &&
                    Objects.equals(action, that.action) &&
                    Objects.equals(subject, that.subject) &&
                    thisAdditionalContextNull == thatAdditionalContextNull;
        }
        return Objects.equals(resource, that.resource) &&
                Objects.equals(action, that.action) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(additionalContext, that.additionalContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource, action, subject, additionalContext);
    }
}
