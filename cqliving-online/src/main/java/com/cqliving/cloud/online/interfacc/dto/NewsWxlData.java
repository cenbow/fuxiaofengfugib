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

import com.cqliving.cloud.common.constant.ClientControlType;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public class NewsWxlData {
	
	/** 新闻 id */
	private Long id;
	/** 新闻详情 id */
	private Long informationId;
	/** 排序号 */
	private Integer sortNo;
	/** 列表显示类型{0:无图,1:单图,2:大图,3:多图,4:轮播} */
	private Byte listViewType;
	/** 资讯标题 */
	private String title;
	/** 资讯详情标题 */
	private String detailTitle;
	/** 内容URL */
	private String contentUrl;
	/** 来源网站，文字 */
	private String infoSource;
	/** 上线时间yyyy-MM-dd格式 */
	private String onlineDate;
	/** 上线时间yyyy-MM-dd HH:mm:ss格式 */
	private String onlineTime;
	/** 评论数 */
	private Integer replyCount;
	/** 浏览量 */
	private String viewCount;
	/** 类型{0:普通新闻,2:主题新闻} */
	private Byte type;
	/** 新闻内容类型{0:纯文本,1:多图,2:原创,3:外链,4:音频,5:视频} */
	private Byte contextType;
	/** 第一个标签 */
	private String infoLabel;
	/** 图片 */
	private String[] images;
	/** 允许评论 */
	private Byte commentType;
	/** 跳转的Url */
	private String url;
	/** 分享的Url */
	private String shareUrl;
	/** 分享的标题 */
	private String shareTitle;
	/** 详情展示类型{1:多图新闻,2:普通新闻,3:专题新闻} */
	private Byte detailViewType;
	/** 业务类型{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题} */
	private Byte sourceType;
	/** 多图新闻图片数量 */
	private Integer multipleImgCount;
	/** 摘要 */
	private String synopsis;

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

	public Byte getListViewType() {
		return listViewType;
	}

	public void setListViewType(Byte listViewType) {
		this.listViewType = listViewType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public String getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(String tags) {
		this.infoLabel = tags;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}

	public Byte getContextType() {
		return contextType;
	}

	public void setContextType(Byte contextType) {
		this.contextType = contextType;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public Byte getDetailViewType() {
		return detailViewType;
	}

	/**
	 * <p>Description: 详情展示类型</p>
	 * @author Tangtao on 2016年7月18日
	 * @param detailViewType
	 * @see ClientControlType
	 */
	public void setDetailViewType(Byte detailViewType) {
		this.detailViewType = detailViewType;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getMultipleImgCount() {
		return multipleImgCount;
	}

	public void setMultipleImgCount(Integer multipleImgCount) {
		this.multipleImgCount = multipleImgCount;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}