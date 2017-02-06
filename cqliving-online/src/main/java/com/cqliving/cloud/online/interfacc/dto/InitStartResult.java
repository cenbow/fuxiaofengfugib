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

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年4月30日
 */
public class InitStartResult {
	
	/** 会话 id */
	private String sessionId;
	/** app 信息 */
	private InitStartApp app;
	/** 图片版本号 */
	private Integer imgVersion;
	/** 广告图是否已过期{true:已过期,false:未过期} */
	private Boolean isExpired;
	/** 广告图 */
	private InitStartLoadingImg loadingImg;
	/** 栏目版本号 */
	private Integer colVersionNo;
	/** 栏目集合 */
	private List<GetColumnsData> columns;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionIjd) {
		this.sessionId = sessionIjd;
	}
	public InitStartApp getApp() {
		return app;
	}
	public void setApp(InitStartApp app) {
		this.app = app;
	}
	public Integer getImgVersion() {
		return imgVersion;
	}
	public void setImgVersion(Integer imgVersion) {
		this.imgVersion = imgVersion;
	}
	public Boolean getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}
	public Integer getColVersionNo() {
		return colVersionNo;
	}
	public void setColVersionNo(Integer colVersionNo) {
		this.colVersionNo = colVersionNo;
	}
	
	public List<GetColumnsData> getColumns() {
		return columns;
	}
	public void setColumns(List<GetColumnsData> columns) {
		this.columns = columns;
	}
	public InitStartLoadingImg getLoadingImg() {
		return loadingImg;
	}
	public void setLoadingImg(InitStartLoadingImg loadingImg) {
		this.loadingImg = loadingImg;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}