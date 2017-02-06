/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.dao.support;

import java.util.List;

import com.cqliving.framework.common.dao.jdbc.PagedJdbcTemplate;
import com.google.common.collect.Lists;
/**
 * Title:滚动分页类
 * <p>Description:配合实现滚动分页功能，与传统分页不同</p>
 * <p>使用方法：需要设置此对象的各个参数，然后使用 @see {@link PagedJdbcTemplate#queryPage(Class, ScrollPage, String, Object...)}进行查询</p>
 * <p>第一页不用设置value值，从第二页开始，需要将value值设置为上一页最后一条数据，且排序列字段的值</p>
 * <p>传统分页对象请使用@see com.CQLIVING.framework.common.dao.support.PageInfo</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2014年12月19日
 */
public class ScrollPage<T> {
	
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	
	/** 查询分页结果 */
	private List<T> pageResults;
	
	/**
	 * 瀑布流式分页中
	 * 1、排序字段最多只有两个
	 * 2、其中必须有一个唯一约束排序字段（orderUniqueColumn），另一个业务排序字段（orderBuessColumn）的值可以重复。
	 * 唯一约束排序字段，该属性值不能为空。如果该属性里面的columnName在表中不唯一，则可能会有重复值
	 */
	private ScrollPageOrder orderUniqueColumn = new ScrollPageOrder("id",ScrollPage.DESC,null);
	/**
	 * 业务排序字段，该属性值可以为空
	 */
	private ScrollPageOrder orderBuessColumn;
	

	private List<ScrollPageOrder> listOrderColumn = Lists.newArrayList();
	
	/** 每页显示多少条*/
	private int pageSize;

	public List<T> getPageResults() {
		return pageResults;
	}
	public void setPageResults(List<T> pageResults) {
		this.pageResults = pageResults;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public ScrollPageOrder getOrderUniqueColumn() {
		return orderUniqueColumn;
	}
	@Deprecated
	public void setOrderUniqueColumn(ScrollPageOrder orderUniqueColumn) {
		this.orderUniqueColumn = orderUniqueColumn;
	}
	public ScrollPageOrder getOrderBuessColumn() {
		return orderBuessColumn;
	}
	@Deprecated
	public void setOrderBuessColumn(ScrollPageOrder orderBuessColumn) {
		this.orderBuessColumn = orderBuessColumn;
	}
	public List<ScrollPageOrder> getListOrderColumn() {
		return listOrderColumn;
	}
	public void setListOrderColumn(List<ScrollPageOrder> listOrderColumn) {
		this.listOrderColumn = listOrderColumn;
	}
	
	public void addScrollPageOrder(ScrollPageOrder scrollPageOrder) {
		this.listOrderColumn.add(scrollPageOrder);
	}
}