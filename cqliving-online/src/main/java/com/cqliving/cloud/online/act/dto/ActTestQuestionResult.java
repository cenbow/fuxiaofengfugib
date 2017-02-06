package com.cqliving.cloud.online.act.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月27日
 */
public class ActTestQuestionResult {
	
	/** 问题ID */
	private Long questionId;
	/** 问题名称（问题描述） */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String imageUrl;
	/** 问题类型 */
	private Byte type;
	/** 问题分值 */
	private Integer score;
	/** 问题回答是否正确 */
	private Boolean isRight;
	
	/** 答案列表 **/
	private List<ActTestAnswerResult> answerList;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<ActTestAnswerResult> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ActTestAnswerResult> answerList) {
		this.answerList = answerList;
	}

	public Boolean getIsRight() {
		return isRight;
	}

	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}
	
}
