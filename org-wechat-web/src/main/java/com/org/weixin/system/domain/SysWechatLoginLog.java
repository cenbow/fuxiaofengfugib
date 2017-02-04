package com.org.weixin.system.domain;


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

/**
 * sys_wechat_login_log Entity
 *
 * Date: 2015-07-27 17:15:52
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "sys_wechat_login_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysWechatLoginLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** id */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 公众号id */
	private Long accId;
	/** 用户昵称 */
	private String nickName;
	/** 登录时间 */
	private Date loginTime;
	
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
	public Long getAccId(){
		return this.accId;
	}
	
	public void setAccId(Long accId){
		this.accId = accId;
	}
	public String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	public Date getLoginTime(){
		return this.loginTime;
	}
	
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
