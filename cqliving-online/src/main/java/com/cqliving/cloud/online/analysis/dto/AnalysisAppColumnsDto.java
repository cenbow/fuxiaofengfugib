/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.analysis.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月14日
 */
public class AnalysisAppColumnsDto {

	/** ID */
	private Long id;
	/** app_info表ID */
	private Long appId;
	/** 统计日期 */
	private Date statisticsDate;
	/** 小时数，24小时制 */
	private Integer day;
	/** 资讯栏目表ID，表app_columns的主键 */
	private Long appColumnsId;
	/** 用户类型 */
	private Byte userType;
	/** 浏览量 */
	private Integer viewNum;
	/** 评论数 */
	private Integer commentNum;
	/** 收藏数 */
	private Integer favoriteNum;
	/** 分享数 */
	private Integer shareNum;
	//客户端名称
	private String appName;
	//栏目名称
	private String appColumnsName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Date getStatisticsDate() {
		return statisticsDate;
	}
	public void setStatisticsDate(Date statisticsDate) {
		this.statisticsDate = statisticsDate;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Long getAppColumnsId() {
		return appColumnsId;
	}
	public void setAppColumnsId(Long appColumnsId) {
		this.appColumnsId = appColumnsId;
	}
	public Byte getUserType() {
		return userType;
	}
	public void setUserType(Byte userType) {
		this.userType = userType;
	}
	public Integer getViewNum() {
		return viewNum;
	}
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getFavoriteNum() {
		return favoriteNum;
	}
	public void setFavoriteNum(Integer favoriteNum) {
		this.favoriteNum = favoriteNum;
	}
	public Integer getShareNum() {
		return shareNum;
	}
	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppColumnsName() {
		return appColumnsName;
	}
	public void setAppColumnsName(String appColumnsName) {
		this.appColumnsName = appColumnsName;
	}
	
}
