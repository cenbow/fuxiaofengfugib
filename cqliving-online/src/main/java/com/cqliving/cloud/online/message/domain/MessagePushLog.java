package com.cqliving.cloud.online.message.domain;


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
 * 推送日志表 Entity
 * Date: 2016-06-03 16:23:00
 * @author Code Generator
 */
@Entity
@Table(name = "message_push_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessagePushLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 资讯 */
	public static final Byte SOURCE_TYPE1 = 1;
		
	/** 推送类型 */
	public static final Map<Byte, String> allSourceTypes = Maps.newTreeMap();
	static {
		allSourceTypes.put(SOURCE_TYPE1, "资讯");
	}
	
	/** 主键 */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 极光任务_ID */
	private Long msgId;
	/** 推送类型 */
	private Byte sourceType;		//type修改为sourceType By Tangtao 2016-12-01
	/** 推送标题 */
	private String title;
	/** 推送摘要 */
	private String summary;
	/** 创建时间 */
	private Date createTime;
	/** 创建人ID */
	private Long createUserId;
	/** 创建人 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updateUserId;
	/** 更新人 */
	private String updator;
	/** 源ID */
	private Long sourceId;	//By Tangtao 2016-12-01
	/** 栏目ID */
	private Long columnsId;	//By Tangtao 2017-02-04
	
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
	public Long getMsgId(){
		return this.msgId;
	}
	
	public void setMsgId(Long msg_id){
		this.msgId = msg_id;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getSummary(){
		return this.summary;
	}
	
	public void setSummary(String summary){
		this.summary = summary;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Long getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(Long createUserId){
		this.createUserId = createUserId;
	}
	public String getCreator(){
		return this.creator;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Long getUpdateUserId(){
		return this.updateUserId;
	}
	
	public void setUpdateUserId(Long updateUserId){
		this.updateUserId = updateUserId;
	}
	public String getUpdator(){
		return this.updator;
	}
	
	public void setUpdator(String updator){
		this.updator = updator;
	}
	
	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getColumnsId() {
		return columnsId;
	}

	public void setColumnsId(Long columnsId) {
		this.columnsId = columnsId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
