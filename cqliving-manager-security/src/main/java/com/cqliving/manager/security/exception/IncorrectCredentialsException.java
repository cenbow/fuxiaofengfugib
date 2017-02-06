package com.cqliving.manager.security.exception;

/**
 * Created by Administrator on 2015/5/21.
 *
 * 密码错误
 */
public class IncorrectCredentialsException extends AuthenticationException {
	private static final long serialVersionUID = 9119333629800823805L;

	public IncorrectCredentialsException(Throwable cause) {
        super(cause);
    }
}
