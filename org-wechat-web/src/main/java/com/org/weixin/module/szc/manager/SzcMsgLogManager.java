package com.org.weixin.module.szc.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.szc.domain.SzcMsgLog;
import com.org.weixin.module.szc.sms.vo.RequestData;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.module.szc.sms.vo.SmsResponse;

/**
 * 短信日志表 Manager
 * Date: 2016-09-02 11:24:19
 * @author Code Generator
 */
public interface SzcMsgLogManager extends EntityService<SzcMsgLog> {
	
	public void saveSzcMsgLog(SmsResponse response,RequestData requestData,SmsRequest sMSRequest,String code);
}
