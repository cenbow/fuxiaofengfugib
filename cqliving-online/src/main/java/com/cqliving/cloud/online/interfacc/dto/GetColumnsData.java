/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.cloud.online.interfacc.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月1日
 */
public class GetColumnsData implements Serializable {

    private static final long serialVersionUID = 1L;
    /** ID */
	private Long id;
	/** 栏目名称 */
	private String name;
	/** 模板CODE */
	private String templetCode;
	/** 父栏目CODE */
	private String parentCode;
	/** 栏目CODE，增加类型，组合方式：父父栏目ID,父档目ID.自身ID */
	private String code;
	/** 排序号 */
	private Integer sortNo;
	/** 栏目图标 */
	private String imageUrl;
	/** 栏目类型 */
	private Byte columnsType;
	/** 栏目URL */
	private String columnsUrl;
	/** 显示浏览数类型{0:显示,1:不显示} */
	private Byte viewCountType;
	/** 显示评论数类型{0:显示,1:不显示} */
	private Byte replyCountType;
	/** 显示日期类型{0:显示日期,1:不显示日期} */
	private Byte dateType;
	
	public Byte getReplyCountType() {
		return replyCountType;
	}

	public void setReplyCountType(Byte replyCountType) {
		this.replyCountType = replyCountType;
	}

	public Byte getDateType() {
		return dateType;
	}

	public void setDateType(Byte dateType) {
		this.dateType = dateType;
	}

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

	public String getTempletCode() {
		return templetCode;
	}

	public void setTempletCode(String templetCode) {
		this.templetCode = templetCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Byte getColumnsType() {
		return columnsType;
	}

	public void setColumnsType(Byte columnsType) {
		this.columnsType = columnsType;
	}

	public String getColumnsUrl() {
		return columnsUrl;
	}

	public void setColumnsUrl(String columnsUrl) {
		this.columnsUrl = columnsUrl;
	}

	public Byte getViewCountType() {
		return viewCountType;
	}

	public void setViewCountType(Byte viewCountType) {
		this.viewCountType = viewCountType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}