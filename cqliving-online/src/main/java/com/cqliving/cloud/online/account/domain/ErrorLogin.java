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
 * 登录错误日志表 Entity
 * Date: 2016-04-29 16:39:15
 * @author Code Generator
 */
@Entity
@Table(name = "ERROR_LOGIN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ErrorLogin extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID */
	private Long id;
	/** 来源ID,用户来源的区县ID */
	private Long sourceAppId;
	/** 账号 */
	private String loginName;
	/** 设备码 */
	private String phoneCode;
	/** 登录时间 */
	private Date loginTime;
	/** 所处位置经度 */
	private String lng;
	/** 所处位置纬度 */
	private String lat;
	/** 登录IP */
	private String loginIp;
	
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
	public String getLoginName(){
		return this.loginName;
	}
	
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
	}
	public Date getLoginTime(){
		return this.loginTime;
	}
	
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}
	
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
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
