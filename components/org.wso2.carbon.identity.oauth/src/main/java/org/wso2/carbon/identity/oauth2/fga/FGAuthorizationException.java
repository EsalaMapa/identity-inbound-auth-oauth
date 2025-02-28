package org.wso2.carbon.identity.oauth2.fga;

import org.wso2.carbon.identity.base.IdentityException;

/**
 * The {@code FGAuthorizationException} class represents the exception that is thrown when an error occurs during
 * Fine-Grained Authorization related flows.
 */
public class FGAuthorizationException extends IdentityException {

    private String errorCode = null;

    public FGAuthorizationException (String message) {
        super(message);
    }

    public FGAuthorizationException (String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FGAuthorizationException (String message, Throwable cause) {
        super(message, cause);
    }

    public FGAuthorizationException (String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
