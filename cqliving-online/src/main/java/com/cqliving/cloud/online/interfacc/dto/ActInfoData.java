package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
public class ActInfoData {

	/** ID */
	private Long id;
	/** 前端展示类型 */
	private Byte showType;
	/** 前端展示类型字符串 */
	private String showTypeStr;
	/** 外链地址 */
	private String linkUrl;
	/** 活动开始时间 */
	private String startTime;
	/** 活动开始时间 */
	private String startTimestamp;
	/** 活动结束时间 */
	private String endTime;
	/** 标题 */
	private String title;
	/** 排序号 */
	private Integer sortNo;
	/** 列表图片地址 */
	private String imageUrl;
	/** 推荐到首页图片展示的图片地址 */
	private String recommendImageUrl;
	/** 活动是否过期 */
	private boolean overdue;
	/** 跳转的Url */
	private String url;
	/** 分享的Url */
	private String shareUrl;
	/** 详情展示类型{1:多图新闻,2:普通新闻,3:专题新闻,4:随手拍,5:段子,7:话题} */
	private Byte detailViewType;
	/** 业务类型{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题} */
	private Byte sourceType;
	/** 摘要 */
	private String synopsis;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getShowType() {
		return showType;
	}

	public void setShowType(Byte type) {
		this.showType = type;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getShowTypeStr() {
		return showTypeStr;
	}

	public void setShowTypeStr(String typeStr) {
		this.showTypeStr = typeStr;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRecommendImageUrl() {
		return recommendImageUrl;
	}

	public void setRecommendImageUrl(String recommendImageUrl) {
		this.recommendImageUrl = recommendImageUrl;
	}

	public boolean isOverdue() {
		return overdue;
	}

	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
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

	public void setDetailViewType(Byte detailViewType) {
		this.detailViewType = detailViewType;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}