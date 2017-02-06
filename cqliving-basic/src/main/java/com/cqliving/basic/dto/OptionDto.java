/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.basic.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月30日
 */
public class OptionDto {

	/** ID */
	private Long id;
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 类别编码 */
	private String typeCode;
	/** 类别名称 */
	private String typeName;
	/** 排序值 */
	private int sortKey;
	//区域
	private List<RegionDto> region;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getSortKey() {
		return sortKey;
	}
	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}
	public List<RegionDto> getRegion() {
		return region;
	}
	public void setRegion(List<RegionDto> region) {
		this.region = region;
	}
}
