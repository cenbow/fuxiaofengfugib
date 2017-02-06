package com.cqliving.cloud.online.interfacc.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年9月9日
 */
public class WzCollectInfoData {

	/** 收集字段ID **/
	private Long id;
	/** 收集信息名称 **/
	private String name;
	/** 用户收集的值 **/
	private String value;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
