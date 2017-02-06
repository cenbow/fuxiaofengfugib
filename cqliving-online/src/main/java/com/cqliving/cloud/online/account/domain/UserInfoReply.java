package com.cqliving.cloud.online.account.domain;


import java.util.Map;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cqliving.framework.common.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户资讯回复表 Entity
 * Date: 2016-05-11 21:29:30
 * @author Code Generator
 */
@Entity
@Table(name = "user_info_reply")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserInfoReply extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 正常回复 */
	public static final Byte TYPE0 = 0;
	/** 匿名 */
	public static final Byte TYPE1 = 1;
	/** 游客回复 */
	public static final Byte TYPE2 = 2;
	/** 官方回复 */
	public static final Byte TYPE3 = 3;
		
	/** 类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE0, "正常回复");
		allTypes.put(TYPE1, "匿名");
		allTypes.put(TYPE2, "游客回复");
		allTypes.put(TYPE3, "官方回复");
	}
	/** 正常 */
	public static final Byte PASSIVERELPSYSTATUS3 = 3;
	/** 删除 */
	public static final Byte PASSIVERELPSYSTATUS99 = 99;
		
	/** 被回复人显示状态 */
	public static final Map<Byte, String> allPassiveRelpsyStatuss = Maps.newTreeMap();
	static {
		allPassiveRelpsyStatuss.put(PASSIVERELPSYSTATUS3, "正常");
		allPassiveRelpsyStatuss.put(PASSIVERELPSYSTATUS99, "删除");
	}
	/** 审核不通过 */
	public static final Byte STATUS_1 = -1;
	/** 待审核 */
	public static final Byte STATUS2 = 2;
	/** 正常 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "审核不通过");
		allStatuss.put(STATUS2, "待审核");
		allStatuss.put(STATUS3, "审核通过");
		allStatuss.put(STATUS99, "删除");
	}
	/** 未处理 */
	public static final Byte AUDITINGTYPE0 = 0;
	/** 已处理 */
	public static final Byte AUDITINGTYPE1 = 1;
		
	/** 审阅状态 */
	public static final Map<Byte, String> allAuditingTypes = Maps.newTreeMap();
	static {
		allAuditingTypes.put(AUDITINGTYPE0, "未处理");
		allAuditingTypes.put(AUDITINGTYPE1, "已处理");
	}
	/** 新闻 */
	public static final Byte SOURCETYPE1 = 1;
	/** 问政 */
	public static final Byte SOURCETYPE2 = 2;
	/** 商情 */
	public static final Byte SOURCETYPE3 = 3;
	/** 随手拍 */
	public static final Byte SOURCETYPE4 = 4;
	/** 段子 */
	public static final Byte SOURCETYPE5 = 5;
	/** 活动 */
	public static final Byte SOURCETYPE6 = 6;
	/** 话题 */
	public static final Byte SOURCETYPE7 = 7;
	/** 旅游 */
	public static final Byte SOURCETYPE10 = 10;
	/** 手机置业 */
	public static final Byte SOURCETYPE11 = 11;
	/** 商城 */
	public static final Byte SOURCETYPE13 = 13;
		
	/** 回复源类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCETYPE1, "新闻");
		allSourceTypes.put(SOURCETYPE2, "问政");
		allSourceTypes.put(SOURCETYPE3, "商情");
		allSourceTypes.put(SOURCETYPE4, "随手拍");
		allSourceTypes.put(SOURCETYPE5, "段子");
		allSourceTypes.put(SOURCETYPE6, "活动");
		allSourceTypes.put(SOURCETYPE7, "话题");
		allSourceTypes.put(SOURCETYPE10, "旅游");
		allSourceTypes.put(SOURCETYPE11, "手机置业");
		allSourceTypes.put(SOURCETYPE13, "商城");
	}
	
	/** ID */
	private Long id;
	/** 会话code */
	private String sessionCode;
	/** 评论人ID */
	private Long replyUserId;
	/** 评论人姓名 */
	private String name;
	/** 评论内容 */
	private String content;
	/** 评论图片 */
	private String imageUrl;
	/** 评论位置 */
	private String place;
	/** 所处位置经度 */
	private String lng;
	/** 所处位置纬度 */
	private String lat;
	/** source_type=1时，对应对应information表的主键。source_type=2时，对应wz_question表的主键 */
	private Long sourceId;
	/** APPID，评论用户使用APP_id */
	private Long appId;
	/** 类型 */
	private Byte type;
	/** 被回复人名称 */
	private String passiveReplyName;
	/** 被回复人ID */
	private Long passiveReplyId;
	/** 被回复人显示状态 */
	private Byte passiveRelpsyStatus;
	/** 被回复人的评论ID（引用的是该表的ID） */
	private Long refInfoReplyId;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
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
	/** 审核描述 */
	private String auditingContent;
	/** 审核时间 */
	private Date auditingTime;
	/** 评论点赞数 */
	private Integer praise;
	/** 新闻资讯ID，对应info_classify表的主键 */
	private Long infoClassifyId;
	/** 回复源类型 */
	private Byte sourceType;
	/** 回复数 */
	private Integer replyCount;
	/** 回复源标题（内容） */
	private String sourceTitle;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	public Long getSourceId(){
		return this.sourceId;
	}
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
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
	
	public String getAuditingContent() {
        return auditingContent;
    }

    public void setAuditingContent(String auditingContent) {
        this.auditingContent = auditingContent;
    }

    public void setAuditingTime(Date auditingTime){
		this.auditingTime = auditingTime;
	}
	public Integer getPraise(){
		return this.praise;
	}
	
	public void setPraise(Integer praise){
		this.praise = praise;
	}
	public Long getInfoClassifyId(){
		return this.infoClassifyId;
	}
	
	public void setInfoClassifyId(Long infoClassifyId){
		this.infoClassifyId = infoClassifyId;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	
	public Long getRefInfoReplyId() {
		return refInfoReplyId;
	}

	public void setRefInfoReplyId(Long refInfoReplyId) {
		this.refInfoReplyId = refInfoReplyId;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
