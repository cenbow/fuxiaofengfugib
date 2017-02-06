package com.cqliving.cloud.online.act.dto;

/**
 * Title:问题excel导入
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月17日
 */
public class ActTestQuestionExcelOption {

	private String question;
	private Byte questionType;
	private Integer score;
	
	private String answer;
	private String answerDesc;
	private Byte isRightAnswer;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Byte getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Byte questionType) {
		this.questionType = questionType;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	public Byte getIsRightAnswer() {
		return isRightAnswer;
	}
	public void setIsRightAnswer(Byte isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
}
