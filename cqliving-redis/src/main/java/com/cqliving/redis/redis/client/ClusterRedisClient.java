package com.cqliving.redis.redis.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import com.alibaba.fastjson.JSON;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.redis.util.SerializeUtil;
import com.google.common.collect.Sets;

/**
 * Title:Redis3.0版本后的集群操作客户端
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author yuwu on 2016年6月19日
 */
public class ClusterRedisClient extends AbstractRedisClient {
	private static final Logger logger = LoggerFactory.getLogger(ClusterRedisClient.class);

	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 失效时间设置
	 */
	@Override
	public long expire(String key, int seconds) {
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public long expireAt(String key, int unixTimestamp) {
		if (StringUtils.isEmpty(key)) {
			return 0;
		}
		try {
			return jedisCluster.expireAt(key, unixTimestamp);
		} catch (Exception e) {
			logger.error("Set expireAt fail!key=" + key, e);
		}
		return 0;
	}

	@Override
	public String trim(String key, long start, long end) {
		if (StringUtils.isEmpty(key)) {
			return "-";
		}
		try {
			return jedisCluster.ltrim(key, start, end);
		} catch (Exception e) {
			logger.error("LTRIM error[key=" + key + " start=" + start + " end=" + end + "]", e);
		}
		return "-";
	}

	@Override
	public long getSetLength(String key) {
		if (StringUtils.isEmpty(key)) {
			return 0L;
		}
		try {
			return jedisCluster.scard(key);
		} catch (Exception e) {
			logger.error("GetSetLength error[key=" + key + "]", e);
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
		try {
			jedisCluster.sadd(key, value);
			return true;
		} catch (Exception e) {
			logger.error("AddSet error[key=" + key + ",value=" + Arrays.toString(value) + "]", e);
		}
		return false;
	}

	/**
	 * 将实体对象装为json
	 */
	@Override
	public <T> boolean addSet(String key, T value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		String json = JSON.toJSONString(value);
//				JacksonMapper.bean2Json(value);
		try {
			jedisCluster.sadd(key, json);
			return true;
		} catch (Exception e) {
			logger.error("AddSet error[key=" + key + ",value=" + json + "]", e);
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
	@SuppressWarnings("unchecked")
	public <T> Set<T> getObjectSet(String key, Class<T> clazz) {
		Set<T> setObject = Sets.newHashSet();
		if (StringUtils.isEmpty(key)) {
			return setObject;
		}
		try {
			Set<String> set = jedisCluster.smembers(key);
			Object obj = null;
			for (String json : set) {
//				obj = JacksonMapper.json2Bean(json, clazz);
				obj = JSON.toJavaObject(JSON.parseObject(json), clazz);
				setObject.add((T) obj);
			}
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
		}
		return setObject;
	}

	//自增
	public long incrBy(String key){
		try {
			return jedisCluster.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long hincrBy(String domain,String key,long value){
		try {
			return jedisCluster.hincrBy(key, domain, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	@SuppressWarnings("all")
	public <T> Set<T> getObjectSet(String key) {
		Set<T> setObject = new HashSet<T>();
		if (StringUtils.isEmpty(key)) {
			return setObject;
		}
		try {
			Set<String> setByte = jedisCluster.smembers(key);
			Iterator<String> iterator = setByte.iterator();
			String temp = null;
			while (iterator.hasNext()) {
				temp = iterator.next();
				setObject.add((T) SerializeUtil.unserialize(temp.getBytes()));
			}
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
		}
		return setObject;
	}

	@Override
	public boolean containsInSet(String key, String value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return false;
		}
		try {
			return jedisCluster.sismember(key, value);
		} catch (Exception ex) {
			logger.error("ContainsInSet error[key=" + key + "value=" + value + "]", ex);
		}
		return false;
	}

	@Override
	public Set<String> getSet(String key) {
		try {
			return jedisCluster.smembers(key);
		} catch (Exception ex) {
			logger.error("GetSet error[key" + key + "]", ex);
		}
		return null;
	}

	@Override
	public boolean removeSetValue(String key, String... value) {
		try {
			jedisCluster.srem(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("RemoveSetValue error.", ex);
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
		try {
			jedisCluster.lrem(key, count, value);
			return true;
		} catch (Exception ex) {
			logger.error("RemoveListValue error[key=" + key + ",count=" + count + ",value=" + value + "]", ex);
		}
		return false;
	}

	@Override
	public List<String> rangeList(String key, long start, long end) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		try {
			return jedisCluster.lrange(key, start, end);
		} catch (Exception ex) {
			logger.error("RangeList error[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public long getListLength(String key) {
		if (key == null) {
			return 0;
		}
		try {
			return jedisCluster.llen(key);
		} catch (Exception ex) {
			logger.error("GetListLength error[key=" + key + "]", ex);
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
		try {
			jedisCluster.lpush(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("AddList error[key=" + key + Arrays.toString(value) + "]", ex);
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
		try {
			return jedisCluster.lrange(key, 0, -1);
		} catch (Exception ex) {
			logger.error("GetList error[key=" + key + "]", ex);
		}
		return null;
	}

	@Override
	public boolean setHSet(String domain, String key, String value) {
		if (value == null) {
			return false;
		}
		try {
			jedisCluster.hset(domain, key, value);
			return true;
		} catch (Exception ex) {
			logger.error("SetHSet error[key=" + key + ",value=" + value + "]", ex);
		}
		return false;
	}

	@Override
	public String getHSet(String domain, String key) {
		try {
			return jedisCluster.hget(domain, key);
		} catch (Exception ex) {
			logger.error("GetHSet error[key=" + key + "]", ex);
			
		
			
		}
		return null;
	}

	@Override
	public long delHSet(String domain, String key) {
		long count = 0;
		
		try {
			
			count = jedisCluster.hdel(domain, key);
		} catch (Exception ex) {
			logger.error("DelHSet error[key=" + key + "]", ex);
			
		
			
		}
		return count;
	}

	@Override
	public long delHSet(String domain, String... key) {
		long count = 0;
		
		try {
			
			count = jedisCluster.hdel(domain, key);
		} catch (Exception ex) {
			logger.error("DelHSet error[key=" + Arrays.toString(key) + "]", ex);
			
		
			
		}
		return count;
	}

	@Override
	public boolean existsHSet(String domain, String key) {
		boolean isExist = false;
		
		try {
			
			isExist = jedisCluster.hexists(domain, key);
		} catch (Exception ex) {
			logger.error("ExistsHSet error[key=" + key + "]", ex);
			
		
			
		}
		return isExist;
	}

	@Override
	public List<Entry<String, String>> scanHSet(String domain, String match) {
		return null;
	}

	@Override
	public List<String> hvals(String domain) {
		
		List<String> retList = null;
		try {
			
			retList = jedisCluster.hvals(domain);
		} catch (Exception ex) {
			logger.error("Hvals error.", ex);
			
		
			
		}
		return retList;
	}

	@Override
	public Set<String> hkeys(String domain) {
		
		Set<String> retList = null;
		try {
			
			retList = jedisCluster.hkeys(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
			
		
			
		}
		return retList;
	}

	@Override
	public long lenHset(String domain) {
		
		long retList = 0;
		try {
			
			retList = jedisCluster.hlen(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
			
		
			
		}
		return retList;
	}

	@Override
	public boolean setSortedSet(String key, long score, String value) {
		try {
			jedisCluster.zadd(key, score, value);
			return true;
		} catch (Exception ex) {
			logger.error("setSortedSet error.", ex);
		}
		return false;
	}

	@Override
	public Set<String> getSoredSet(String key, long startScore, long endScore, boolean orderByDesc) {
		try {
			if (orderByDesc) {
				return jedisCluster.zrevrangeByScore(key, endScore, startScore);
			} else {
				return jedisCluster.zrangeByScore(key, startScore, endScore);
			}
		} catch (Exception ex) {
			logger.error("getSoredSet error.", ex);
		}
		return null;
	}

	@Override
	public long countSoredSet(String key, long startScore, long endScore) {
		try {
			Long count = jedisCluster.zcount(key, startScore, endScore);
			return count == null ? 0L : count;
		} catch (Exception ex) {
			logger.error("countSoredSet error.", ex);
		}
		return 0L;
	}

	@Override
	public boolean delSortedSet(String key, String value) {
		try {
			long count = jedisCluster.zrem(key, value);
			return count > 0;
		} catch (Exception ex) {
			logger.error("delSortedSet error.", ex);
		}
		return false;
	}

	@Override
	public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {
		try {
			if (orderByDesc) {
				return jedisCluster.zrevrange(key, startRange, endRange);
			} else {
				return jedisCluster.zrange(key, startRange, endRange);
			}
		} catch (Exception ex) {
			logger.error("getSoredSetByRange error.", ex);
		}
		return null;
	}

	@Override
	public Double getScore(String key, String member) {
		try {
			return jedisCluster.zscore(key, member);
		} catch (Exception ex) {
			logger.error("getSoredSet error.", ex);
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
		try {
			if (value instanceof String) {
				jedisCluster.setex(key, second, (String) value);
			} else {
				jedisCluster.setex(key, second, JSON.toJSONString(value));
//				jedis.setex(key, second, JacksonMapper.bean2Json(value));
			}
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
			
		
			
		}
		return false;
	}

	@Override
	public boolean set(String key, Object value) {
		try {
			if (value instanceof String) {
				jedisCluster.set(key, (String) value);
			} else {
				jedisCluster.set(key, JSON.toJSONString(value));
//				jedis.set(key, JacksonMapper.bean2Json(value));
			}
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
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
		try {
			value = jedisCluster.get(key);
		} catch (Exception ex) {
			logger.error("Get error.", ex);
		}
		return value;
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clazz){
		List<T> value = null;
		try {
			String json = jedisCluster.get(key);
			if (json != null) {
				value = JSON.parseArray(json, clazz);
//				value = (T) JacksonMapper.json2Bean(json, clazz);
			}
		} catch (Exception ex) {
			logger.error("Get error.", ex);
		}
		return value;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		T value = null;
		try {
			String json = jedisCluster.get(key);
			if (json != null) {
				value = (T) JSON.parseObject(json, clazz);
//				value = (T) JacksonMapper.json2Bean(json, clazz);
			}
		} catch (Exception ex) {
			logger.error("Get error.", ex);
		}
		return value;
	}

	@Override
	public boolean del(String key) {
		try {
			jedisCluster.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
		}
		return false;
	}

	public boolean delObject(String key) {
		try {
			jedisCluster.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
		}
		return false;
	}

	@Override
	public boolean delObjectOrSet(String key) {
		try {
			jedisCluster.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
		}
		return false;
	}


	/**
	 * 清空缓存
	 */
	@Override
	@Deprecated
	public void flushDB() {
		try {
			jedisCluster.flushDB();
		} catch (Exception e) {
			logger.error("Flush DB fail!", e);
		}
	}

	/**
	 * 开始事务
	 */
	@Override
	public Transaction multi() {
		throw new RuntimeException("redis集群不支持事务");
//		Transaction transaction = null;
//		try {
//			transaction = jedisCluster.multi();
//		} catch (Exception e) {
//			logger.error("Redis Transaction start fail!", e);
//		}
//		return transaction;
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
		try {
			flag = jedisCluster.exists(key);
		} catch (Exception ex) {
			logger.error("query error.", ex);
		}
		return flag;
	}

    @Override
    public <T> T getHSet(String domain, String key, Class<T> clazz) {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.redis.base.AbstractRedisClient#setHMap(java.lang.String, java.util.Map)
	 */
	@Override
	public boolean setHMap(String key, Map<String, String> value) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.redis.base.AbstractRedisClient#getHMap(java.lang.String, java.lang.String[])
	 */
	@Override
	public List<String> getHMap(String key, String... fields) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.redis.base.AbstractRedisClient#getAllKeys(java.lang.String)
	 */
	@Override
	public Set<String> getAllKeys(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

}
