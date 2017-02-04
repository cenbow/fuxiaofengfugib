/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.sms.vo;

import com.org.util.Crypto;
import com.org.weixin.util.JsonUtil;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月2日
 */
public class RequestData {

	/**短信接收手机号*/
	private String phone;
	/**短信发送内容(需带签名)*/
	private String msgStr;
	/**1:通知类;2:营销类*/
	private Integer sendType=1;
	
	@SuppressWarnings("unused")
	private RequestData(){}
	
	public RequestData(String phone,String msgStr){
		this.phone = phone;
		this.msgStr = msgStr;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMsgStr() {
		return msgStr;
	}
	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	
	public String getRequestData(String publicKey){
		
		String json = JsonUtil.toJSONString(this);
		return Crypto.encodeRSA(json, publicKey);
	}
}
