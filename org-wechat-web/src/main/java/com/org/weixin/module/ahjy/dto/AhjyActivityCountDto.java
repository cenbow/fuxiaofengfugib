/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月21日
 */
public class AhjyActivityCountDto {

	//参加活动人数
	private Integer joinNum;
	//中奖人数
	private Integer awardNum;
	//领奖人数
	private Integer takeNum;
	//每日统计活动数的分组时间
	private Date beginTime;
	
	
	//中奖人
	private Long userId;
	//中奖人昵称
	private String nickname;
	//活动ID
	private Long activityId;
	//奖品code
	private String code;
	//奖品名称
	private String name;
	//领取时间
	private Date awardGainTime;
	//是否领奖
	private Byte isGetAward;
	//是否中奖
	private Byte isAward;
	
	public Integer getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}
	public Integer getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}
	public Integer getTakeNum() {
		return takeNum;
	}
	public void setTakeNum(Integer takeNum) {
		this.takeNum = takeNum;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAwardGainTime() {
		return awardGainTime;
	}
	public void setAwardGainTime(Date awardGainTime) {
		this.awardGainTime = awardGainTime;
	}
	public Byte getIsGetAward() {
		return isGetAward;
	}
	public void setIsGetAward(Byte isGetAward) {
		this.isGetAward = isGetAward;
	}
	public Byte getIsAward() {
		return isAward;
	}
	public void setIsAward(Byte isAward) {
		this.isAward = isAward;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
}
