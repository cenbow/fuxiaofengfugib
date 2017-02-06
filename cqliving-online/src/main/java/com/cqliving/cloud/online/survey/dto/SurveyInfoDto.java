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
public class SurveyInfoDto {

	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 新闻详情_ID */
	private Long informationId;
	/** 新闻内容Id */
	private Long informationContentId;
	/** 标题 */
	private String title;
	/** 类型 */
	private Byte type;
	/** 类型 */
	private Byte limitRateType;
	/** 数量 */
	private Integer limitRateTimes;
	/** 类型 */
	private Byte limitSingleType;
	/** 数量 */
	private Integer limitSingleTimes;
	/** 类型 */
	private Byte limitRuleType;
	/** 当limit_rule_type为1和0时，值默认为0但无效，当为限制值时有效 */
	private Integer limitRuleTimes;
	/** 是否登陆后才能操作 */
	private Byte loggedStatus;
	
	//问题ID
	private Long questionId;
	private Byte questionType;
	private Integer questionSortNo;
	/** 问题 */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String images;
	/** 数量控制,填空，文本 表示输入长度，多选,则控制选择数量, -1 表示不控制 */
	private Integer limitCount;
	private Date questionCreateTime;
	//答案ID
	private Long answerId;
	private Byte answerType;
	private Integer answerSortNo;
	/** 答案 */
	private String answer;
	/** 答案图片，多个用,分隔 */
	private String imageUrl;
	/** 输入长度限制 */
	private Integer inputLimit;
	/** 编号 */
	private String number;
	//实际量
	private Integer actValue;
	/** 初始量 */
	private Integer initValue;
	/** 是否正方,该字段只对打擂有用{0:否,1:是} */
	private Byte isAffirmative;
	private Date answerCreateTime;
	
	List<SurveyQuestionDto> surveyQuestionDtos;

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

	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getLimitRateType() {
		return limitRateType;
	}

	public void setLimitRateType(Byte limitRateType) {
		this.limitRateType = limitRateType;
	}

	public Integer getLimitRateTimes() {
		return limitRateTimes;
	}

	public void setLimitRateTimes(Integer limitRateTimes) {
		this.limitRateTimes = limitRateTimes;
	}

	public Byte getLimitSingleType() {
		return limitSingleType;
	}

	public void setLimitSingleType(Byte limitSingleType) {
		this.limitSingleType = limitSingleType;
	}

	public Integer getLimitSingleTimes() {
		return limitSingleTimes;
	}

	public void setLimitSingleTimes(Integer limitSingleTimes) {
		this.limitSingleTimes = limitSingleTimes;
	}

	public Byte getLimitRuleType() {
		return limitRuleType;
	}

	public void setLimitRuleType(Byte limitRuleType) {
		this.limitRuleType = limitRuleType;
	}

	public Integer getLimitRuleTimes() {
		return limitRuleTimes;
	}

	public void setLimitRuleTimes(Integer limitRuleTimes) {
		this.limitRuleTimes = limitRuleTimes;
	}

	public Byte getLoggedStatus() {
		return loggedStatus;
	}

	public void setLoggedStatus(Byte loggedStatus) {
		this.loggedStatus = loggedStatus;
	}

	public List<SurveyQuestionDto> getSurveyQuestionDtos() {
		return surveyQuestionDtos;
	}

	public void setSurveyQuestionDtos(List<SurveyQuestionDto> surveyQuestionDtos) {
		this.surveyQuestionDtos = surveyQuestionDtos;
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

	public Byte getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Byte questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionSortNo() {
		return questionSortNo;
	}

	public void setQuestionSortNo(Integer questionSortNo) {
		this.questionSortNo = questionSortNo;
	}

	public Byte getAnswerType() {
		return answerType;
	}

	public void setAnswerType(Byte answerType) {
		this.answerType = answerType;
	}

	public Integer getAnswerSortNo() {
		return answerSortNo;
	}

	public void setAnswerSortNo(Integer answerSortNo) {
		this.answerSortNo = answerSortNo;
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

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
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

	public Integer getInputLimit() {
		return inputLimit;
	}

	public void setInputLimit(Integer inputLimit) {
		this.inputLimit = inputLimit;
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

	public Byte getIsAffirmative() {
		return isAffirmative;
	}

	public void setIsAffirmative(Byte isAffirmative) {
		this.isAffirmative = isAffirmative;
	}

	public Long getInformationContentId() {
		return informationContentId;
	}

	public void setInformationContentId(Long informationContentId) {
		this.informationContentId = informationContentId;
	}

	public Date getQuestionCreateTime() {
		return questionCreateTime;
	}

	public void setQuestionCreateTime(Date questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}

	public Date getAnswerCreateTime() {
		return answerCreateTime;
	}

	public void setAnswerCreateTime(Date answerCreateTime) {
		this.answerCreateTime = answerCreateTime;
	}

	public Integer getActValue() {
		return actValue;
	}

	public void setActValue(Integer actValue) {
		this.actValue = actValue;
	}
	
}
