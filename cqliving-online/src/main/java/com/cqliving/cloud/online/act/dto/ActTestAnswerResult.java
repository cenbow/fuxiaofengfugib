package com.cqliving.cloud.online.act.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月27日
 */
public class ActTestAnswerResult {

	/** 答案ID */
	private Long id;
	/** 答案 */
	private String answer;
	/** 答案描述 */
	private String answerDesc;
	/** 答案图片，多个用,分隔 */
	private String imageUrl;
	/** 答案类型 */
	private Byte type;
	/** 是否正确答案 */
	private Byte isRightAnswer;
	/** 用户的答案 */
	private Boolean userAnswer;
	/** 用户输入的答案内容 **/
	private String answerContent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Byte getIsRightAnswer() {
		return isRightAnswer;
	}
	public void setIsRightAnswer(Byte isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
	public Boolean getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(Boolean userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
}
