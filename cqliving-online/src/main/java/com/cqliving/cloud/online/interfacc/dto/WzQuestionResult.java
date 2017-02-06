package com.cqliving.cloud.online.interfacc.dto;

public class WzQuestionResult {
 
	/** 问题ID **/
	private Long questionId;
	/** 标题 **/
	private String title;
	/** 内容 **/
	private String content;
	/** 提问人 **/
	private String creator;
	/** 审核时间（即受理时间） **/
	private String auditingTime;
	/** 受理部门，用于在前台展示 **/
	private String auditingDepartment;
	/** 回复时间 **/
	private String replyTime;
	/** 回复内容 **/
	private String replyContent;
	/** 问政图片 , 多个用逗号“,”隔开 **/
    private String images;
    /** 事件进度, 多个用逗号“,”隔开,事件名称和日期用“_”隔开  **/
    private String eventProgress;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getAuditingTime() {
		return auditingTime;
	}
	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
	}
	public String getAuditingDepartment() {
		return auditingDepartment;
	}
	public void setAuditingDepartment(String auditingDepartment) {
		this.auditingDepartment = auditingDepartment;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getEventProgress() {
		return eventProgress;
	}
	public void setEventProgress(String eventProgress) {
		this.eventProgress = eventProgress;
	}
    
}
