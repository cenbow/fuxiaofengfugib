/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.web.property;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:获取配置文件当中的配置
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author YUWU on 2015年10月19日
 */
public class ApplicationProperties {

	private static Map<String, String> properties = new HashMap<String, String>();
	
	public static void addProperty(String key,String value){
		properties.put(key, value);
	}
	
	public static String getProperty(String key){
		return properties.get(key);
	}
	
	public static Long getPropertyLong(String key){
		if(notEmpty(properties.get(key))){
			return Long.parseLong(properties.get(key));
		}
		return null;
	}
	
	public static Integer getPropertyInt(String key){
		if(notEmpty(properties.get(key))){
			return Integer.parseInt(properties.get(key));
		}
		return null;
	}
	
	public static Map<String, String> getProperties(){
		return properties;
	}
	
	private static boolean notEmpty(String val){
		return val!=null && val.length()>0;
	}
}
