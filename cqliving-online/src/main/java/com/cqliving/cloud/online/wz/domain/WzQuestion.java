package com.cqliving.cloud.online.wz.domain;


import java.util.Date;
import java.util.Map;

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
 * 问政问题表 Entity
 * Date: 2016-05-09 16:59:11
 * @author Code Generator
 */
/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月18日
 */
@Entity
@Table(name = "wz_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WzQuestion extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 建言献策 */
	public static final Byte TYPE1 = 1;
	/** 投诉举报 */
	public static final Byte TYPE2 = 2;
	/** 咨询求助 */
	public static final Byte TYPE3 = 3;
	/** 其他 */
	public static final Byte TYPE4 = 4;
		
	/** 事件类型 */
	public static final Map<Byte, String> allTypes = Maps.newTreeMap();
	static {
		allTypes.put(TYPE1, "建言献策");
		allTypes.put(TYPE2, "投诉举报");
		allTypes.put(TYPE3, "咨询求助");
		allTypes.put(TYPE4, "其他");
	}
	/** 审核不通过 */
	public static final Byte STATUS_1 = -1;
	/** 未审核 */
	public static final Byte STATUS2 = 2;
	/** 审核通过待处理 */
	public static final Byte STATUS3 = 3;
	/** 转交中 */
	public static final Byte STATUS4 = 4;
	/** 已处理待发布（子帐号已处理） */
	public static final Byte STATUS5 = 5;
	/** 已驳回 */
	public static final Byte STATUS6 = 6;
	/** 已发布 */
	public static final Byte STATUS7 = 7;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "审核不通过");
		allStatuss.put(STATUS2, "未审核");
		allStatuss.put(STATUS3, "审核通过待处理");
		allStatuss.put(STATUS4, "转交中");
		allStatuss.put(STATUS5, "已处理待发布");
		allStatuss.put(STATUS6, "已驳回");
		allStatuss.put(STATUS7, "已发布");
        allStatuss.put(STATUS88, "已下线");
        allStatuss.put(STATUS99, "删除");
	}
	/** H5显示状态 */
	public static final Map<Byte, String> allStatussFront = Maps.newTreeMap();
	static {
		allStatussFront.put(STATUS_1, "已拒绝");
		allStatussFront.put(STATUS2, "待审核");
		allStatussFront.put(STATUS3, "已受理");
		allStatussFront.put(STATUS4, "已转交");
		allStatussFront.put(STATUS5, "已转交");
		allStatussFront.put(STATUS6, "已转交");
		allStatussFront.put(STATUS7, "已回复");
		allStatussFront.put(STATUS88, "已下线");
		allStatussFront.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 事件类型 */
	private Byte type;
	/** 状态 */
	private Byte status;
	/** 所属区域CODE，对应wz_region表CODE */
	private String regionCode;
	/** 所属区域名称 */
	private String regionName;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 资讯浏览量 */
	private Integer viewCount;
	/** 资讯回复量 */
	private Integer replyCount;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人名称 */
	private String creator;
	/** 拒绝内容 */
	private String rejectContent;
	/** 回复内容 */
	private String replyContent;
	/** 回复时间 */
	private Date replyTime;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 审核人ID */
	private Long auditingId;
	/** 审核人姓名 */
	private String auditingtor;
	/** 审核时间（即受理时间） */
	private Date auditingTime;
	/** 受理部门，用于在前台展示 */
	private String auditingDepartment;
	/** 评论位置 */
	private String place;
	/** 所处位置经度 */
	private String lng;
	/** 所处位置纬度 */
	private String lat;
	/** 转交时间 */
	private Date transferTime;
	/** 用户手机号 **/
	private String creatorPhone;
	
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
	public Byte getType(){
		return this.type;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	public String getRegionCode(){
		return this.regionCode;
	}
	
	public void setRegionCode(String regionCode){
		this.regionCode = regionCode;
	}
	public String getRegionName(){
		return this.regionName;
	}
	
	public void setRegionName(String regionName){
		this.regionName = regionName;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public Integer getViewCount(){
		return this.viewCount;
	}
	
	public void setViewCount(Integer viewCount){
		this.viewCount = viewCount;
	}
	public Integer getReplyCount(){
		return this.replyCount;
	}
	
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreatorId(){
		return this.creatorId;
	}
	
	public void setCreatorId(Long creatorId){
		this.creatorId = creatorId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public String getRejectContent(){
		return this.rejectContent;
	}
	
	public void setRejectContent(String rejectContent){
		this.rejectContent = rejectContent;
	}
	public String getReplyContent(){
		return this.replyContent;
	}
	
	public void setReplyContent(String replyContent){
		this.replyContent = replyContent;
	}
	public Date getReplyTime(){
		return this.replyTime;
	}
	
	public void setReplyTime(Date replyTime){
		this.replyTime = replyTime;
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
	public String getAuditingDepartment(){
		return this.auditingDepartment;
	}
	
	public void setAuditingDepartment(String auditingDepartment){
		this.auditingDepartment = auditingDepartment;
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

	public Date getTransferTime(){
		return this.transferTime;
	}
	
	public void setTransferTime(Date transferTime){
		this.transferTime = transferTime;
	}
	
	public String getCreatorPhone() {
		return creatorPhone;
	}

	public void setCreatorPhone(String creatorPhone) {
		this.creatorPhone = creatorPhone;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
