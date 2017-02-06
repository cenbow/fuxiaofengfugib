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
 * 用户举报表 Entity
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
@Entity
@Table(name = "user_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserReport extends AbstractEntity {

	private static final long serialVersionUID = 1L;

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
		
	/** 举报来源类型 */
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
	}
	
	/** 举报来源类型页面展示title */
	public static final Map<Byte, String> allSTypeTitle = Maps.newTreeMap();
	static {
		allSTypeTitle.put(SOURCETYPE1, "新闻");
		allSTypeTitle.put(SOURCETYPE2, "问政");
		allSTypeTitle.put(SOURCETYPE3, "商情");
		allSTypeTitle.put(SOURCETYPE4, "随手拍");
		allSTypeTitle.put(SOURCETYPE5, "段子");
		allSTypeTitle.put(SOURCETYPE6, "活动");
		allSTypeTitle.put(SOURCETYPE7, "话题");
		allSTypeTitle.put(SOURCETYPE10, "旅游");
		allSTypeTitle.put(SOURCETYPE11, "手机置业");
	}
	/** 举报不实 */
	public static final Byte STATUS_1 = -1;
	/** 待审核 */
	public static final Byte STATUS2 = 2;
	/** 举报属实 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "举报不实");
		allStatuss.put(STATUS2, "待审核");
		allStatuss.put(STATUS3, "举报属实");
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
	
	/** 业务类型 */
	public static final Byte OPERATETYPE0 = 0;
	/** 评论 */
	public static final Byte OPERATETYPE1 = 1;
	
	/** 类型 */
	public static final Map<Byte, String> allOperateTypes = Maps.newTreeMap();
	static {
		allOperateTypes.put(OPERATETYPE0, "业务类型");
		allOperateTypes.put(OPERATETYPE1, "评论");
	}
	
	/** 是否为评论举报 */
	public static final Map<Byte, String> allTypesView = Maps.newTreeMap();
	static {
	    allTypesView.put(OPERATETYPE0, "非评论");
	    allTypesView.put(OPERATETYPE1, "评论");
	}
	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 会话code */
	private String sessionCode;
	/** 评论人ID */
	private Long userId;
	/** 举报人姓名 */
	private String name;
	/** 评论内容,预留，用户前台暂时无填写 */
	private String content;
	/** 举报内容CODE,参照字典表 */
	private String reportCode;
	/** 举报来源类型 */
	private Byte sourceType;
	/** 举报来源ID */
	private Long sourceId;
	/** 状态 */
	private Byte status;
	/** 审阅状态 */
	private Byte auditingType;
	/** 创建时间 */
	private Date createTime;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间 */
	private Date auditingTime;
	/** 审核描述 */
    private String auditingContent;
    /** 类型 */
    private Byte operateType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public String getSessionCode(){
		return this.sessionCode;
	}
	
	public void setSessionCode(String sessionCode){
		this.sessionCode = sessionCode;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
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
	public String getReportCode(){
		return this.reportCode;
	}
	
	public void setReportCode(String reportCode){
		this.reportCode = reportCode;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
	}
	public Long getSourceId(){
		return this.sourceId;
	}
	
	public void setSourceId(Long sourceId){
		this.sourceId = sourceId;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public Byte getAuditingType(){
		return this.auditingType;
	}
	
	public void setAuditingType(Byte auditingType){
		this.auditingType = auditingType;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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
	
	public String getAuditingContent() {
        return auditingContent;
    }

    public void setAuditingContent(String auditingContent) {
        this.auditingContent = auditingContent;
    }

    public Byte getOperateType() {
		return operateType;
	}

	public void setOperateType(Byte operateType) {
		this.operateType = operateType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}