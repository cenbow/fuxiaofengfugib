/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月28日
 */
public class UserSurveyQuestionAnswerDto {

	/** ID */
	private Long id;
	/** 参与历史ID */
	private Long historyId;
	/** 客户端_ID */
	private Long appId;
	/** 问题ID */
	private Long questionId;
	/** 答案ID */
	private Long answerId;
	/** 答案内容 */
	private String content;
	/** 答案附加内容 */
	private String answerAttach;
	
	//------------ext----------
	//总数
	private Integer total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswerAttach() {
		return answerAttach;
	}

	public void setAnswerAttach(String answerAttach) {
		this.answerAttach = answerAttach;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
