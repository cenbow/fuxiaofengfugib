package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

/**
 * Title:商情app自主上传接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月21日
 */
public class ShopInfoAddData {
	
	/** 栏目id **/
	private Long id;
	/** 栏目名称 **/
	private String name;
	/** 栏目对应的分类集合 **/
	private List<ShopKeyValueData> typeList;
	/** 栏目对应的区域集合 **/
	private List<CommonKeyValueData> regionList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ShopKeyValueData> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<ShopKeyValueData> typeList) {
		this.typeList = typeList;
	}
	public List<CommonKeyValueData> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<CommonKeyValueData> regionList) {
		this.regionList = regionList;
	}
	
}
