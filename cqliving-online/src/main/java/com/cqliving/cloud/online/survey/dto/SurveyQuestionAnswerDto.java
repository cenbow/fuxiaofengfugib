/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.survey.dto;

import java.util.Date;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月9日
 */
public class SurveyQuestionAnswerDto {

	/** ID */
	private Long id;
	/** 问题ID */
	private Long questionId;
	/** 答案 */
	private String answer;
	/** 答案图片，多个用,分隔 */
	private String imageUrl;
	/** 答案类型 */
	private Byte type;
	/** 输入长度限制 */
	private Integer inputLimit;
	/** 排序号 */
	private Integer sortNo;
	/** 编号 */
	private String number;
	/** 初始量 */
	private Integer initValue;
	/** 实际数 */
	private Integer actValue;
	/** 创建时间 */
	private Date createTime;
	/** 是否正方,该字段只对打擂有用{0:否,1:是} */
	private Byte isAffirmative;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getInputLimit() {
		return inputLimit;
	}

	public void setInputLimit(Integer inputLimit) {
		this.inputLimit = inputLimit;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getInitValue() {
		return initValue;
	}

	public void setInitValue(Integer initValue) {
		this.initValue = initValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getIsAffirmative() {
		return isAffirmative;
	}

	public void setIsAffirmative(Byte isAffirmative) {
		this.isAffirmative = isAffirmative;
	}
    
	public Integer getActValue() {
		return actValue;
	}

	public void setActValue(Integer actValue) {
		this.actValue = actValue;
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
		SurveyQuestionAnswerDto other = (SurveyQuestionAnswerDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
