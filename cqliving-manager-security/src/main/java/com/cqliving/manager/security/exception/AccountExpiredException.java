package com.cqliving.manager.security.exception;

/**
 * 账户过期
 */
public class AccountExpiredException extends AuthenticationException {

	/**
	 * @param string
	 */
	public AccountExpiredException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -825331897983004489L;


}
