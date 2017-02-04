package com.org.weixin.module.dalingmusicale.domain;


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

import org.wechat.framework.domain.AbstractEntity;
import com.google.common.collect.Maps;

/**
 * 用户手机验证码表 Entity
 * Date: 2016-09-16 09:09:44
 * @author Code Generator
 */
@Entity
@Table(name = "sms_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsCode extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/** 未使用 */
	public static final Byte USED1 = 1;
	/** 已使用 */
	public static final Byte USED2 = 2;
		
	/** 是否已使用 */
	public static final Map<Byte, String> allUseds = Maps.newTreeMap();
	static {
		allUseds.put(USED1, "未使用");
		allUseds.put(USED2, "已使用");
	}
	
	/** 主键 */
	private Long id;
	/** 姓名 */
	private String name;
	/** 接收手机号 */
	private String phone;
	/** 验证码 */
	private String smsCode;
	/** 发送时间 */
	private Date sendTime;
	/** 有效时间 */
	private Integer validTime;
	/** 过期时间 */
	private Date invalidTime;
	/** 是否已使用 */
	private Byte used;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getPhone(){
		return this.phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getSmsCode(){
		return this.smsCode;
	}
	
	public void setSmsCode(String smsCode){
		this.smsCode = smsCode;
	}
	public Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	public Integer getValidTime(){
		return this.validTime;
	}
	
	public void setValidTime(Integer validTime){
		this.validTime = validTime;
	}
	public Date getInvalidTime(){
		return this.invalidTime;
	}
	
	public void setInvalidTime(Date invalidTime){
		this.invalidTime = invalidTime;
	}
	public Byte getUsed(){
		return this.used;
	}
	
	public void setUsed(Byte used){
		this.used = used;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
