package com.cqliving.framework.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * 针对单个Entity对象的操作,不依赖于具体ORM实现方案.
 * 
 * @author zhangpu
 * @param <T>
 *            受管实体
 */
public interface EntityDao<T> {
	/**
	 * 创建实体
	 * 
	 * @param o
	 *            实体对象
	 */
	T create(T o);

	/**
	 * 获取单个实体
	 * 
	 * @param id
	 *            实体ID
	 * @return 实体对象
	 */
	T get(Serializable id);

	/**
	 * 更新实体
	 * 
	 * @param o
	 *            实体对象
	 */
	void update(T o);

	/**
	 * 批量保存
	 * 
	 * @param entities
	 *            实体集合
	 */
	void saves(List<T> entities);

	/**
	 * 删除实体
	 * 
	 * @param o
	 *            实体对象
	 */
	void remove(T o);

	/**
	 * 根据实体ID删除实体
	 * 
	 * @param id
	 *            实体ID
	 */
	void removeById(Serializable id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            实体ID数组
	 */
	void removes(Serializable... ids);

	/**
	 * 获取全部数据
	 * 
	 * @return 实体集合
	 */
	List<T> getAll();

	/**
	 * 多条件,多排序规则的分页查询
	 * 
	 * @param pageInfo
	 *            分页对象(countOfCurrentPage和currentPage为必填)
	 * @param map
	 *            条件Map，Key--> LIKE/EQ/GT/GTE/LT/LTE_propertyName;
	 *            value-->查询条件右值
	 * @param sortMap
	 *            排序Map, Key --> propertyName; value=true(ASC)/false(DESC)
	 * @return PageInfo<T> 查询结果：包括分页信息和当前页数据集合
	 */
	PageInfo<T> query(PageInfo<T> pageInfo, Map<String, Object> map,
			Map<String, Boolean> sortMap);

	/**
	 * 多条件,多排序规则的查询
	 * 
	 * @param map
	 *            条件Map，Key--> LIKE/EQ/GT/GTE/LT/LTE_propertyName;
	 *            value-->查询条件右值
	 * @param sortMap
	 *            排序Map, Key --> propertyName; value=true(ASC)/false(DESC)
	 * @return List<T> 查询结果：满足条件并按直接排序的实体集合
	 */
	List<T> query(Map<String, Object> map, Map<String, Boolean> sortMap);

}
