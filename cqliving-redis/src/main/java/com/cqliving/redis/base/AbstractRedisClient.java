package com.cqliving.redis.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Transaction;

/**
 * 抽象redis客服端基类 Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2013
 * 
 * @author Administrator on 2014年12月22日
 */
public abstract class AbstractRedisClient {
	// =================================定义抽象方法======================
	/**
	 * 设置key的失效时间（单位：秒） Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public abstract long expire(String key, int seconds);

	/**
	 * 设置一个key在某个时间点过期 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param unixTimestamp
	 * @return
	 */
	public abstract long expireAt(String key, int unixTimestamp);

	/**
	 * 删除区间以外的数据
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract String trim(String key, long start, long end);

	/**
	 * 获取对应set的长度 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract long getSetLength(String key);

	/**
	 * 添加到Set中（同时设置过期时间） Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param expireSeconds
	 * @param value
	 * @return
	 */
	public abstract boolean addSet(String key, int expireSeconds, String... value);

	/**
	 * 添加到Set中 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param expireSeconds
	 * @param value
	 * @return
	 */
	public abstract boolean addSet(String key, String... value);

	/**
	 * 添加到Set中 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param expireSeconds
	 * @param value
	 * @return
	 */
	public abstract <T> boolean addSet(String key, T value);

	/**
	 * 获取序列化对象set Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract <T> Set<T> getObjectSet(String key);
	public abstract <T> Set<T> getObjectSet(String key, Class<T> clazz);
	/**
	 * 检查对应的set集合中是否包含对应的value Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean containsInSet(String key, String value);

	/**
	 * 获取对应的set集合 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract Set<String> getSet(String key);

	/**
	 * 从set中删除value
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean removeSetValue(String key, String... value);

	/**
	 * 从list中删除value 默认count 1
	 * 
	 * @param key
	 * @param values
	 *            值list
	 * @return
	 */
	public abstract int removeListValue(String key, List<String> values);

	/**
	 * 从list中删除value
	 * 
	 * @param key
	 * @param count
	 * @param values
	 *            值list
	 * @return
	 */
	public abstract int removeListValue(String key, long count, List<String> values);

	/**
	 * 从list中删除value
	 * 
	 * @param key
	 * @param count
	 *            要删除个数
	 * @param value
	 * @return
	 */
	public abstract boolean removeListValue(String key, long count, String value);

	/**
	 * 截取List
	 * 
	 * @param key
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public abstract List<String> rangeList(String key, long start, long end);

	/**
	 * 检查List长度
	 * 
	 * @param key
	 * @return
	 */
	public abstract long getListLength(String key);

	/**
	 * 添加到List中（同时设置过期时间）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            过期时间 单位s
	 * @param value
	 * @return
	 */
	public abstract boolean addList(String key, int seconds, String... value);

	/**
	 * 添加到List
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean addList(String key, String... value);

	/**
	 * 添加到List(只新增)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean addList(String key, List<String> list);

	/**
	 * 获取List
	 * 
	 * @param key
	 * @return
	 */
	public abstract List<String> getList(String key);

	/**
	 * 设置HashSet对象
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @param value
	 *            Json String or String value
	 * @return
	 */
	public abstract boolean setHSet(String domain, String key, String value);

	/**
	 * Title:
	 * <p>Description:设置map值</p>
	 * @author fuxiaofeng on 2016年11月21日
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean setHMap(String key,Map<String,String> value);
	
	/**
	 * Title:
	 * <p>Description:获取hashMap</p>
	 * @author fuxiaofeng on 2016年11月21日
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract List<String> getHMap(String key,String... fields);
	
	//正则表达式获取所有匹配的key
	public abstract Set<String> getAllKeys(String pattern);
	
	/**
	 * 获得HashSet对象
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return Json String or String value
	 */
	public abstract String getHSet(String domain, String key);
	
	/**
	 * 获得HashSet对象
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return Object of clazz type
	 */
	public abstract <T> T getHSet(String domain, String key, Class<T> clazz);

	/**
	 * 删除HashSet对象
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return 删除的记录数
	 */
	public abstract long delHSet(String domain, String key);

	/**
	 * 删除HashSet对象
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return 删除的记录数
	 */
	public abstract long delHSet(String domain, String... key);

	/**
	 * 判断key是否存在
	 * 
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return
	 */
	public abstract boolean existsHSet(String domain, String key);

	/**
	 * 全局扫描hset
	 * 
	 * @param match
	 *            field匹配模式
	 * @return
	 */
	public abstract List<Map.Entry<String, String>> scanHSet(String domain, String match);

	/**
	 * 返回 domain 指定的哈希集中所有字段的value值
	 * 
	 * @param domain
	 * @return
	 */

	public abstract List<String> hvals(String domain);

	/**
	 * 返回 domain 指定的哈希集中所有字段的key值
	 * 
	 * @param domain
	 * @return
	 */

	public abstract Set<String> hkeys(String domain);

	/**
	 * 返回 domain 指定的哈希key值总数
	 * 
	 * @param domain
	 * @return
	 */
	public abstract long lenHset(String domain);

	/**
	 * 设置排序集合
	 * 
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public abstract boolean setSortedSet(String key, long score, String value);

	/**
	 * 获得排序集合
	 * 
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @param orderByDesc
	 * @return
	 */
	public abstract Set<String> getSoredSet(String key, long startScore, long endScore, boolean orderByDesc);

	/**
	 * 计算排序长度
	 * 
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @return
	 */
	public abstract long countSoredSet(String key, long startScore, long endScore);

	/**
	 * 删除排序集合
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean delSortedSet(String key, String value);

	/**
	 * 获得排序集合
	 * 
	 * @param key
	 * @param startRange
	 * @param endRange
	 * @param orderByDesc
	 * @return
	 */
	public abstract Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc);

	/**
	 * 获得排序打分
	 * 
	 * @param key
	 * @return
	 */
	public abstract Double getScore(String key, String member);

	/**
	 * 设置对象并设置有效时间
	 */
	public abstract boolean set(String key, Object value, int second);

	/**
	 * 设置对象
	 */
	public abstract boolean set(String key, Object value);

	/**
	 * 获取给定对象，若为空，则直接返回默认值 Title:
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public abstract String get(String key, String defaultValue);
	public abstract String get(String key);
	public abstract <T> T get(String key, Class<T> clazz) ;
	public abstract <T> List<T> getList(String key, Class<T> clazz) ;
	/**
	 * 获取给定对象 Title:
	 * @param key
	 * @param defaultValue
	 * @return
	 */

	/**
	 * 删除操作 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean del(String key);

	/**
	 * 删除序列化对象或set Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean delObjectOrSet(String key);

	/**
	 * 清空缓存 Title:
	 * <p>
	 * Description:
	 * </p>
	 */
	public abstract void flushDB();

	/**
	 * 开始事务 Title:
	 * <p>
	 * Description:
	 * </p>
	 */
	public abstract Transaction multi();

	/**
	 * 事务提交 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param transaction
	 * @return
	 */
	public abstract List<Object> exec(Transaction transaction);

	/**
	 * 
	 * Title:是否存在key值
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean existsKey(String key);
}
