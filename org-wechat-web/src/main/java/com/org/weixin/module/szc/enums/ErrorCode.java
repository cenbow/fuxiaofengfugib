/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.enums;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月4日
 */
public enum ErrorCode {

	AWARD_NOT_BEGIN(-11111,"未开始抽奖"),
	AWARD_NUM_INSUFFICIENT(-22222,"奖品已抽完"),
	USER_HAD_AWARD(-33333,"你已抽奖了"),
	
	VERIFY_ERR_NOT_AWARD(-4444,"兑奖码错误,请重新输入"),
	HAD_VERIFY(-5555,"已兑奖"),
	VERIFY_AWARD_NUM_INSUFFICIENT(-6666,"不能兑奖,奖品已兑完");
	
	public int code;
	public String msg;
	/**
	 * @param code
	 * @param msg
	 */
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
