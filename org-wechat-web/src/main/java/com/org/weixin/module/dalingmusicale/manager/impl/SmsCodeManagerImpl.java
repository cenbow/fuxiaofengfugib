package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.dao.SmsCodeDao;
import com.org.weixin.module.dalingmusicale.domain.SmsCode;
import com.org.weixin.module.dalingmusicale.enums.MusicaleEnum;
import com.org.weixin.module.dalingmusicale.manager.SmsCodeManager;
import com.org.weixin.module.szc.constant.SzcConstant;
import com.org.weixin.module.szc.manager.SzcMsgLogManager;
import com.org.weixin.module.szc.sms.provider.SmsSendUtil;
import com.org.weixin.module.szc.sms.vo.RequestData;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.module.szc.sms.vo.SmsResponse;
import com.org.weixin.module.szc.util.RandomUtil;
import com.org.weixin.util.JsonUtil;

@Service("smsCodeManager")
public class SmsCodeManagerImpl extends EntityServiceImpl<SmsCode, SmsCodeDao> implements SmsCodeManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	SzcMsgLogManager szcMsgLogManager;
	private final static String MSG_REPLACE = "{msg_code}";
	private final static int EXPIRE_TIME= 60*10;
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.SmsCodeManager#getSmsCode(java.lang.String)
	 */
	@Override
	@Transactional
	public SmsCode getSmsCode(String phone) {
		
		if(StringUtil.isEmpty(phone)){
			throw new BusinessException(MusicaleEnum.PHONE_NOT_NULL.code,MusicaleEnum.PHONE_NOT_NULL.msg);
		}
		Date now = Dates.now();
		List<SmsCode> inOneMinute = this.getEntityDao().findInOneMinute(now, phone);
		if(CollectionUtils.isNotEmpty(inOneMinute)){//一分钟内只能发送一次
			throw new BusinessException(MusicaleEnum.SEND_SMS_FREQUENTLY.code,MusicaleEnum.SEND_SMS_FREQUENTLY.msg);
		}
		String msgCode = RandomUtil.randomR(6);
		SmsCode smsCode = new SmsCode();
		smsCode.setInvalidTime(DateUtil.addMinute(now,EXPIRE_TIME/60));
		smsCode.setPhone(phone);
		smsCode.setSendTime(now);
		smsCode.setSmsCode(msgCode);
		smsCode.setUsed(SmsCode.USED1);
		smsCode.setValidTime(EXPIRE_TIME);
		String msg = memcachedClient.get(MusicaleConstant.DALING_SMS_MSG);
		msg = msg.replace(MSG_REPLACE, msgCode);
		this.sendSms(phone, msg, msgCode);
		return this.getEntityDao().saveAndFlush(smsCode);
	}

	@Transactional
	@Override
	public void sendSms(String phone,String msg,String code){
		logger.info("<<<<<<<<<<---------接收手机号:{}",phone);
		String channel = memcachedClient.get(SzcConstant.SZC_SMS_CHANNEL);
		String publicKey = memcachedClient.get(SzcConstant.SZC_SMS_PUBLIC_KEY);
		String url = memcachedClient.get(SzcConstant.SZC_SMS_URL);
		String token = memcachedClient.get(SzcConstant.SZC_SMS_TOKEN);
		RequestData data = new RequestData(phone,msg);
		logger.info("------短信data:{}",JsonUtil.toJSONString(data));
		String strData = data.getRequestData(publicKey);
		String timeStamp = String.valueOf(System.currentTimeMillis());
		SmsRequest sms = new SmsRequest(channel,strData,token,timeStamp);
		String s = SmsSendUtil.sendSms(sms, url);
		SmsResponse smsResponse = JsonUtil.parseObject(s,SmsResponse.class);
		//保存短信结果
		szcMsgLogManager.saveSzcMsgLog(smsResponse, data, sms, code);
		logger.info("--------短信发送回调结果:{}",s);
	}
	
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.SmsCodeManager#verifySmsCode(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public SmsCode verifySmsCode(String name, String phone, String smsCode) {
		
		if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(smsCode) || StringUtil.isEmpty(name)){
			throw new BusinessException(MusicaleEnum.VERIFY_ERR_MSG_NOT_NULL.code,MusicaleEnum.VERIFY_ERR_MSG_NOT_NULL.msg);
		}
		Date now = Dates.now();
		List<SmsCode> codes = this.getEntityDao().findByPhoneCode(phone, smsCode, now);
		if(CollectionUtils.isEmpty(codes)){
			throw new BusinessException(MusicaleEnum.VERIFY_ERR_CODE_ERR.code,MusicaleEnum.VERIFY_ERR_CODE_ERR.msg);
		}
		this.getEntityDao().updateSmsCode(name, phone, smsCode);
		return codes.get(0);
	}
}
