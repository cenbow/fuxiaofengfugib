/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common.sms;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.tool.common.util.ObjectUtils;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;
import com.org.weixin.module.jywth.common.sms.vo.SMSRequest;
import com.org.weixin.module.jywth.common.sms.vo.SMSResponse;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月2日
 */
public class SMSProvider {

	private static Logger logger = LoggerFactory.getLogger(SMSProvider.class);
	
	public static SMSResponse sendSMS(SMSRequest sMSRequest,String url,int type){
		
		Map<String, Object> params = ObjectUtils.beanToMap(sMSRequest);
		
		Map<String,String> strMap = objToStr(params);
		
		strMap.remove("token");
		
		strMap.put("sign", SMSRequest.getSign(sMSRequest,type));
		
		logger.info("调用短信接口参数：{},请求路径：{}", strMap,url);
		
		String result = HttpClientUtil.sendPostRequest(url, strMap, null,null);
		logger.info(String.format("调用短信接口返回：%s", result));
		result = replaceUnicodeStr(result);
		logger.info("回调结果UNICODE转String：{}", result);
		return JSONObject.parseObject(result, SMSResponse.class);
		
	}
	
	public static Map<String,String> objToStr(Map<String, Object> objMap){
		
		if(CollectionUtils.isEmpty(objMap))return null;
		Map<String,String> strMap = Maps.newHashMap();
		
		for(Map.Entry<String,Object> entry:objMap.entrySet()){
			strMap.put(entry.getKey(), null != entry.getValue() ? entry.getValue().toString() : null);
		}
		
		return strMap;
	}
	
	public static String replaceUnicodeStr(String jsonStr){
		
		if(StringUtil.isEmpty(jsonStr))return null;
		
		int begin = jsonStr.indexOf("\\u");
		int end = jsonStr.lastIndexOf("\\u")+6;
		
		String unicodeStr = jsonStr.substring(begin,end);
		String coverStr = unicode2String(unicodeStr);
		jsonStr = jsonStr.replace(unicodeStr, coverStr);
		
		return jsonStr;
		
	}
	
	public static String unicode2String(String unicode) {
		 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    if(string.length()<=0)string.append(hex[0]);
	    
	    return string.toString();
	}

}
