package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月26日
 */
public class RecommendAppData {

	/** ID */
	private Long id;
	/** 推荐客户端ID,APP_INFO表ID */
	private Long appId;
	/** 推荐客户名称 */
	private String appName;
	/** 推荐客户端的栏目表ID,app_columns表的ID */
	private Long columnsId;
	/** 推荐客户端图标 */
	private String appIcon;
	/** 下载地址 */
	private String downloadUrl;
	/** 是否获取轮播新闻数据 */
	private Boolean isCarousel;
	/** 排序号 */
	private Integer sortNo;
	
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getColumnsId() {
		return columnsId;
	}

	public void setColumnsId(Long columnsId) {
		this.columnsId = columnsId;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Boolean getIsCarousel() {
		return isCarousel;
	}

	public void setIsCarousel(Boolean isCarousel) {
		this.isCarousel = isCarousel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
