package com.cqliving.cloud.online.interfacc.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月2日
 */
public class BusinessDetailResult {

	/** 评论量 */
	private Integer replyCount;
	/** 浏览量 */
	private String viewCount;
	/** 点赞数 */
	private Integer praiseCount;
	/** 是否收藏 */
	private Boolean isCollected;
	/** 是否点赞 */
	private Boolean isPraised;

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

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praise) {
		this.praiseCount = praise;
	}

	public Boolean getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}

	public Boolean getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Boolean isPraised) {
		this.isPraised = isPraised;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
