/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.basic.facade;

import java.util.Map;

import com.cqliving.basic.constant.RedisCachedKey;
import com.cqliving.basic.service.impl.BasicServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:用来获取配置项的类
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author YUWU on 2015年10月19日
 */
public class PropertiesConfig{

	private static AbstractRedisClient abstractRedisClient;
	
	private static BasicServiceImpl basicServiceImpl;
	
	/**
	 * <p>Description:通过key获取配置项</p>
	 * <p>首先从配置文件获取，配置文件当中找不到从缓存获取，缓存找不到从数据库获取</p>
	 * @param key
	 * @return
	 * @author YUWU on 2015年10月19日
	 */
	/*public static String get(String key){
		String val = ApplicationProperties.getProperty(key);
		if(notEmpty(val)) return val;
		return getDirect(key);
	}*/
	
	/**
	 * <p>Description:通过key获取配置项的String类型数值</p>
	 * @param key
	 * @return
	 * @author YUWU on 2015年10月19日
	 */
	public static String getString(String key){
		return getDirect(key);
	}
	
	/**
	 * <p>Description:直接从缓存获取配置项</p>
	 * @param key
	 * @return
	 * @author YUWU on 2015年10月19日
	 */
	public static String getDirect(String key){
		Map<String,String> map = getCachedProperty();
		return map.get(key);
	}
	
	/**
	 * <p>Description:将配置项的值以Long返回</p>
	 * @param key
	 * @return
	 * @author YUWU on 2015年10月19日
	 */
	public static Long getLong(String key){
		String val = getDirect(key);
		if(notEmpty(val)){
			return Long.parseLong(val);
		}
		return null;
	}
	
	/**
	 * <p>Description:将配置项的值以Integer返回</p>
	 * @param key
	 * @return
	 * @author YUWU on 2015年10月19日
	 */
	public static Integer getInteger(String key){
		String val = getDirect(key);
		if(notEmpty(val)){
			return Integer.parseInt(val);
		}
		return null;
	}
	private static boolean notEmpty(String val){
		return val!=null && val.length()>0;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String, String> getCachedProperty(){
		Map<String,String> map = abstractRedisClient.get(RedisCachedKey.PROPERTIES_KEY,Map.class);
		if(map == null){
			map = basicServiceImpl.loadProperties();
		}
		return map;
	}

	public static void setFutureRedisClient(AbstractRedisClient abstractRedisClient) {
		PropertiesConfig.abstractRedisClient = abstractRedisClient;
	}

	public static void setBasicServiceImpl(BasicServiceImpl basicServiceImpl) {
		PropertiesConfig.basicServiceImpl = basicServiceImpl;
	}
	
}
