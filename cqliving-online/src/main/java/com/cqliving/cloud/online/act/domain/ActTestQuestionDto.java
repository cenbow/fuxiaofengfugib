package com.cqliving.cloud.online.act.domain;

public class ActTestQuestionDto {

	/** 问题ID */
	private Long id;
	/** 活动答题分类表ID（act_test_classify表主键） */
	private Long actTestClassifyId;
	/** 问题名称（问题描述） */
	private String question;
	/** 问题图片,多张用,分隔 */
	private String imageUrl;
	/** 问题类型 */
	private Byte type;
	/** 问题分值 */
	private Integer score;
	
	/** 答案ID */
	private Long answerId;
	/** 答案 */
	private String answer;
	/** 答案描述 */
	private String answerDesc;
	/** 答案图片，多个用,分隔 */
	private String answerImageUrl;
	/** 答案类型 */
	private Byte answerType;
	/** 是否正确答案 */
	private Byte isRightAnswer;
	/** 用户答案id 对应user_act_test表的id，为了区分用户是否答正确 **/
	private Long userAnswerId;
	/** 用户输入的答案内容 **/
	private String answerContent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActTestClassifyId() {
		return actTestClassifyId;
	}
	public void setActTestClassifyId(Long actTestClassifyId) {
		this.actTestClassifyId = actTestClassifyId;
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
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
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
	public String getAnswerImageUrl() {
		return answerImageUrl;
	}
	public void setAnswerImageUrl(String answerImageUrl) {
		this.answerImageUrl = answerImageUrl;
	}
	public Byte getAnswerType() {
		return answerType;
	}
	public void setAnswerType(Byte answerType) {
		this.answerType = answerType;
	}
	public Byte getIsRightAnswer() {
		return isRightAnswer;
	}
	public void setIsRightAnswer(Byte isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
	public Long getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(Long userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	
}
