/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.redis.test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Title:
 * @author yuwu on 2016年1月23日
 */
public class RedisTest{
	
	public static void main(String args[]){
		JedisPoolConfig config = new JedisPoolConfig(); 
		config.setMaxTotal(100);
		config.setMaxIdle(20);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(true);
			
		JedisPool jedisPool= new JedisPool(config, "127.0.0.1");
		
		jedisPool.getResource().set("key1","value111111");
//		FutureRedisClient futureRedisClient = new FutureRedisClient();
//		futureRedisClient.setJedisPool(jedisPool);
	}
}
