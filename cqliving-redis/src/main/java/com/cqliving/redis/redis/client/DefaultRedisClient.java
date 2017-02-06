package com.cqliving.redis.redis.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.alibaba.fastjson.JSON;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.redis.util.SerializeUtil;
import com.google.common.collect.Sets;

/**
 * 非集群架构客服端 Title:
 * Copyright (c) CQLIVING 2013
 * @author Administrator on 2015年1月13日
 */
public class DefaultRedisClient extends AbstractRedisClient {
	private static final Logger logger = LoggerFactory.getLogger(DefaultRedisClient.class);

	@Resource
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 失效时间设置
	 */
	@Override
	public long expire(String key, int seconds) {
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("Set expire fail!key=" + key, e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	//获取剩余过期时间(毫秒级)
	public long getSurplusExpireTime(String key){
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.pttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}
	
	//获取剩余过期时间(秒级)
	public long getSurplusExpireTimeS(String key){
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.ttl(key);
		} catch (Exception e) {
			e.printStackTrace();
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}
	
	@Override
	public long expireAt(String key, int unixTimestamp) {
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.expireAt(key, unixTimestamp);
		} catch (Exception e) {
			logger.error("Set expireAt fail!key=" + key, e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public String trim(String key, long start, long end) {
		if (StringUtils.isEmpty(key)) {
			return "-";
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.ltrim(key, start, end);
		} catch (Exception e) {
			logger.error("LTRIM error[key=" + key + " start=" + start + " end=" + end + "]", e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return "-";
	}

	@Override
	public long getSetLength(String key) {
		if (StringUtils.isEmpty(key)) {
			return 0L;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.scard(key);
		} catch (Exception e) {
			logger.error("GetSetLength error[key=" + key + "]", e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public boolean addSet(String key, int expireSeconds, String... value) {
		boolean result = this.addSet(key, value);
		if (result) {
			long i = this.expire(key, expireSeconds);
			return i == 1;
		}
		return false;
	}

	@Override
	public boolean addSet(String key, String... value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.sadd(key, value);
			return true;
		} catch (Exception e) {
			logger.error("AddSet error[key=" + key + ",value=" + Arrays.toString(value) + "]", e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	//自增
	public long incrBy(String key){
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			this.returnBrokenResource(jedis);
		}finally{
			this.returnResource(jedis);
		}
		return 0;
	}
	
	public long hincrBy(String domain,String key,long value){
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.hincrBy(key, domain, value);
		} catch (Exception e) {
			e.printStackTrace();
			this.returnBrokenResource(jedis);
		}finally{
			this.returnResource(jedis);
		}
		return 0;
	}
	
	/**
	 * 将实体对象装为json
	 */
	@Override
	public <T> boolean addSet(String key, T value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		Jedis jedis = null;
		String json = JSON.toJSONString(value);
//				JacksonMapper.bean2Json(value);
		try {
			jedis = this.getJedis();
			jedis.sadd(key, json);
			return true;
		} catch (Exception e) {
			logger.error("AddSet error[key=" + key + ",value=" + json + "]", e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	/**
	 * 获取bean集合 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> Set<T> getObjectSet(String key, Class<T> clazz) {
		Set<T> setObject = Sets.newHashSet();
		if (StringUtils.isEmpty(key)) {
			return setObject;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Set<String> set = jedis.smembers(key);
			T obj = null;
			for (String json : set) {
//				obj = JacksonMapper.json2Bean(json, clazz);
				obj = JSON.toJavaObject(JSON.parseObject(json), clazz);
				setObject.add(obj);
			}
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return setObject;
	}

	@Override
	@SuppressWarnings("all")
	public <T> Set<T> getObjectSet(String key) {
		Set<T> setObject = new HashSet<T>();
		if (StringUtils.isEmpty(key)) {
			return setObject;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Set<byte[]> setByte = jedis.smembers(key.getBytes("UTF-8"));
			Iterator<byte[]> iterator = setByte.iterator();
			byte[] temp = null;
			while (iterator.hasNext()) {
				temp = iterator.next();
				setObject.add((T) SerializeUtil.unserialize(temp));
			}
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return setObject;
	}

	@Override
	public boolean containsInSet(String key, String value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.sismember(key, value);
		} catch (Exception ex) {
			logger.error("ContainsInSet error[key=" + key + "value=" + value + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public Set<String> getSet(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.smembers(key);
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public boolean removeSetValue(String key, String... value) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.srem(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("RemoveSetValue error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public int removeListValue(String key, List<String> values) {
		return this.removeListValue(key, 1, values);
	}

	@Override
	public int removeListValue(String key, long count, List<String> values) {
		int result = 0;
		if (values != null && values.size() > 0) {
			for (String value : values) {
				if (this.removeListValue(key, count, value)) {
					result++;
				}
			}
		}
		return result;
	}

	@Override
	public boolean removeListValue(String key, long count, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.lrem(key, count, value);
			return true;
		} catch (Exception ex) {
			logger.error("RemoveListValue error[key=" + key + ",count=" + count + ",value=" + value + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public List<String> rangeList(String key, long start, long end) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.lrange(key, start, end);
		} catch (Exception ex) {
			logger.error("RangeList error[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage(), ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long getListLength(String key) {
		if (key == null) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.llen(key);
		} catch (Exception ex) {
			logger.error("GetListLength error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public boolean addList(String key, int seconds, String... value) {
		boolean result = this.addList(key, value);
		if (result) {
			long i = this.expire(key, seconds);
			return i == 1;
		}
		return false;
	}

	@Override
	public boolean addList(String key, String... value) {
		if (key == null || value == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.lpush(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("AddList error[key=" + key + Arrays.toString(value) + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public boolean addList(String key, List<String> list) {
		if (StringUtils.isEmpty(key) || list == null || list.size() == 0) {
			return false;
		}
		for (String value : list) {
			this.addList(key, value);
		}
		return true;
	}

	@Override
	public List<String> getList(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.lrange(key, 0, -1);
		} catch (Exception ex) {
			logger.error("GetList error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public boolean setHSet(String domain, String key, String value) {
		if (value == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.hset(domain, key, value);
			return true;
		} catch (Exception ex) {
			logger.error("SetHSet error[key=" + key + ",value=" + value + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public boolean setHMap(String key,Map<String,String> value) {
		if (null == value || value.isEmpty()) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.hmset(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("SetHSet error[key=" + key + ",value=" + value + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}
	
	@Override
	public String getHSet(String domain, String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.hget(domain, key);
		} catch (Exception ex) {
			logger.error("GetHSet error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}
	
	@Override
	public List<String> getHMap(String key,String... fields){
		
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.hmget(key, fields);
		} catch (Exception ex) {
			logger.error("GetHSet error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}
	
	//正则表达式获取所有匹配的key
	@Override
	public Set<String> getAllKeys(String pattern){
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.keys(pattern);
		} catch (Exception ex) {
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}
	
	@Override
	public <T> T getHSet(String domain, String key, Class<T> clazz){
	    Jedis jedis = null;
	    try {
	        jedis = this.getJedis();
	        String json = jedis.hget(domain, key);
	        if(!StringUtils.isEmpty(json)){
	            return JSON.parseObject(json, clazz);
	        }
	    } catch (Exception ex) {
	        logger.error("GetHSet error[key=" + key + "]", ex);
	        this.returnBrokenResource(jedis);
	    } finally {
	        this.returnResource(jedis);
	    }
	    return null;
	}

	@Override
	public long delHSet(String domain, String key) {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			count = jedis.hdel(domain, key);
		} catch (Exception ex) {
			logger.error("DelHSet error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return count;
	}

	@Override
	public long delHSet(String domain, String... key) {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			count = jedis.hdel(domain, key);
		} catch (Exception ex) {
			logger.error("DelHSet error[key=" + Arrays.toString(key) + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return count;
	}

	@Override
	public boolean existsHSet(String domain, String key) {
		boolean isExist = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			isExist = jedis.hexists(domain, key);
		} catch (Exception ex) {
			logger.error("ExistsHSet error[key=" + key + "]", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return isExist;
	}

	@Override
	public List<Entry<String, String>> scanHSet(String domain, String match) {
		return null;
	}

	@Override
	public List<String> hvals(String domain) {
		Jedis jedis = null;
		List<String> retList = null;
		try {
			jedis = this.getJedis();
			retList = jedis.hvals(domain);
		} catch (Exception ex) {
			logger.error("Hvals error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return retList;
	}

	@Override
	public Set<String> hkeys(String domain) {
		Jedis jedis = null;
		Set<String> retList = null;
		try {
			jedis = this.getJedis();
			retList = jedis.hkeys(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return retList;
	}

	@Override
	public long lenHset(String domain) {
		Jedis jedis = null;
		long retList = 0;
		try {
			jedis = this.getJedis();
			retList = jedis.hlen(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return retList;
	}

	@Override
	public boolean setSortedSet(String key, long score, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.zadd(key, score, value);
			return true;
		} catch (Exception ex) {
			logger.error("setSortedSet error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public Set<String> getSoredSet(String key, long startScore, long endScore, boolean orderByDesc) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (orderByDesc) {
				return jedis.zrevrangeByScore(key, endScore, startScore);
			} else {
				return jedis.zrangeByScore(key, startScore, endScore);
			}
		} catch (Exception ex) {
			logger.error("getSoredSet error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long countSoredSet(String key, long startScore, long endScore) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			Long count = jedis.zcount(key, startScore, endScore);
			return count == null ? 0L : count;
		} catch (Exception ex) {
			logger.error("countSoredSet error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return 0L;
	}

	@Override
	public boolean delSortedSet(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			long count = jedis.zrem(key, value);
			return count > 0;
		} catch (Exception ex) {
			logger.error("delSortedSet error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (orderByDesc) {
				return jedis.zrevrange(key, startRange, endRange);
			} else {
				return jedis.zrange(key, startRange, endRange);
			}
		} catch (Exception ex) {
			logger.error("getSoredSetByRange error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Double getScore(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			return jedis.zscore(key, member);
		} catch (Exception ex) {
			logger.error("getSoredSet error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	/**
	 * 将实体对象装为json保存 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param value
	 * @param second
	 * @param clazz
	 * @return
	 */
	@Override
	public boolean set(String key, Object value, int second) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (value instanceof String) {
				jedis.setex(key, second, (String) value);
			} else {
				jedis.setex(key, second, JSON.toJSONString(value));
//				jedis.setex(key, second, JacksonMapper.bean2Json(value));
			}

			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public boolean set(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (value instanceof String) {
				jedis.set(key, (String) value);
			} else {
				jedis.set(key, JSON.toJSONString(value));
//				jedis.set(key, JacksonMapper.bean2Json(value));
			}
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public String get(String key, String defaultValue) {
		String value = this.get(key);
		return value == null ? defaultValue : value;
	}

	@Override
	public String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			value = jedis.get(key);
		} catch (Exception ex) {
			logger.error("Get error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return value;
	}


	public <T> List<T> getList(String key, Class<T> clazz){
		List<T> value = null;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String json = jedis.get(key);
			if (json != null) {
				value = JSON.parseArray(json, clazz);
			}
		} catch (Exception ex) {
			logger.error("Get error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return value;
	}
	
	@Override
	public <T> T get(String key, Class<T> clazz) {
		T value = null;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			String json = jedis.get(key);
			if (json != null) {
				value = (T) JSON.parseObject(json, clazz);
//				value = (T) JacksonMapper.json2Bean(json, clazz);
			}
		} catch (Exception ex) {
			logger.error("Get error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return value;
	}

	@Override
	public boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.del(key.getBytes("UTF-8"));
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	public boolean delObject(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public boolean delObjectOrSet(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	/**
	 * 获取非切片redis客服端 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	/**
	 * 销毁对象 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param jedis
	 */
	public void returnBrokenResource(Jedis jedis) {
		jedis.close();
	}

	/**
	 * 释放对象 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param jedis
	 */
	public void returnResource(Jedis jedis) {
		jedis.close();
	}

	/**
	 * 清空缓存
	 */
	@Override
	public void flushDB() {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.flushDB();
		} catch (Exception e) {
			logger.error("Flush DB fail!", e);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
	}

	/**
	 * 开始事务
	 */
	@Override
	public Transaction multi() {
		Transaction transaction = null;
		try {
			Jedis jedis = this.getJedis();
			transaction = jedis.multi();
		} catch (Exception e) {
			logger.error("Redis Transaction start fail!", e);
		}
		return transaction;
	}

	/**
	 * 提交事务
	 */
	@Override
	public List<Object> exec(Transaction transaction) {
		try {
			return transaction.exec();
		} catch (Exception e) {
			logger.error("Redis submit transaction fail!", e);
		}
		return null;
	}

	@Override
	public boolean existsKey(String key) {
		Boolean flag = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			flag = jedis.exists(key);
		} catch (Exception ex) {
			logger.error("query error.", ex);
			this.returnBrokenResource(jedis);
		} finally {
			this.returnResource(jedis);
		}
		return flag;
	}

}
