package com.org.weixin.module.jywth.service;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.jywth.domain.JywthMsgLog;

/**
 * 短信日志表 Service
 *
 * Date: 2016-04-02 13:11:48
 *
 * @author Acooly Code Generator
 *
 */
public interface JywthMsgLogService extends EntityService<JywthMsgLog> {

	//发送短信验证码，调用接口下发
	public JywthMsgLog sendSmsCode(String phone);
	//验证验证，调用接口
	public JywthMsgLog sendAuthCodeSms(String phone,String code);
	//下发文本短信
	public JywthMsgLog sendTextSms(String phone,String name,String code);
}
