/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.interfacc.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月27日
 */
public class LoginResult {
	
	/** 用户 token */
	private String token;
	/** 昵称 */
	private String nickName;
	/** 头像地址 */
	private String imgUrl;
	/** 性别 */
	private Byte gender;
	/** 邮箱 */
	private String email;
	/** 登录方式{1:注册用户,2:第三方用户} */
	private Byte loginType;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String name) {
		this.nickName = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getLoginType() {
		return loginType;
	}
	public void setLoginType(Byte loginType) {
		this.loginType = loginType;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}