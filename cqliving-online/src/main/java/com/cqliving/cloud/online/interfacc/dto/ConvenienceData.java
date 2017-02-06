package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月13日
 */
public class ConvenienceData {
	
	/** 便民分类序号 */
	private Integer typeSortNo;
	/** 便民分类名称 */
	private String typeName;
	/** 便民名称 */
	private String name;
	/** 便民链接 */
	private String linkUrl;
	/** 便民图标48*48px地址 */
	private String iconMinUrl;
	/** 便民图标72*72px地址 */
	private String iconMaxUrl;

	public Integer getTypeSortNo() {
		return typeSortNo;
	}

	public void setTypeSortNo(Integer typeSortNo) {
		this.typeSortNo = typeSortNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getIconMinUrl() {
		return iconMinUrl;
	}

	public void setIconMinUrl(String iconMinUrl) {
		this.iconMinUrl = iconMinUrl;
	}

	public String getIconMaxUrl() {
		return iconMaxUrl;
	}

	public void setIconMaxUrl(String iconMaxUrl) {
		this.iconMaxUrl = iconMaxUrl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}