package com.cqliving.manager.security.exception;

import java.util.Date;

/**
 * 账户锁定
 */
public class AccountLockedException extends AuthenticationException {

	private static final long serialVersionUID = -7818319355313908808L;
	
	public AccountLockedException(Date unlockTime) {
		super();
		this.unlockTime = unlockTime;
	}

	private Date unlockTime;

	public Date getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}


}
