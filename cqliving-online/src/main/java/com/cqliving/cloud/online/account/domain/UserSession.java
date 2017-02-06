package com.cqliving.cloud.online.account.domain;


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

/**
 * 用户APP当前登录信息表 Entity
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
@Entity
@Table(name = "USER_SESSION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSession extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 来源APP */
	private Long appId;
	/** 会话code */
	private String sessionCode;
	/** 用户TOKEN */
	private String token;
	/** 设备码 */
	private String phoneCode;
	/** 创建时间 */
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getUserId(){
		return this.userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
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
	public String getToken(){
		return this.token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
