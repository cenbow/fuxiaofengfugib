/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
/**
 * 
 */
package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年4月30日
 */
public class InitStartApp {
	
	/** 当前版本号 */
	private Integer vesionNo;
	/** 显示版本号 */
	private String viewVersion;
	/** 最低支持版本号 */
	private Integer minVersion;
	/** 版本升级说明 */
	private String updateContext;
	/** 客户端下载URL */
	private String url;
	/** 客户端名称 */
	private String name;
	/** 发布时间yyyy-MM-dd格式 */
	private String publishTime;
	
	public Integer getVesionNo() {
		return vesionNo;
	}
	public void setVesionNo(Integer vesionNo) {
		this.vesionNo = vesionNo;
	}
	public String getViewVersion() {
		return viewVersion;
	}
	public void setViewVersion(String viewVersion) {
		this.viewVersion = viewVersion;
	}
	public Integer getMinVersion() {
		return minVersion;
	}
	public void setMinVersion(Integer minVersion) {
		this.minVersion = minVersion;
	}
	public String getUpdateContext() {
		return updateContext;
	}
	public void setUpdateContext(String updateContext) {
		this.updateContext = updateContext;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}