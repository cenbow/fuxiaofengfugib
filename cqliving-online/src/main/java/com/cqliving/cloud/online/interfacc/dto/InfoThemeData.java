package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月6日
 */
public class InfoThemeData {

	/** ID */
	private Long id;
	/** 分类名称 */
	private String name;
	/** 分类颜色 */
	private String color;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getColor(){
		return this.color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}