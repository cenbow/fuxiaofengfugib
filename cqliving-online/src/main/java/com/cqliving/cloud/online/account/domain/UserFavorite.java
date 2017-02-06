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
 * 用户收藏表 Entity
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
@Entity
@Table(name = "USER_FAVORITE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFavorite extends AbstractEntity {

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
	/** 商城 */
	public static final Byte SOURCETYPE13 = 13;
		
	/** 来源类型 */
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
	/** 收藏 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS3, "收藏");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long appId;
	/** 用户ID */
	private Long userId;
	/** 收藏来源栏目 */
	private Long sourceColumns;
	/** 栏目名称 */
	private String columnsName;
	/** 标题 */
	private String title;
	/** 图片 */
	private String imageUrl;
	/** 来源类型 */
	private Byte sourceType;
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
	/** 来源ID，根据来源类型不同，引用不同表的主键 */
	private Long sourceId;
	
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
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Long getSourceColumns(){
		return this.sourceColumns;
	}
	
	public void setSourceColumns(Long sourceColumns){
		this.sourceColumns = sourceColumns;
	}
	public String getColumnsName(){
		return this.columnsName;
	}
	
	public void setColumnsName(String columnsName){
		this.columnsName = columnsName;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public Byte getSourceType(){
		return this.sourceType;
	}
	
	public void setSourceType(Byte sourceType){
		this.sourceType = sourceType;
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
	
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
