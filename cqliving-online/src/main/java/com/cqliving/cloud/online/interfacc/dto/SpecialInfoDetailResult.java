package com.cqliving.cloud.online.interfacc.dto;


import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月6日
 */
public class SpecialInfoDetailResult {
	
	/** 资讯 id */
	private Long infoClassifyId;
	/** 专题图片地址 */
	private String contentUrl;
	/** 简介 */
	private String synopsis;
	/** 专题分享地址 */
	private String shareUrl;
	/** 专题分享标题 */
	private String shareTitle;
	/** 专题分类 */
	private List<InfoThemeData> themes;
	/** 专题子新闻第一页数据 */
	private CommonListResult<NewsData> firstPageData;
	private Long appId;
	private Long id;
	private Byte status;
	private String listViewImg;
	private String title;
	public List<InfoThemeData> getThemes() {
		return themes;
	}

	public void setThemes(List<InfoThemeData> themes) {
		this.themes = themes;
	}

	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public CommonListResult<NewsData> getFirstPageData() {
		return firstPageData;
	}

	public void setFirstPageData(CommonListResult<NewsData> firstPageData) {
		this.firstPageData = firstPageData;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getListViewImg() {
		return listViewImg;
	}

	public void setListViewImg(String listViewImg) {
		this.listViewImg = listViewImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
