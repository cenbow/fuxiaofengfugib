package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月26日
 */
public class TopicInfoData {

	/** ID */
	private Long id;
	/** 话题名称 */
	private String name;
	/** 话题列表图片，取用户上传的第一张图，没有取默认图 */
	private String listImageUrl;
	/** 置顶图片 */
	private String topImageUrl;
	/** 推荐到首页图片 */
	private String recommendImageUrl;
	/** 评论量 */
	private Integer replyCount;
	/** 创建时间 yyyy-MM-dd HH:mm:ss */
	private String createTime;
	/** 创建时间 yyyy.MM.dd */
	private String createTimeStr;
	/** 创建人名称 */
	private String creator;
	/** 跳转的Url */
	private String url;
	/** 分享的Url */
	private String shareUrl;
	/** 详情展示类型{1:多图新闻,2:普通新闻,3:专题新闻,4:随手拍,5:段子,7:话题} */
	private Byte detailViewType;
	/** 业务类型{1:新闻,2:问政,3:商情,4:随手拍,5:段子,6:活动,7:话题} */
	private Byte sourceType;
	/** 用于分享的标题 */
	private String title;
	/** 摘要 */
	private String synopsis;
	/** 是否允许评论{0:允许,1:不允许} */
	private Byte commentType;

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

	public String getListImageUrl() {
		return listImageUrl;
	}

	public void setListImageUrl(String listImageUrl) {
		this.listImageUrl = listImageUrl;
	}

	public String getTopImageUrl() {
		return topImageUrl;
	}

	public void setTopImageUrl(String topImageUrl) {
		this.topImageUrl = topImageUrl;
	}

	public String getRecommendImageUrl() {
		return recommendImageUrl;
	}

	public void setRecommendImageUrl(String recommendImageUrl) {
		this.recommendImageUrl = recommendImageUrl;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Byte getCommentType() {
		return commentType;
	}

	public void setCommentType(Byte commentType) {
		this.commentType = commentType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}