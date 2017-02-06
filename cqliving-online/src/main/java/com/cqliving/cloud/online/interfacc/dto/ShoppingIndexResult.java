package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月23日
 */
public class ShoppingIndexResult {
	
	/** 分类ID（顶级） */
	private Long categoryId;
	/** 分类名称 */
	private String categoryName;
	/** 商品列表 */
	private List<ShoppingData> dataList;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ShoppingData> getDataList() {
		return dataList;
	}

	public void setDataList(List<ShoppingData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}