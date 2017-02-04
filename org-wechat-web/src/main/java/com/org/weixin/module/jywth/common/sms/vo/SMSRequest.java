/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common.sms.vo;

import com.cqliving.tool.common.util.StringUtil;
import com.org.weixin.module.jywth.common.MD5Util;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月2日
 */
public class SMSRequest {

	//发送验证码
	public final static int SEND_TYPE1=0;
	//验证验证码
	public final static int SEND_TYPE2=1;
	//发送文本短信
	public final static int SEND_TYPE3=2;
	//短信接收手机号
	private String phone;
	//短信发送内容
	private String text;
	//短信认证用户
	private String uid;
	//短信认证TOKEN
	private String token;
	//验证码
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@SuppressWarnings("unused")
	private SMSRequest(){}
	
	/**
	 * @param phone
	 * @param text
	 * @param uid
	 * @param token
	 */
	public SMSRequest(String phone, String text, String uid, String token,String code) {
		super();
		this.phone = phone;
		this.text = text;
		this.uid = uid;
		this.token = token;
		this.code = code;
	}
	
	//短信签名
	public static String getSign(SMSRequest sMSRequest,int type){
		
		String code = sMSRequest.getCode();
		String phone = sMSRequest.getPhone();
		String uid = sMSRequest.getUid();
		String token = sMSRequest.getToken();
		String text = sMSRequest.getText();
		if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(uid)
				|| StringUtil.isEmpty(token))
		return "";
		
		StringBuilder sign = new StringBuilder();
		
		sign.append("code=").append(code);
		sign.append("&phone=").append(phone).append("&text=").append(text);;
		sign.append("&uid=").append(uid).append("&token=").append(token);
		
		return MD5Util.MD5(sign.toString()).toLowerCase();
	}
	
}
