/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月6日
 */
public class SzcUserAwardDto {

	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 领奖手机号 */
	private String phone;
	/** 奖品CODE */
	private String awardCode;
	/** 领取状态 */
	private Byte takeStatus;
	/** 兑换码 */
	private String convertCode;
	/** 中奖时间 */
	private Date awardTime;
	/** 核销时间 */
	private Date verifyTime;
	/** 奖券名称 */
	private String awardName;
	/** 奖券图片 */
    private String awardImg;	
    /** 中奖区域  */
    private Integer district;
    
    /**  冗余字段  */
    /** 奖品实际剩余量  */
    private Integer surplusNum;
    /** 奖品中奖数量  */
    private Integer awardNum;
    /** 奖品实际核销数量  */
    private Integer verifyNum;
    /** 奖品实际数量  */
    private Integer actualNum;
    /** 奖品剩余虚拟数量  */
    private Integer virtualNum;
    /** 中奖人数  */
    private Integer userNum;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAwardCode() {
		return awardCode;
	}
	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}
	public Byte getTakeStatus() {
		return takeStatus;
	}
	public void setTakeStatus(Byte takeStatus) {
		this.takeStatus = takeStatus;
	}
	public String getConvertCode() {
		return convertCode;
	}
	public void setConvertCode(String convertCode) {
		this.convertCode = convertCode;
	}
	public Date getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getAwardImg() {
		return awardImg;
	}
	public void setAwardImg(String awardImg) {
		this.awardImg = awardImg;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public Integer getSurplusNum() {
		return surplusNum;
	}
	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}
	public Integer getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(Integer awardNum) {
		this.awardNum = awardNum;
	}
	public Integer getVerifyNum() {
		return verifyNum;
	}
	public void setVerifyNum(Integer verifyNum) {
		this.verifyNum = verifyNum;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public Integer getVirtualNum() {
		return virtualNum;
	}
	public void setVirtualNum(Integer virtualNum) {
		this.virtualNum = virtualNum;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	
}
