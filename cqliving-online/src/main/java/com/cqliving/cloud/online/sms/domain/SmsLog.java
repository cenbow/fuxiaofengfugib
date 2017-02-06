package com.cqliving.cloud.online.sms.domain;


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
 * 发送短信表 Entity
 * Date: 2016-04-29 16:40:00
 * @author Code Generator
 */
@Entity
@Table(name = "SMS_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 即时发送 */
	public static final Byte SENDTYPE0 = 0;
	/** 定制发送 */
	public static final Byte SENDTYPE1 = 1;
		
	/** 发送类型 */
	public static final Map<Byte, String> allSendTypes = Maps.newTreeMap();
	static {
		allSendTypes.put(SENDTYPE0, "即时发送");
		allSendTypes.put(SENDTYPE1, "定制发送");
	}
	/** 发送失败 */
	public static final Byte STATUS_1 = -1;
	/** 待发送 */
	public static final Byte STATUS0 = 0;
	/** 已发送 */
	public static final Byte STATUS1 = 1;
		
	/** 发送状态 */
	public static final Map<Byte, String> allStatuss = Maps.newTreeMap();
	static {
		allStatuss.put(STATUS_1, "发送失败");
		allStatuss.put(STATUS0, "待发送");
		allStatuss.put(STATUS1, "已发送");
	}
	
	/** ID */
	private Long id;
	/** 来源APP */
	private Long sourceAppId;
	/** 扩展码号 */
	private String extendsCode;
	/** 手机号 */
	private String telephone;
	/** 内容 */
	private String context;
	/** 发送类型 */
	private Byte sendType;
	/** 发送状态 */
	private Byte status;
	/** 任务ID */
	private String taskid;
	/** 发送时间 */
	private Date sendTime;
	/** 创建时间 */
	private Date createTime;
	/** 接口返回内容 */
	private String response;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getSourceAppId(){
		return this.sourceAppId;
	}
	
	public void setSourceAppId(Long sourceAppId){
		this.sourceAppId = sourceAppId;
	}
	public String getExtendsCode(){
		return this.extendsCode;
	}
	
	public void setExtendsCode(String extendsCode){
		this.extendsCode = extendsCode;
	}
	public String getTelephone(){
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getContext(){
		return this.context;
	}
	
	public void setContext(String context){
		this.context = context;
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
	public String getTaskid(){
		return this.taskid;
	}
	
	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public String getResponse(){
		return this.response;
	}
	
	public void setResponse(String response){
		this.response = response;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
