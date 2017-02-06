package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title: 旅游专题
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月5日
 */
public class TourismSpecialData {

	/** 专题描述 */
	private String description;
	/** 旅游子列表 */
	private List<TourismInfoData> dataList;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TourismInfoData> getDataList() {
		return dataList;
	}

	public void setDataList(List<TourismInfoData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}