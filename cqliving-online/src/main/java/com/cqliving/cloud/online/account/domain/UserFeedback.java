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
 * 意见反馈表 Entity
 * Date: 2016-05-28 12:26:25
 * @author Code Generator
 */
@Entity
@Table(name = "user_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFeedback extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 待处理 */
	public static final Byte STATUS2 = 2;
	/** 已处理 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS2, "待处理");
		allStatuss.put(STATUS3, "已处理");
		allStatuss.put(STATUS99, "删除");
	}
	
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
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
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
	public String getReplyContent(){
		return this.replyContent;
	}
	public void setReplyContent(String replyContent){
		this.replyContent = replyContent;
	}
	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
