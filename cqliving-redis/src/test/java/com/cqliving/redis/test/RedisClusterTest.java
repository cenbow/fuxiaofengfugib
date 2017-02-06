/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.redis.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author yuwu on 2016年1月23日
 */
public class RedisClusterTest extends BaseTest{
	//private static final Logger logger = LoggerFactory.getLogger(DemoVideoInfoTest.class);
	
	@Autowired
	public AbstractRedisClient abstractRedisClient;
	
	@Test
	public void testGet(){
		List<Shoot> list = new ArrayList<Shoot>();
		list.add(new Shoot("name1","value1"));
		list.add(new Shoot("name2","value2"));
		list.add(new Shoot("name3","value3"));
		System.out.println("开始");
		abstractRedisClient.set("key1", "value1");
		String key1 = "list";
        abstractRedisClient.del(key1);
		abstractRedisClient.set(key1, list);
		List<Shoot> list1 = abstractRedisClient.getList(key1,Shoot.class);
		System.out.println(list1);
		//System.out.println(futureRedisClient.get("key2"));
		//System.out.println(defaultRedisClient.get("key1"));
		System.out.println("结束");
	}
	
	//@Test
	public void clusterTest(){
		System.out.println(abstractRedisClient.get("name"));
		System.out.println(abstractRedisClient.get("value"));
		System.out.println(abstractRedisClient.get("aa"));
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("127.0.0.1",7001));
		nodes.add(new HostAndPort("127.0.0.1",7002));
		nodes.add(new HostAndPort("127.0.0.1",7003));
		nodes.add(new HostAndPort("127.0.0.1",7004));
		nodes.add(new HostAndPort("127.0.0.1",7005));
		nodes.add(new HostAndPort("127.0.0.1",7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("name", "yuwu");
		cluster.set("value", "hahaha");
		System.out.println(cluster.get("name"));
		System.out.println(cluster.get("value"));
		System.out.println(cluster.get("aa"));
		cluster.close();
	}
}

class Shoot{
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Shoot(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Shoot() {
	}
}