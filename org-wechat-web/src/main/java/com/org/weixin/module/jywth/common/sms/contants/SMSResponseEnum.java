/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common.sms.contants;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月2日
 */
public enum SMSResponseEnum {

	SEND_FAILUR("0","发送成功"),
	SEND_SUCC("5001","发送失败"),
	AUTH_ERR("1520403","签名验证失败");
	
	public String code;
	public String msg;
	/**
	 * @param code
	 * @param msg
	 */
	private SMSResponseEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	
}
