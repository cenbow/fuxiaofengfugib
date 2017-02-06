/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.basic.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.baisc.BaseTest;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:
 * @author yuwu on 2016年6月20日
 */
public class BasicServiceTest extends BaseTest{
	//private static final Logger logger = LoggerFactory.getLogger(DemoVideoInfoTest.class);
	
	@Autowired
	public AbstractRedisClient abstractRedisClient;
	
	@Test
	public void testGet(){
		System.out.println(PropertiesConfig.getString("file.local.path"));
		System.out.println(PropertiesConfig.getString("file.local.path"));
		/*System.out.println("开始");
		abstractRedisClient.set("key1", "value1111");
		String weatherKey = PropertiesConfig.getString("weather_key");
		System.out.println("weatherKey=" + weatherKey);
		System.out.println(abstractRedisClient.get("key1"));
		System.out.println("结束");*/
	}
}
