package com.cqliving.cloud.online.order.domain;

/**
 * Title:支付需要的配置
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月2日
 */
public class OrderPayConfigData {
	
	/** 商户生成签名字符串所使用的签名算法类型 RSA */
	public final static String SIGN_TYPE_RSA = "RSA";
	/** 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https */
	public final static String NOTIFYURL = "order/alipayCallback.html";
	/** 0—虚拟类商品。 注：虚拟类商品不支持使用花呗渠道*/
	public final static String goods_type_0 = "0";
	/** 1—实物类商品 */
	public final static String goods_type_1 = "1";
	
	/** 私钥 自己帐号的沙箱环境 */
	public final static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKtL3VpZAwz2aQu0h8fsi6LKu/7QRPSHmOJ3tgKS24kK72F75yaknG/KOLwPIz2qIWmgThmex4LO1JPOs3tGdnfRnug1CvNlpXsyKOTz2MTxIJl3nX7d2GzfnVSex0V1d+EAcvAmUFe8g9KcsiCR5ebBjUtzPGLfNCwwcDAs2F2PAgMBAAECgYAUxCC0mD/nbWP0uFERqpqPBLbfwE3ruxiMs+iLzVPx/DJjBgtSwvf4EXLpUYh7LEnarUVqk7YqJHre5Fsb4cHwt8G7dTMjg2F+lU/EY91SlsGB1fwC6XU2p1gN6+AQQlZPFm4IK9w+JRoOzxfwJtrgqf94TgxKUrzM9jZbqFNaQQJBAOCoDI0a936Z3oDcrm2rzsU/7B3brSubZbADN7F1llcrppqbGlQuxTRgXCb2xn1hH9Hp5fgNW12G0JlpPP9zYG8CQQDDMfpExch10ZUP9AhEEPqNDf5ndMfiwozrZgCTCOxsSas++ue1DdIHgPC0FrEmPVNDy+vcR7s0gK0cJ/stryThAkEAkex2Ip2WjxRRE7DEWBZV+4e2zfon9IA+v+KHJWy0l7CRyjDfHDk4/PluQhze9wfETGw6ZTH/27M7Abr61Q7TXwJAbZcStygASz3XHfTZ6zVJJoazzewACa1HJybGrca1znpx/dMq4jkXMYVQHxwaa6jEpezIyI0xauHPz14XJOgPoQJAfjGlLt61qllWCPcQWZ6wQyXrur40vCs9ljbasxO9lSke3nsYxEZ/XKiEmf96bIelYDwiOxlrh4yUoRwfVYulzQ==";
	
	//1、支付宝公共参数
	/** 支付宝分配给开发者的应用ID */
	private String app_id;
	/** 商户生成签名字符串所使用的签名算法类型，目前支持RSA */
	private String sign_type;
	/** 商户请求参数的签名串，详见签名 */
	private String sign;
	/** 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https */
	private String notify_url;
	/** 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档	 */
	private String biz_content;
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getBiz_content() {
		return biz_content;
	}
	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}
}
