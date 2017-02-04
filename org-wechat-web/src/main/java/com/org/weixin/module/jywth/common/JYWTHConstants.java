/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月2日
 */
public class JYWTHConstants {

	
	public final static String JYWTH_SMS_TOKEN = "jywth_sms_token";
	public final static String JYWTH_SMS_UUID = "jywth_sms_uuid";
	//短信验证码验证接口
	public final static String JYWTH_SMS_AUTHCODE_URL = "jywth_sms_authcode_url";
	//短信验证码发送接口
	public final static String JYWTH_SMS_SENDCODE_URL = "jywth_sms_sendcode_url";
	//短信文本发送接口
	public final static String JYWTH_SMS_SENDTEXT_URL = "jywth_sms_sendtext_url";
	//数据核销密码
	public final static String JYWTH_STATICTIS_TOKEN = "jywth_statictis_token";
	//发送中奖短信文案
	public final static String JYWTH_GETAWARD_TEXT = "jywth_getaward_text";
	//缓存时间
	public final static int MEMCACHED_EXPIRED_TIME = 3600;
	//活动时间阶段
	public final static String JYWTH_AWARD_DURATION_TIME = "jywth_award_duration_time";
	//MD8编码
	public final static String ENCODE_TYPE = "UTF-8";
	
	//抽奖错误码
	//当天已中奖
	public final static int GETAWARD_TODAY_ERR = 0;
	//已中奖
	public final static int GETAWARD_HASGOT_ERR = 1;
	//红包已派完
	public final static int GETAWARD_NOAWARD_ERR = 2;
	//验证码错误
	public final static int GETAWARD_CODE_ERR= 3;
	
	public final static int PHONE_ERR = 4;
	
	public final static Map<Integer,String> errorMap = Maps.newHashMap();
	public final static Integer error1 = 1;
	public final static Integer error2 = 2;
	public final static Integer error3 = 3;
	
	static{
		errorMap.put(1,"核销密码不能为空");
		errorMap.put(2, "请设置核销密码");
		errorMap.put(3, "核销密码不对");
	}
	
}
