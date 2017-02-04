/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.sms.vo;

import com.org.weixin.module.jywth.common.MD5Util;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月2日
 */
public class SmsRequest {

	/**发送短信的具体的参数*/
	private String data;
	/**容易网分配的渠道*/
	private String channel;
	/**请求时间搓，超过2秒不予处理*/
	private String timeStamp;
	/**md5(data+timeStamp+channel+token)*/
	private String sign;
	/**请求token*/
	private String token;
	
	@SuppressWarnings("unused")
	private SmsRequest(){}
	
	public SmsRequest(String channel,String data,String token,String timeStamp){
		this.channel = channel;
		this.data = data;
		this.token = token;
		this.timeStamp = timeStamp;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getSign() {
		this.sign ="data=" + this.data+ "&timeStamp="+ this.timeStamp+"&channel=" +this.channel+"&token=" + this.token;
		return MD5Util.MD5(this.sign).toLowerCase();
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
