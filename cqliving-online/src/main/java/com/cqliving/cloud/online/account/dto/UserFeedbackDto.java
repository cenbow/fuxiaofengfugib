package com.cqliving.cloud.online.account.dto;


import java.util.Date;

/**
 * 意见反馈表Dto
 * Date: 2016-05-28 14:26:25
 * @author Code Generator
 */
public class UserFeedbackDto {

	/** ID */
	private Long id;
	/** 来源ID,用户来源的区县ID */
	private Long appId;
	/** 用户名称 */
	private String name;
	/** 会话code */
	private String sessionCode;
	/** 用户ID */
	private Long userId;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 图片 */
	private String imageUrl;
	/** 回复内容 */
	private String replyContent;
	/** 回复时间 */
	private Date replyTime;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	
	/** 手机号 */
    private String telephone;
    /** 区县客户端名称 */
    private String appName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAuditingId() {
        return auditingId;
    }

    public void setAuditingId(Long auditingId) {
        this.auditingId = auditingId;
    }

    public String getAuditingtor() {
        return auditingtor;
    }

    public void setAuditingtor(String auditingtor) {
        this.auditingtor = auditingtor;
    }

    public Date getAuditingTime() {
        return auditingTime;
    }

    public void setAuditingTime(Date auditingTime) {
        this.auditingTime = auditingTime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}