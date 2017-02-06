package com.cqliving.cloud.online.act.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public class ActCollectOptionResult {

	/** 选项ID **/
	private Long id;
	/** 选项值 **/
	private String value;
	/** 是否被选择 **/
	private Boolean isDefault;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
