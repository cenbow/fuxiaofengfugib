package com.org.weixin.module.ahjy.dto;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Title:积分授权码
 * <p>Description:</p>
 * Copyright (c) 
 * @author huxiaoping on 2016年3月27日
 */
public class GenerationIntegralDto {
	
	public static String CODESUCCEED = "200";
	public static String CODEERROR = "201";
	/** 200：成功 201：失败  */
	private String retcode;
	/** 鉴权码 */
    private String integral;
    /** 错误提示 */
    private String error;
    /** 成功提示 */
    private String succeed;

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}