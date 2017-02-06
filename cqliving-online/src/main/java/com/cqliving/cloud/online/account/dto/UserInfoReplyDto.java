package com.cqliving.cloud.online.account.dto;


import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.cqliving.tool.common.util.file.Export;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public class UserInfoReplyDto {

	/** ID */
	private Long id;
	/** 会话code */
	private String sessionCode;
	/** 评论人ID */
	private Long replyUserId;
	/** 评论人姓名 */
	private String name;
	/** 评论内容 */
	@Export(name = "评论内容", order = 1)
	private String content;
	/** 评论图片 */
	private String imageUrl;
	/** 评论位置 */
	private String place;
	/** 最后经纬度 */
	private String position;
	/** source_type=1时，对应对应information表的主键。source_type=2时，对应wz_question表的主键 */
	private Long sourceId;
	/** APPID，评论用户使用APP_id */
	private Long appId;
	/** 类型 */
	@Export(name = "类型", json = "{0:正常回复,1:匿名,2:游客回复,3:官方回复}", order = 2)
	private Byte type;
	/** 被回复人名称 */
	private String passiveReplyName;
	/** 被回复人ID */
	private Long passiveReplyId;
	/** 被回复人显示状态 */
	private Byte passiveRelpsyStatus;
	/** 状态 */
	@Export(name = "状态", json = "{-1:审核不通过,2:待审核,3:正常}", order = 6)
	private Byte status;
	/** 创建时间 */
	@Export(name = "评论时间", pattern = "yyyy-MM-dd hh:mm:ss", order = 5)
	private Date createTime;
	/** 用户更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人姓名 */
	private String updator;
	/** 审阅状态 */
	private Byte auditingType;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	@Export(name = "审核时间", pattern = "yyyy-MM-dd hh:mm:ss", order = 4)
	private Date auditingTime;
	/** 评论点赞数 */
	private Integer praise;
	/** 新闻资讯ID，对应info_classify表的主键 */
	private Long infoClassifyId; 
	/** 回复源类型 */
	private Byte sourceType;
	/** 审核描述 */
	private String auditingContent;
	/** 被回复人的评论ID（引用的是该表的ID） **/
	private Long refInfoReplyId;
	/** 回复数 */
	private Integer replyCount;
	/** 评论人姓名 */
	@Export(name = "评论用户", order = 3)
	private String replyUser;
	/** 回复源标题（内容） */
	private String sourceTitle;
	
	//------------------------ 冗余字段 --------------------------
	/** 用户头像 */
	private String imgUrl;
	/** 资讯标题 */
	@Export(name = "标题", order = 0)
	private String title;
	/** 状态（用于排序） */
	private Byte statusNew;
	/** 是否已点赞 */
	private Integer isPraised;
	/** 被评论内容 */
	private String passiveReplyContent;
	/** 评论人昵称 */
	private String nickname;
	/** 匿名昵称 */
	private String anonymousName;
	/** 创建时间 */
	private String createTimeStr;
	/** 新闻类型 */
    private Byte newsType;
    /** 用户注册电话号码  */
    private String telephone;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public Long getReplyUserId(){
		return this.replyUserId;
	}
	
	public void setReplyUserId(Long replyUserId){
		this.replyUserId = replyUserId;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public String getPassiveReplyName(){
		return this.passiveReplyName;
	}
	
	public void setPassiveReplyName(String passiveReplyName){
		this.passiveReplyName = passiveReplyName;
	}
	public Long getPassiveReplyId(){
		return this.passiveReplyId;
	}
	
	public void setPassiveReplyId(Long passiveReplyId){
		this.passiveReplyId = passiveReplyId;
	}
	public Byte getPassiveRelpsyStatus(){
		return this.passiveRelpsyStatus;
	}
	
	public void setPassiveRelpsyStatus(Byte passiveRelpsyStatus){
		this.passiveRelpsyStatus = passiveRelpsyStatus;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdatorId(){
		return this.updatorId;
	}
	
	public void setUpdatorId(Long updatorId){
		this.updatorId = updatorId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	public Byte getAuditingType(){
		return this.auditingType;
	}
	
	public void setAuditingType(Byte auditingType){
		this.auditingType = auditingType;
	}
	public Long getAuditingId(){
		return this.auditingId;
	}
	
	public void setAuditingId(Long auditingId){
		this.auditingId = auditingId;
	}
	public String getAuditingtor(){
		return this.auditingtor;
	}
	
	public void setAuditingtor(String auditingtor){
		this.auditingtor = auditingtor;
	}
	public Date getAuditingTime(){
		return this.auditingTime;
	}
	
	public void setAuditingTime(Date auditingTime){
		this.auditingTime = auditingTime;
	}
	
	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Long getInfoClassifyId() {
		return infoClassifyId;
	}

	public void setInfoClassifyId(Long infoClassifyId) {
		this.infoClassifyId = infoClassifyId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String headerImg) {
		this.imgUrl = headerImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public String getAuditingContent() {
        return auditingContent;
    }

    public void setAuditingContent(String auditingContent) {
        this.auditingContent = auditingContent;
    }

    public Byte getStatusNew() {
        return statusNew;
    }

    public void setStatusNew(Byte statusNew) {
        this.statusNew = statusNew;
    }

    public Long getRefInfoReplyId() {
        return refInfoReplyId;
    }

    public void setRefInfoReplyId(Long refInfoReplyId) {
        this.refInfoReplyId = refInfoReplyId;
    }

    public Integer getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Integer isPraised) {
		this.isPraised = isPraised;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getPassiveReplyContent() {
		return passiveReplyContent;
	}

	public void setPassiveReplyContent(String passiveReplyContent) {
		this.passiveReplyContent = passiveReplyContent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAnonymousName() {
		return anonymousName;
	}

	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}

	public String getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(String replyUser) {
        this.replyUser = replyUser;
    }

    public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Byte getNewsType() {
        return newsType;
    }

    public void setNewsType(Byte newsType) {
        this.newsType = newsType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}