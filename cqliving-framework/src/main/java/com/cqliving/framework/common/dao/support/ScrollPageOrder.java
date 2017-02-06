/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.dao.support;

/**
 * Title:滚动分页类
 * <p>Description:配合实现滚动分页功能，与传统分页不同</p>
 * <p>使用方法：需要设置此对象的各个参数，然后使用 @see {@link PagedJdbcTemplate#queryPage(Class, CopyOfScrollPage, String, Object...)}进行查询</p>
 * <p>第一页不用设置value值，从第二页开始，需要将value值设置为上一页最后一条数据，且排序列字段的值</p>
 * <p>传统分页对象请使用@see com.CQLIVING.framework.common.dao.support.PageInfo</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2014年12月19日
 */
public class ScrollPageOrder {
	
    /**
     * 排序列
     */
	private String columnName;
	/**
	 * 排序方式{ScrollPage.ASC:升序,ScrollPage.DESC:降序}
	 */
	private String orderByType;
	/**
	 * 上一页最后一条数据，第二次查询必须设置该值
	 */
	private Object value;
	
	public ScrollPageOrder(){}
	public ScrollPageOrder(String columnName,String orderByType,Object value){
	    this.columnName = columnName;
	    this.orderByType = orderByType;
	    this.value = value;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOrderByType() {
		return orderByType;
	}
	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}