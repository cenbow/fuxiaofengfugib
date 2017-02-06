package com.cqliving.cloud.online.config.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月15日
 */
public class AppPermissionDto {

	/** 客户端ID，表app_info的主键 */
	private Long appId;
	/** 是否有该方法的请求权限 */
	private Byte isPermission;
	/** 是否必须登录 */
	private Byte isLogin;
	/** 是对接口请求进行签名验证 */
	private Byte isSign;
	/** 是否验证sessionId为空 */
	private Byte isSessionId;
	/** 是否控制接口请求次数 */
	private Byte isRequestTimes;
	/** 请求次数间隔时间，单位分钟。request_times_interval分钟内，最多只能请求request_times_limit次 */
	private Integer requestTimesInterval;
	/** 请求限制次数，request_times_interval分钟内，最多只能请求request_times_limit次 */
	private Integer requestTimesLimit;
	/** 状态 */
	private Byte status;
	
	//------------------------ 冗余字段 --------------------------
	/** 资源请求URL，根据该字段和用户请求的判断做比较来判断是否拥有权限值 */
	private String url;
	
	public Long getAppId(){
		return this.appId;
	}
	
	public void setAppId(Long appId){
		this.appId = appId;
	}
	public Byte getIsLogin(){
		return this.isLogin;
	}
	
	public void setIsLogin(Byte isLogin){
		this.isLogin = isLogin;
	}
	public Byte getIsSign(){
		return this.isSign;
	}
	
	public void setIsSign(Byte isSign){
		this.isSign = isSign;
	}
	public Byte getIsSessionId(){
		return this.isSessionId;
	}
	
	public void setIsSessionId(Byte isSessionId){
		this.isSessionId = isSessionId;
	}
	public Byte getIsRequestTimes(){
		return this.isRequestTimes;
	}
	
	public void setIsRequestTimes(Byte isRequestTimes){
		this.isRequestTimes = isRequestTimes;
	}
	public Integer getRequestTimesInterval(){
		return this.requestTimesInterval;
	}
	
	public void setRequestTimesInterval(Integer requestTimesInterval){
		this.requestTimesInterval = requestTimesInterval;
	}
	public Integer getRequestTimesLimit(){
		return this.requestTimesLimit;
	}
	
	public void setRequestTimesLimit(Integer requestTimesLimit){
		this.requestTimesLimit = requestTimesLimit;
	}
	public Byte getStatus(){
		return this.status;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	public Byte getIsPermission() {
		return isPermission;
	}

	public void setIsPermission(Byte isPermission) {
		this.isPermission = isPermission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}