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
 * 用户登录日志表 Entity
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
@Entity
@Table(name = "USER_LOGIN_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLoginLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	
	/** ID,同URSERID */
	private Long id;
	/** 用户ID */
	private Long userId;
	/** 创建时间 */
	private Date createTime;
	/** 来源APP */
	private Long appId;
	/** 会话code */
	private String sessionCode;
	/** 设备码 */
	private String phoneCode;
	/** 所处位置 */
	private String place;
	/** 所处位置经度 */
	private String lng;
	/** 所处位置纬度 */
	private String lat;
	/** 最后登录IP */
	private String loginIp;
	/** 登录token */
	private String token;
	
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
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
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
	public String getPhoneCode(){
		return this.phoneCode;
	}
	
	public void setPhoneCode(String phoneCode){
		this.phoneCode = phoneCode;
	}
	public String getPlace(){
		return this.place;
	}
	
	public void setPlace(String place){
		this.place = place;
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
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
