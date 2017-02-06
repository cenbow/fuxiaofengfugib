package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月27日
 */
public class TopicInfoDetailData {

	/** 话题名称 */
	private String name;
	/** 图片列表 */
	private String imageUrls;
	/** 话题内容 */
	private String content;
	/** 话题分类 */
	private String types;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImageUrls() {
		return imageUrls;
	}


	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTypes() {
		return types;
	}


	public void setTypes(String types) {
		this.types = types;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}