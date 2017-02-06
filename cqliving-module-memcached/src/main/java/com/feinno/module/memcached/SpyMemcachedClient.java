/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.feinno.module.memcached;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

/**
 * 对SpyMemcached Client的二次封装,提供常用的Get/GetBulk/Set/Delete/Incr/Decr函数的同步与异步操作封装.
 * 
 * 未提供封装的函数可直接调用getClient()取出Spy的原版MemcachedClient来使用.
 * 
 * @author calvin
 */
@SuppressWarnings("unchecked")
public class SpyMemcachedClient implements DisposableBean {

	private static Logger logger = LoggerFactory.getLogger(SpyMemcachedClient.class);

	private MemcachedClient memcachedClient;

	private long shutdownTimeout = 2500;

	private long updateTimeout = 2500;

	private int expiredTime = 30 * 60 * 1000;

	/**
	 * Get方法, 转换结果类型并屏蔽异常, 仅返回Null.
	 */
	public <T> T get(String key) {
		try {
			return (T) memcachedClient.get(key);
		} catch (RuntimeException e) {
			handleException(e, key);
			return null;
		}
	}

	public <T> CASValue<T> gets(String key) {
		try {
			return (CASValue<T>) memcachedClient.gets(key);
		} catch (RuntimeException e) {
			handleException(e, key);
			return null;
		}
	}

	public void cas(String key, Object value) {
		CASValue<Object> casValue = gets(key);
		cas(key, casValue.getCas(), value);
	}

	public void cas(String key, long casId, Object value) {
		cas(key, casId, expiredTime, value);
	}

	public void cas(String key, long casId, int exp, Object value) {
		try {
			CASResponse response = memcachedClient.cas(key, casId, exp, value, memcachedClient.getTranscoder());
			if (response != CASResponse.OK) {
				throw new RuntimeException("CAS操作失败:" + response);
			}
		} catch (Exception e) {
			handleException(e, key);
			throw new RuntimeException("spymemcached client receive an exception with key:" + key + " : "
					+ e.getMessage());
		}
	}

	/**
	 * GetBulk方法, 转换结果类型并屏蔽异常.
	 */
	public <T> Map<String, T> getBulk(Collection<String> keys) {
		try {
			return (Map<String, T>) memcachedClient.getBulk(keys);
		} catch (RuntimeException e) {
			handleException(e, StringUtils.join(keys, ","));
			return null;
		}
	}

	/**
	 * 异步Set方法, 不考虑执行结果.
	 */
	public void set(String key, int expiredTime, Object value) {
		memcachedClient.set(key, expiredTime, value);
	}

	public void set(String key, Object value) {
		set(key, expiredTime, value);
	}

	/**
	 * 安全的Set方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
	 */
	public boolean safeSet(String key, int expiration, Object value) {
		Future<Boolean> future = memcachedClient.set(key, expiration, value);
		try {
			return future.get(updateTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return false;
	}

	public boolean safeSet(String key, Object value) {
		return safeSet(key, expiredTime, value);
	}

	/**
	 * 异步 Delete方法, 不考虑执行结果.
	 */
	public void delete(String key) {
		memcachedClient.delete(key);
	}

	/**
	 * 安全的Delete方法, 保证在updateTimeout秒内返回执行结果, 否则返回false并取消操作.
	 */
	public boolean safeDelete(String key) {
		Future<Boolean> future = memcachedClient.delete(key);
		try {
			return future.get(updateTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(false);
		}
		return false;
	}

	/**
	 * Incr方法.
	 */
	public long incr(String key, int by, long defaultValue) {
		return memcachedClient.incr(key, by, defaultValue);
	}

	/**
	 * Decr方法.
	 */
	public long decr(String key, int by, long defaultValue) {
		return memcachedClient.decr(key, by, defaultValue);
	}

	/**
	 * 异步Incr方法, 不支持默认值, 若key不存在返回-1.
	 */
	public Future<Long> asyncIncr(String key, int by) {
		return memcachedClient.asyncIncr(key, by);
	}

	/**
	 * 异步Decr方法, 不支持默认值, 若key不存在返回-1.
	 */
	public Future<Long> asyncDecr(String key, int by) {
		return memcachedClient.asyncDecr(key, by);
	}

	/**
	 * 列表遍历Key 使用: stats item 和 stats cachedump
	 * 
	 * @param keyPrefix
	 * @param limits
	 */
	public List<String> listKeys(String keyPrefix, Integer limits) {
		List<String> keys = new ArrayList<String>();
		MemcachedClient mc = getMemcachedClient();
		Map<SocketAddress, Map<String, String>> stats = mc.getStats("items");
		Set<String> slabs = new HashSet<String>();
		for (Map.Entry<SocketAddress, Map<String, String>> entry : stats.entrySet()) {
			for (Map.Entry<String, String> entry3 : entry.getValue().entrySet()) {
				slabs.add(StringUtils.split(entry3.getKey(), ':')[1]);
			}
		}
		int count = 0;
		for (String slab : slabs) {
			String cachedumpArgs = "cachedump " + slab + " 0";
			Map<SocketAddress, Map<String, String>> data = mc.getStats(cachedumpArgs);
			for (Map.Entry<SocketAddress, Map<String, String>> entry2 : data.entrySet()) {
				for (String key : entry2.getValue().keySet()) {
					if (StringUtils.isNotBlank(keyPrefix) && !StringUtils.startsWith(key, keyPrefix)) {
						continue;
					}
					keys.add(key);
					if (limits != null && limits > 0 && ++count >= limits) {
						return keys;
					}
				}
			}
		}
		return keys;
	}

	@Override
	public void destroy() throws Exception {
		if (memcachedClient != null) {
			memcachedClient.shutdown(shutdownTimeout, TimeUnit.MILLISECONDS);
		}
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setUpdateTimeout(long updateTimeout) {
		this.updateTimeout = updateTimeout;
	}

	public void setShutdownTimeout(long shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}

	public void setExpiredTime(int expiredTime) {
		this.expiredTime = expiredTime;
	}

	private void handleException(Exception e, String key) {
		logger.warn("spymemcached client receive an exception with key:" + key, e);
	}

}