package com.org.weixin.client.bean.base;

/**
 * 微信请求状态数据
 */
public class BaseResult {

	public final static String SUCCESS = "0";
	private String errcode;
	private String errmsg;

	public String getErrcode() {
		if (errcode == null) {
			this.errcode = SUCCESS;
		}
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


}
