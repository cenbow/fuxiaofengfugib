/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public class UserTicketDto {

	//用户抽奖时填写的手机号
	String phone;
	/** 音乐会名称 */
	private String ticketName;
	/** 音乐会图片 */
	private String imageUrl;
	/** 演出时间段 */
	private String duration;
	//用户中奖时间
	Date grabTime;
	//是否已兑奖
	Byte takeStatus;
	//核销码
	String verifyCode;
	//音乐会门票ID
	Long musicaleId;
	
	/**   统计字段    */
	
	//以下为人次字段
	private Integer awardNum;
	private Integer notAwardNum;
	private Integer verifyNum;
	//以下为人数字段
	private Integer awardUserNum;
	private Integer notAwardUserNum;
	private Integer verifyUserNum;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getGrabTime() {
		return grabTime;
	}
	public void setGrabTime(Date grabTime) {
		this.grabTime = grabTime;
	}
	public Byte getTakeStatus() {
		return takeStatus;
	}
	public void setTakeStatus(Byte takeStatus) {
		this.takeStatus = takeStatus;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Long getMusicaleId() {
		return musicaleId;
	}
	public void setMusicaleId(Long musicaleId) {
		this.musicaleId = musicaleId;
	}
	public Integer getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}
	public Integer getNotAwardNum() {
		return notAwardNum;
	}
	public void setNotAwardNum(Integer notAwardNum) {
		this.notAwardNum = notAwardNum;
	}
	public Integer getVerifyNum() {
		return verifyNum;
	}
	public void setVerifyNum(Integer verifyNum) {
		this.verifyNum = verifyNum;
	}
	public Integer getAwardUserNum() {
		return awardUserNum;
	}
	public void setAwardUserNum(Integer awardUserNum) {
		this.awardUserNum = awardUserNum;
	}
	public Integer getNotAwardUserNum() {
		return notAwardUserNum;
	}
	public void setNotAwardUserNum(Integer notAwardUserNum) {
		this.notAwardUserNum = notAwardUserNum;
	}
	public Integer getVerifyUserNum() {
		return verifyUserNum;
	}
	public void setVerifyUserNum(Integer verifyUserNum) {
		this.verifyUserNum = verifyUserNum;
	}
}
