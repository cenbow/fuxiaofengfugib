/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.sms.provider;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.util.JsonUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月2日
 */
public class SmsSendUtil {

	private final static Logger logger = LoggerFactory.getLogger(SmsSendUtil.class);
	
	public static String sendSms(SmsRequest smsRequest,String url){
		
		String paramStr = JsonUtil.toJSONString(smsRequest);
		//Map<String,String> params = SmsSendUtil.objMapCoverStrMap(ObjectUtils.beanToMap(smsRequest));
		logger.info(">>>>>>>>>>>>>>>发送短信请求参数：{}",paramStr);
		return HttpClientUtil.sendRequest(url,paramStr);
		
	}
	
	public static Map<String,String> objMapCoverStrMap(Map<String,Object> map){
		
		if(null == map || map.isEmpty())return null;
		Map<String,String> strMap = Maps.newHashMap();
		for(String key : map.keySet()){
			strMap.put(key, map.get(key).toString());
		}
		return strMap;
	}
}
