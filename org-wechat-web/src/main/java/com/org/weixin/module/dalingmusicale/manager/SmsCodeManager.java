package com.org.weixin.module.dalingmusicale.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.SmsCode;

/**
 * 用户手机验证码表 Manager
 * Date: 2016-09-16 09:09:44
 * @author Code Generator
 */
public interface SmsCodeManager extends EntityService<SmsCode> {
	
	//获取短信验证码
	public SmsCode getSmsCode(String phone);
	//验证手机验证码
	public SmsCode verifySmsCode(String name,String phone,String smsCode);
	//发送短信
	public void sendSms(String phone,String msg,String code);
}
