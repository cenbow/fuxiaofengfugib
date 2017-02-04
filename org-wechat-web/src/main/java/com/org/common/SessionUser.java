package com.org.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:用户session缓存
 * <p>Description:用户session数据</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月10日
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** sessionId */
	private String sessionId;
	/** id */
	private Long id;
	/** 模块id */
	private Long moduleId;
	/** 公众号id */
	private Long accId;
	/** openid */
	private String openid;
	/** 用户昵称 */
	private String nickname;
	/** 用户头像 */
	private String headimgurl;
	/** 用户电话 */
	private String phone;
	/** 是否显示过二维码 */
	private boolean tdCodeShowed;
	/** szc用户活动区域 */
	private Integer district;
	/** 是否关注{0:未关注,1:已关注} */
	private byte subscribe;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean getTdCodeShowed() {
		return tdCodeShowed;
	}

	public void setTdCodeShowed(boolean tdCodeShowed) {
		this.tdCodeShowed = tdCodeShowed;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}
	
	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public byte getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(byte subscribe) {
		this.subscribe = subscribe;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
