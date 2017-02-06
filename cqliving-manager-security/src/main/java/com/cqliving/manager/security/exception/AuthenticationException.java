package com.cqliving.manager.security.exception;

/**
 * Created by Administrator on 2015/5/21.
 */
public class AuthenticationException extends org.apache.shiro.authc.AuthenticationException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
