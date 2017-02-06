package com.cqliving.manager.security.exception;

/**
 * Created by Administrator on 2015/5/21.
 *
 * 未知账号错误
 */
public class UnknownAccountException extends AuthenticationException {

	private static final long serialVersionUID = -8150140575117200045L;

	public UnknownAccountException(Throwable cause) {
        super(cause);
    }
}
