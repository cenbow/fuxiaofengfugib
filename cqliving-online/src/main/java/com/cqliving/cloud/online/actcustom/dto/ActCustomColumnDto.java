package com.cqliving.cloud.online.actcustom.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月22日
 */
public class ActCustomColumnDto {
	/** ID */
	private Long id;
	/** 参数名称 */
	private String name;
	/** 参数类型 */
	private Byte type;
	/** 描述信息 */
	private String description;
	/** 值 */
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
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
