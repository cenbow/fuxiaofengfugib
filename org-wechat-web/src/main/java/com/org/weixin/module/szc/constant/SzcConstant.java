/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.constant;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月2日
 */
public class SzcConstant {
	
	//短信相关常量
	public final static String SZC_SMS_TOKEN = "szc_sms_token";
	public final static String SZC_SMS_PUBLIC_KEY = "szc_sms_public_key";
	public final static String SZC_SMS_CHANNEL = "szc_sms_channel";
	public final static String SZC_SMS_URL = "szc_sms_url";
	//核销匹配验证码
	public final static String SZC_VERIFY_SECURIRY_CODE = "szc_verify_securiry_code";
	
	//璧山中奖短信
	public final static String SZC_BISHAN_SMS_MSG = "szc_bishan_sms_msg";
	//合肥中奖短信
	public final static String SZC_HEFEI_SMS_MSG = "szc_hefei_sms_msg";
	//南京中奖短信
	public final static String SZC_NANJING_SMS_MSG = "szc_nanjing_sms_msg";
	
	//合肥
	public final static Integer HEFEI = 1;
	//南京
	public final static Integer NANJING = 2;
	//璧山
	public final static Integer BISHAN = 3;
	
	public final static Map<Integer,String> DISTRICT_MAP = Maps.newHashMap();
	public final static Map<Integer,String> DISTRICT_MSG_MAP = Maps.newHashMap();
	public final static Map<Integer,String> DISTRICT_TITLE = Maps.newHashMap();
	static {
		DISTRICT_MAP.put(HEFEI, "合肥");
		DISTRICT_MAP.put(NANJING,"南京");
		DISTRICT_MAP.put(BISHAN,"璧山");
		
		DISTRICT_MSG_MAP.put(HEFEI,SZC_HEFEI_SMS_MSG);
		DISTRICT_MSG_MAP.put(NANJING,SZC_NANJING_SMS_MSG);
		DISTRICT_MSG_MAP.put(BISHAN,SZC_BISHAN_SMS_MSG);
		
		DISTRICT_TITLE.put(HEFEI,"#合肥头条#");
		DISTRICT_TITLE.put(NANJING,"砂之船奥莱VIP粉丝群");
		DISTRICT_TITLE.put(BISHAN,"砂之船奥莱VIP粉丝群");
	}
}
