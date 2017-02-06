package com.cqliving.cloud.online.act.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月21日
 */
public class ActTestDto {

	private Long id;
	private Byte actType;
	private Byte type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Byte getActType() {
		return actType;
	}
	public void setActType(Byte actType) {
		this.actType = actType;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	
	
}
