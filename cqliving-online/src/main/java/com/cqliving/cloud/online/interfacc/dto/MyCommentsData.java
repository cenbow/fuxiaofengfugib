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
 * @author Tangtao on 2016年5月2日
 */
public class MyCommentsData {
	
	/** 评论 id */
	private Long replyId;
	/** 评论用户 id */
	private Long userId;
	/** 用户昵称 */
	private String userName;
	/** 用户头像地址 */
	private String headerImg;
	/** 被回复人名称 */
	private String passiveReplyName;
	/** 被评论内容 */
	private String passiveReplyContent;
	/** 评论内容 */
	private String content;
	/** 评论时间 */
	private String commentTime;
	/** 点赞数 */
	private Integer praiseCount;
	/** 回复数 */
	private Integer replyCount;
	/** 回复源 id */
	private Long sourceId;
	/** 回复源标题（内容） */
	private String sourceTitle;
	/** 回复源类型 */
	private Byte sourceType;
	
	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String conmmentTime) {
		this.commentTime = conmmentTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassiveReplyName() {
		return passiveReplyName;
	}

	public void setPassiveReplyName(String passiveReplyName) {
		this.passiveReplyName = passiveReplyName;
	}

	public String getPassiveReplyContent() {
		return passiveReplyContent;
	}

	public void setPassiveReplyContent(String passiveReplyContent) {
		this.passiveReplyContent = passiveReplyContent;
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

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}