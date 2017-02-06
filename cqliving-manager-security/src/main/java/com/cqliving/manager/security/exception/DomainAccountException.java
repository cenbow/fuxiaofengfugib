/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.manager.security.exception;

/**
 * Title:
 * <p>Description:账号和域名不匹配</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月30日
 */
public class DomainAccountException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DomainAccountException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DomainAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DomainAccountException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DomainAccountException(Throwable cause) {
		super(cause);
	}
}
