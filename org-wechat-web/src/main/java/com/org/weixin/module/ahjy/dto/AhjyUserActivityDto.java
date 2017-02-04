package com.org.weixin.module.ahjy.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title: 金源活动用户表 Dto
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年3月27日
 */
public class AhjyUserActivityDto {

	/** 用户ID */
	private Long userId;
	/** 用户头像 */
	private String userImg;
	/** 加入时间 */
	private Date joinTime;
	/** 是否是楼主{0:不是,1:是} */
	private Byte isHost;
	/** 活动得分 */
	private Integer score;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public byte getIsHost() {
		return isHost;
	}

	public void setIsHost(Byte isHost) {
		this.isHost = isHost;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
