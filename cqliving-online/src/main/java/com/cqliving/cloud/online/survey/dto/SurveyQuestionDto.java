/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.survey.dto;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月9日
 */
public class SurveyQuestionDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 调研ID */
	private Long surveyId;
	/** 问题 */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String images;
	/** 问题类型 */
	private Byte type;
	/** 数量控制,填空，文本 表示输入长度，多选,则控制选择数量, -1 表示不控制 */
	private Integer limitCount;
	/** 排序号 */
	private Integer sortNo;
	/** 创建时间 */
	private Date createTime;
	//问题答案
	private List<SurveyQuestionAnswerDto> surveyQuestionAnswers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<SurveyQuestionAnswerDto> getSurveyQuestionAnswers() {
		return surveyQuestionAnswers;
	}
	public void setSurveyQuestionAnswers(List<SurveyQuestionAnswerDto> surveyQuestionAnswers) {
		this.surveyQuestionAnswers = surveyQuestionAnswers;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionDto other = (SurveyQuestionDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
