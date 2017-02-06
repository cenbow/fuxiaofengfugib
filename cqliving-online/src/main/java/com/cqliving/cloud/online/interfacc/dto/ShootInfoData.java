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
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月8日
 */
public class ShootInfoData {
	
	/** ID */
	private Long id;
	/** 随手拍内容 */
	private String content;
	/** 用户ID */
	private Long userId;
	/** 回复量 */
	private Integer replyCount;
	/** 点赞量 */
	private Integer priseCount;
	/** 摄影类型 */
	private Byte shootType;
	/** 创建时间 */
	private String createTime;
	/** 所处位置 */
	private String place;
	/** 用户头像 */
	private String userImg;
	/** 用户昵称 */
	private String nickname;
	/** 图片路径 */
	private String imgs;
	/** 图片描述 */
	private String descs;
	/** 是否已点赞 */
	private Byte isPraised;
	/** 状态 */
	private Byte status;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getPriseCount() {
		return priseCount;
	}

	public void setPriseCount(Integer priseCount) {
		this.priseCount = priseCount;
	}

	public Byte getShootType() {
		return shootType;
	}

	public void setShootType(Byte shootType) {
		this.shootType = shootType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickName) {
		this.nickname = nickName;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public Byte getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Byte isPraised) {
		this.isPraised = isPraised;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}