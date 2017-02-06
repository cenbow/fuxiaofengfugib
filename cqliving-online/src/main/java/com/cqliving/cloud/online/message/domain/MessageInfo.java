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
 * 消息通知表 Entity
 * Date: 2016-04-29 16:31:05
 * @author Code Generator
 */
@Entity
@Table(name = "MESSAGE_INFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** APP前端用户 */
	public static final Byte RECEIVERTYPE0 = 0;
	/** 后端用户 */
	public static final Byte RECEIVERTYPE1 = 1;
		
	/** 接收人类型 */
	public static final Map<Byte, String> allReceiverTypes = Maps.newTreeMap();
	static {
		allReceiverTypes.put(RECEIVERTYPE0, "APP前端用户");
		allReceiverTypes.put(RECEIVERTYPE1, "后端用户");
	}
	/** 站内信 */
	public static final Byte SENDTYPE0 = 0;
	/** 公告 */
	public static final Byte SENDTYPE1 = 1;
	/** APP通知 */
	public static final Byte SENDTYPE2 = 2;
	/** 短信通知 */
	public static final Byte SENDTYPE3 = 3;
		
	/** 发送类型 */
	public static final Map<Byte, String> allSendTypes = Maps.newTreeMap();
	static {
		allSendTypes.put(SENDTYPE0, "站内信");
		allSendTypes.put(SENDTYPE1, "公告");
		allSendTypes.put(SENDTYPE2, "APP通知");
		allSendTypes.put(SENDTYPE3, "短信通知");
	}
	/** 保存 */
	public static final Byte STATUS1 = 1;
	/** 已生效 */
	public static final Byte STATUS3 = 3;
	/** 删除 */
	public static final Byte STATUS99 = 99;
		
	/** 状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS1, "保存");
		allStatuss.put(STATUS3, "已生效");
		allStatuss.put(STATUS99, "删除");
	}
	
	/** id */
	private Long id;
	/** APP_ID */
	private Long appId;
	/** 标题 */
	private String title;
	/** 发送时间 */
	private Date sendTime;
	/** 内容 */
	private String context;
	/** 接收人类型 */
	private Byte receiverType;
	/** 发送类型 */
	private Byte sendType;
	/** 状态 */
	private Byte status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Long creatorId;
	/** 创建人姓名 */
	private String creator;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人ID */
	private Long updatorId;
	/** 更新人 */
	private String updator;
	/** 接收客户端名称，多个用,分隔 */
	private String receiverAppId;
	
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
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public Byte getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Byte receiverType) {
        this.receiverType = receiverType;
    }

    public Byte getSendType(){
		return this.sendType;
	}
	
	public void setSendType(Byte sendType){
		this.sendType = sendType;
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
	public String getReceiverAppId(){
		return this.receiverAppId;
	}
	
	public void setReceiverAppId(String receiverAppId){
		this.receiverAppId = receiverAppId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
