package com.cqliving.cloud.online.consult.domain;


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
 * 工商联咨询表 Entity
 * Date: 2016-11-29 14:58:28
 * @author Code Generator
 */
@Entity
@Table(name = "consult_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConsultInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 上线 */
	public static final Byte STATUS3 = 3;
	/** 下线 */
	public static final Byte STATUS88 = 88;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "上线");
		allStatuss.put(STATUS88, "下线");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** ID */
	private Long id;
	/** 客户端_ID */
	private Long appId;
	/** 咨询类别表 */
	private String type;
	/** 咨询类别名称 */
	private String typeName;
	/** 咨询内容 */
	private String content;
	/** 企业名称 */
	private String enterpriseName;
	/** 联系人姓名 */
	private String linkmanName;
	/** 联系人电话 */
	private String linkmanPhone;
	/** 回复时间 */
	private Date replyTime;
	/** 回复人姓名 */
	private String replyUserName;
	/** 回复人ID */
	private Long replyUserId;
	/** 回复内容 */
	private String replyContent;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	
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
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public String getTypeName(){
		return this.typeName;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getEnterpriseName(){
		return this.enterpriseName;
	}
	
	public void setEnterpriseName(String enterpriseName){
		this.enterpriseName = enterpriseName;
	}
	public String getLinkmanName(){
		return this.linkmanName;
	}
	
	public void setLinkmanName(String linkmanName){
		this.linkmanName = linkmanName;
	}
	public String getLinkmanPhone(){
		return this.linkmanPhone;
	}
	
	public void setLinkmanPhone(String linkmanPhone){
		this.linkmanPhone = linkmanPhone;
	}
	public Date getReplyTime(){
		return this.replyTime;
	}
	
	public void setReplyTime(Date replyTime){
		this.replyTime = replyTime;
	}
	public String getReplyUserName(){
		return this.replyUserName;
	}
	
	public void setReplyUserName(String replyUserName){
		this.replyUserName = replyUserName;
	}
	public Long getReplyUserId(){
		return this.replyUserId;
	}
	
	public void setReplyUserId(Long replyUserId){
		this.replyUserId = replyUserId;
	}
	public String getReplyContent(){
		return this.replyContent;
	}
	
	public void setReplyContent(String replyContent){
		this.replyContent = replyContent;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
