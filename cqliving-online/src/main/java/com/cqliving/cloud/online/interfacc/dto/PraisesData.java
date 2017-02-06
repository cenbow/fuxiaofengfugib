/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public class PraisesData {
	
	/** id */
	private Long id;
	/** 点赞用户 id */
	private Long userId;
	/** 点赞用户昵称 */
	private String nickName;
	/** 点赞用户头像 */
	private String sourceUserImage;
	/** 点赞时间 */
	private String praiseTime;
	/** 业务数据 id */
	private Long sourceId;
	/** 来源类型 */
	private Byte sourceType;
	/** 类型{0:业务类型,1:评论} */
	private Byte type;
	/** 评论内容 */
	private String content; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSourceUserImage() {
		return sourceUserImage;
	}

	public void setSourceUserImage(String sourceUserImage) {
		this.sourceUserImage = sourceUserImage;
	}

	public String getPraiseTime() {
		return praiseTime;
	}

	public void setPraiseTime(String praiseTime) {
		this.praiseTime = praiseTime;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}