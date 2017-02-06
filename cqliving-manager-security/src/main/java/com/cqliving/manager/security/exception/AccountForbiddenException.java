/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.manager.security.exception;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2016年2月18日
 */
public class AccountForbiddenException extends AuthenticationException{

	/**
	 * @param string
	 */
	public AccountForbiddenException(String msg) {
		super(msg);
	}

	public AccountForbiddenException(Throwable cause) {
        super(cause);
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
