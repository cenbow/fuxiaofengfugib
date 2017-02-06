/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月28日
 */
public class UserSurveyHistoryDto {
	
	/** ID */
	private Long id;
	/** 会话code */
	private String sessionCode;
	/** 客户端_ID */
	private Long appId;
	/** 调研ID */
	private Long surveyId;
	/** 参与时间 */
	private Date createTime;
	/** 参与时间 */
	private Long userId;
	/** 联系方式 */
	private String phone;
	/** IP */
	private String ip;
	/** 来源 */
	private String source;
	
	
	//---------------扩展字段---------------
	
	private String token;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSessionCode() {
		return sessionCode;
	}


	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}


	public Long getAppId() {
		return appId;
	}


	public void setAppId(Long appId) {
		this.appId = appId;
	}


	public Long getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
