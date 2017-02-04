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
 * @author fuxiaofeng on 2016年9月2日
 */
public enum SmsErrorCode {

	SUCCESS(0,"请求成功"),
	FAILURE(-1,"请求失败"),
	PARAM_NOT_NULL(1068001,"必要参数不能为空"),
	SIGN_ERROR(1068110,"签名无效"),
	TIMESTAMP_ERR(1068111,"请求时间戳格式不对"),
	OVERDUE(1068112,"请求已过期"),
	DECODE_FAILURE(1068115,"解码失败,加密格式不对"),
	PHONE_ERR(1068201,"手机号码不合法"),
	TYPE_ERR(1069110,"发送类型不合法"),
	NUM_OVER_NUM(1069112,"发送条数超过当日限制"),
	MSG_SEND_ERR(1069113,"发送消息失败");
	
	public Integer code;
	public String msg;
	/**
	 * @param code
	 * @param msg
	 */
	private SmsErrorCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMessage(Integer code){
		
		SmsErrorCode[] ercode = SmsErrorCode.values();
		for(SmsErrorCode c : ercode){
			if(code.intValue() == c.code.intValue()){
				return c.msg;
			}
		}
		return null;
	}
}
