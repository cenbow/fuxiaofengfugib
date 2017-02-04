package com.org.weixin.module.jywth.service.impl;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;
import org.wechat.framework.utils.Dates;

import com.alibaba.fastjson.JSONObject;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.weixin.module.jywth.common.JYWTHConstants;
import com.org.weixin.module.jywth.common.sms.SMSProvider;
import com.org.weixin.module.jywth.common.sms.vo.SMSRequest;
import com.org.weixin.module.jywth.common.sms.vo.SMSResponse;
import com.org.weixin.module.jywth.dao.JywthMsgLogDao;
import com.org.weixin.module.jywth.domain.JywthMsgLog;
import com.org.weixin.module.jywth.service.JywthMsgLogService;

@Service("jywthMsgLogService")
public class JywthMsgLogServiceImpl extends EntityServiceImpl<JywthMsgLog,JywthMsgLogDao> implements JywthMsgLogService {

	@Autowired
	private SpyMemcachedClient memcachedClient;
	
	/**
	 * @param phone
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthMsgLogService#sendSmsCode(java.lang.String)
	 * @author fuxiaofeng on 2016年4月2日
	 */
	@Override
	@Transactional
	public JywthMsgLog sendSmsCode(String phone) {
		
		String uid = memcachedClient.get(JYWTHConstants.JYWTH_SMS_UUID);
		String token = memcachedClient.get(JYWTHConstants.JYWTH_SMS_TOKEN);
		String url = memcachedClient.get(JYWTHConstants.JYWTH_SMS_SENDCODE_URL);
		SMSRequest smsReq = new SMSRequest(phone,"",uid,token,"");
		
		SMSResponse sMSResponse = SMSProvider.sendSMS(smsReq, url,SMSRequest.SEND_TYPE1);
		
		JywthMsgLog jywthMsgLog = this.cover(sMSResponse,smsReq,JywthMsgLog.MSGTYPE0);
		
		return this.save(jywthMsgLog);
	}

	private JywthMsgLog cover(SMSResponse sMSResponse,SMSRequest smsReq,byte type){
		if(null == sMSResponse)return null;
		
		String errorno = sMSResponse.getMeta().getErrno();
		Date now = Dates.now();
		JywthMsgLog jywthMsgLog = new JywthMsgLog();
		jywthMsgLog.setCreateTime(Dates.now());
		jywthMsgLog.setMsgType(type);
		jywthMsgLog.setRemoteResp(JSONObject.toJSONString(sMSResponse));
		
		jywthMsgLog.setPhone(smsReq.getPhone());
		jywthMsgLog.setRemoteParams(JSONObject.toJSONString(smsReq));
		
		if(NumberUtils.toInt(errorno,-1) != 0){
			jywthMsgLog.setIsSuccess(JywthMsgLog.ISSUCCESS1);
			jywthMsgLog.setOverdueTime(now);
		}else{
			jywthMsgLog.setIsSuccess(JywthMsgLog.ISSUCCESS0);
			int expire = NumberUtils.toInt(sMSResponse.getResult().getExpire(), 0);
			jywthMsgLog.setExpireTime(expire);
			jywthMsgLog.setOverdueTime(DateUtils.addSeconds(now, expire));
			jywthMsgLog.setMsgCode(smsReq.getCode());
		}
		
		return jywthMsgLog;
	}

	/**
	 * @param phone
	 * @param code
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthMsgLogService#sendAuthCodeSms(java.lang.String, java.lang.String)
	 * @author fuxiaofeng on 2016年4月2日
	 */
	@Override
	@Transactional
	public JywthMsgLog sendAuthCodeSms(String phone, String code) {

		String uid = memcachedClient.get(JYWTHConstants.JYWTH_SMS_UUID);
		String token = memcachedClient.get(JYWTHConstants.JYWTH_SMS_TOKEN);
		String url = memcachedClient.get(JYWTHConstants.JYWTH_SMS_AUTHCODE_URL);
		SMSRequest smsReq = new SMSRequest(phone,"",uid,token,code);
		SMSResponse sMSResponse = SMSProvider.sendSMS(smsReq, url,SMSRequest.SEND_TYPE2);
		
		JywthMsgLog jywthMsgLog = this.cover(sMSResponse, smsReq,JywthMsgLog.MSGTYPE1);
		
		return this.save(jywthMsgLog);
	}

	/**
	 * @return
	 * @see com.org.weixin.module.jywth.service.JywthMsgLogService#sendTextSms()
	 * @author fuxiaofeng on 2016年4月3日
	 */
	@Override
	public JywthMsgLog sendTextSms(String phone,String name,String code) {
		
		String uid = memcachedClient.get(JYWTHConstants.JYWTH_SMS_UUID);
		String token = memcachedClient.get(JYWTHConstants.JYWTH_SMS_TOKEN);
		String url = memcachedClient.get(JYWTHConstants.JYWTH_SMS_SENDTEXT_URL);
		String content = memcachedClient.get(JYWTHConstants.JYWTH_GETAWARD_TEXT);
		String text = String.format(content, name,code);
		
		SMSRequest smsReq = new SMSRequest(phone,text,uid,token,code);
		SMSResponse sMSResponse = SMSProvider.sendSMS(smsReq, url,SMSRequest.SEND_TYPE3);
		
		JywthMsgLog jywthMsgLog = this.cover(sMSResponse, smsReq,JywthMsgLog.MSGTYPE2);
		
		return this.save(jywthMsgLog);
	}
}
