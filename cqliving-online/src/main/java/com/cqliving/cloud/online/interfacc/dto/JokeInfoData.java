package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
public class JokeInfoData {

	/** ID */
	private Long id;
	/** 内容 */
	private String content;
	/** 点赞量 */
	private Integer praiseCount;
	/** 评论量 */
	private Integer replyCount;
	/** 点踩量 */
	private Integer despiseCount;
	/** 上线时间 */
	private String onlineTime;
	/** 是否已点赞 */
	private Byte isPraised;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Byte getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Byte isPraised) {
		this.isPraised = isPraised;
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

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getDespiseCount() {
		return despiseCount;
	}

	public void setDespiseCount(Integer despiseCount) {
		this.despiseCount = despiseCount;
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