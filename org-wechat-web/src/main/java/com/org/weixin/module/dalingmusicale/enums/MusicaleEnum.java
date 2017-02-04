/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.enums;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public enum MusicaleEnum {

	SMS_CODE_ERR(-11111,"短信验证码错误"),
	PHONE_NOT_NULL(-22222,"获取验证码手机号不能为空"),
	SEND_SMS_FREQUENTLY(-33333,"发送短信过于频繁"),
	VERIFY_ERR_MSG_NOT_NULL(-44444,"手机号或验证码不能为空"),
	VERIFY_ERR_CODE_ERR(-55555,"验证码错误或已过期"),
	
	VOTE_NOT_START(-66666,"投票未进行"),
	VOTE_NOT_EXIST(-99999,"投票不存在"),
	HAD_VOTE(-77777,"您已经投票了"),
	SHARE_CAN_VOTE(-88888,"请分享后再投票"),
	
	MUSICALE_NOT_START(-99999,"音乐会抢票时间未开始"),
	MUSICALE_OVER(-12121,"音乐会已结束"),
	MUSICALE_NO_TICKET(-11221,"音乐会票已抢光"),
	MUSICALE_SHARE(-12112,"请分享后再抢票"),
	MUSICALE_NO_TURN(-22112,"当日最多能抢四次,机会已用尽"),
	MUSICALE_ONE_NO_TURN(-23112,"同一场门票机会已用尽"),
	MUSICALE_NO_GOT(-24112,"未中奖"),
	MUSICALE_NO_GOT_NO_SHARE(-24113,"未中奖未分享"),
	
	VERIFY_TICKET_NOT_EXIST(-21221,"核销码错误"),
	VERIFY_TICKET_HAD_HANDLE(-23221,"已核销"),
	VERIFY_TICKET_NO_TICKET(-23221,"未中奖"),
	VERIFY_TICKET_NOT_SURPLUS(-22221,"门票已核销完");
	public int code;
	public String msg;
	/**
	 * @param code
	 * @param msg
	 */
	private MusicaleEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
