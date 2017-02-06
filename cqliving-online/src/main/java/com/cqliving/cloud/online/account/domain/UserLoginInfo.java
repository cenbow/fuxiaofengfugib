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
 * 用户APP登录信息表 Entity
 * Date: 2016-04-15 09:46:12
 * @author Code Generator
 */
@Entity
@Table(name = "user_login_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLoginInfo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 来源APP */
	private Long appId;
	/** 设备码 */
	private String phoneCode;
	/** 最后经纬度 */
	private String position;
	/** 创建时间 */
	private Date createTime;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 最后登录IP */
	private String loginIp;
	
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
	
	public void setAppId(Long sourceAppId){
		this.appId = sourceAppId;
	}
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
	}
	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	public String getLoginIp(){
		return this.loginIp;
	}
	
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
