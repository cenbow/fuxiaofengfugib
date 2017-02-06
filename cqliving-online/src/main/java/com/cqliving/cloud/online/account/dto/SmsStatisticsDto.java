package com.cqliving.cloud.online.account.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月9日
 */
public class SmsStatisticsDto {

	/** 来源APP */
	private Long appId;
	/** APP名称 */
	private String appName;
	/** 总短信数 */
	private Long totalSend;
	/** 注册 */
	private Long type0;
	/** 登录 */
	private Long type1;
	/** 找回密码 */
	private Long type2;
	/** 修改密码 */
	private Long type3;
	/** 修改手机 */
	private Long type4;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getTotalSend() {
		return totalSend;
	}

	public void setTotalSend(Long total) {
		this.totalSend = total;
	}

	public Long getType0() {
		return type0;
	}

	public void setType0(Long type0) {
		this.type0 = type0;
	}

	public Long getType1() {
		return type1;
	}

	public void setType1(Long type1) {
		this.type1 = type1;
	}

	public Long getType2() {
		return type2;
	}

	public void setType2(Long type2) {
		this.type2 = type2;
	}

	public Long getType3() {
		return type3;
	}

	public void setType3(Long type3) {
		this.type3 = type3;
	}

	public Long getType4() {
		return type4;
	}

	public void setType4(Long type4) {
		this.type4 = type4;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}