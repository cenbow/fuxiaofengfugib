package com.org.weixin.module.szc.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.SessionUser;
import com.org.weixin.module.szc.constant.SzcConstant;
import com.org.weixin.module.szc.dao.SzcUserAwardDao;
import com.org.weixin.module.szc.domain.SzcAward;
import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.dto.SzcUserAwardDto;
import com.org.weixin.module.szc.enums.ErrorCode;
import com.org.weixin.module.szc.manager.SzcAwardManager;
import com.org.weixin.module.szc.manager.SzcMsgLogManager;
import com.org.weixin.module.szc.manager.SzcUserAwardManager;
import com.org.weixin.module.szc.sms.provider.SmsSendUtil;
import com.org.weixin.module.szc.sms.vo.RequestData;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.module.szc.sms.vo.SmsResponse;
import com.org.weixin.module.szc.util.RandomUtil;
import com.org.weixin.util.JsonUtil;

@Service("szcUserAwardManager")
public class SzcUserAwardManagerImpl extends EntityServiceImpl<SzcUserAward, SzcUserAwardDao> implements SzcUserAwardManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	SzcAwardManager szcAwardManager;
	@Autowired
	SzcMsgLogManager szcMsgLogManager;
	
	private final static String REPLACE_STR = "{convert_code}";
	private final static String REPLACE_AWARD_NAME = "{award_name}";
	private final static String NOT_AWARD_CODE = "NOT_AWARD";
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#verifyAward(java.lang.String)
	 */
	@Override
	@Transactional
	public void verifyAward(String convertCode) {
		List<SzcUserAward> szcUserAwards = this.getEntityDao().findByConvertCode(convertCode);
		if(CollectionUtils.isEmpty(szcUserAwards)){
			throw new BusinessException(ErrorCode.VERIFY_ERR_NOT_AWARD.code,ErrorCode.VERIFY_ERR_NOT_AWARD.msg);
		}
		SzcUserAward szcUserAward = szcUserAwards.get(0);
        if(SzcUserAward.TAKESTATUS2 == szcUserAward.getTakeStatus().byteValue()){
        	throw new BusinessException(ErrorCode.HAD_VERIFY.code,ErrorCode.HAD_VERIFY.msg);
		}
		SzcAward szcAward = szcAwardManager.findByCode(szcUserAward.getAwardCode());
		//检查核销数和实际发放数
		long verifyNum = this.findVerifyAll(szcAward.getCode(), szcAward.getBeginTime(), szcAward.getEndTime());
		if(verifyNum >= szcAward.getActualNum().intValue()){//已抽完
			throw new BusinessException(ErrorCode.VERIFY_AWARD_NUM_INSUFFICIENT.code,ErrorCode.VERIFY_AWARD_NUM_INSUFFICIENT.msg);
		}
		//核销
		szcUserAward.setTakeStatus(SzcUserAward.TAKESTATUS2);
		szcUserAward.setVerifyTime(Dates.now());
		this.getEntityDao().saveAndFlush(szcUserAward);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#luckDraw(java.lang.String)
	 */
	@Override
	@Transactional
	public synchronized SzcUserAward luckDraw(String phone,SessionUser sessionUser) {
		SzcAward szcAward = szcAwardManager.getSzcAward(sessionUser.getDistrict());
		if(null == szcAward){
			throw new BusinessException(ErrorCode.AWARD_NOT_BEGIN.code,ErrorCode.AWARD_NOT_BEGIN.msg);
		}
		szcAward = szcAwardManager.luckDraw(sessionUser.getDistrict());
		//检查在该段时间是否已抽奖
		List<SzcUserAward> szcUserAwards = this.getEntityDao().findByAwardTime(szcAward.getEndTime(), phone,sessionUser.getDistrict());
		if(CollectionUtils.isNotEmpty(szcUserAwards)){
			throw new BusinessException(ErrorCode.USER_HAD_AWARD.code,ErrorCode.USER_HAD_AWARD.msg);
		}
		String awardCode = szcAward.getCode();
		if(awardCode.indexOf(NOT_AWARD_CODE) != -1){//不是奖品
			SzcUserAward szcUserAward =  this.saveSzcUserAward(szcAward, phone,sessionUser,SzcUserAward.TAKESTATUS0);
			return szcUserAward;
		}
		//检查核销数和实际发放数
		long verifyNum = this.findVerifyAll(szcAward.getCode(), szcAward.getBeginTime(), szcAward.getEndTime());
		if(verifyNum >= szcAward.getActualNum().intValue()){//已抽完
			throw new BusinessException(ErrorCode.AWARD_NUM_INSUFFICIENT.code,ErrorCode.AWARD_NUM_INSUFFICIENT.msg);
		}
		SzcUserAward szcUserAward = this.saveSzcUserAward(szcAward, phone,sessionUser,SzcUserAward.TAKESTATUS1);
		szcAwardManager.minusVirtualNum(szcAward.getCode(), 1);
		
		String smsMsg = memcachedClient.get(SzcConstant.DISTRICT_MSG_MAP.get(sessionUser.getDistrict()));
		smsMsg = smsMsg.replace(REPLACE_STR, szcUserAward.getConvertCode()).replace(REPLACE_AWARD_NAME, szcUserAward.getAwardName());
		//发送中奖短信
		this.sendSms(phone, smsMsg,szcUserAward.getConvertCode());
		return szcUserAward;
	}

	@Transactional
	private void sendSms(String phone,String msg,String code){
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
	
	@Transactional
	private SzcUserAward saveSzcUserAward(SzcAward szcAward,String phone,SessionUser sessionUser,Byte takeStatus){
		
		SzcUserAward szcUserAward = new SzcUserAward();
		String convertCode = RandomUtil.randomR(8);
		List<SzcUserAward> data = this.getEntityDao().findByConvertCode(convertCode);
		if(!CollectionUtils.isEmpty(data)){
			convertCode = RandomUtil.randomR(8);
		}
		szcUserAward.setAwardCode(szcAward.getCode());
		szcUserAward.setAwardName(szcAward.getName());
		szcUserAward.setAwardImg(szcAward.getImg());
		szcUserAward.setAwardTime(Dates.now());
		szcUserAward.setConvertCode(convertCode);
		szcUserAward.setPhone(phone);
		szcUserAward.setTakeStatus(takeStatus);
		szcUserAward.setUserId(sessionUser.getId());
		szcUserAward.setDistrict(sessionUser.getDistrict());
		return this.getEntityDao().saveAndFlush(szcUserAward);
	}
	
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#findVerifyAll(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public long findVerifyAll(String awardCode, Date beginTime, Date endTime) {
		
		return this.getEntityDao().findVerifyAll(awardCode, beginTime, endTime);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#findAwardNumDaily(java.util.Date)
	 */
	@Override
	public long findAwardNumDaily(Date date,Date date2,Integer district) {
		
		if(null == date || null == date2){
			return this.getEntityDao().findAawardNum(district);
		}
		
		return this.getEntityDao().findAwardNumDaily(date, date2,district);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#findVerifyNumDaily(java.util.Date)
	 */
	@Override
	public long findVerifyNumDaily(Date date,Date date2,Integer district) {
		
		if(null == date || null == date2){
			return this.getEntityDao().findVerifyNum(district);
		}
		
		return this.getEntityDao().findVerifyNumDaily(date, date2,district);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#queryByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public List<SzcUserAward> queryByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		return this.getEntityDao().queryByConditions(conditions, sortMap);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#findByUserId(java.lang.Long)
	 */
	@Override
	public List<SzcUserAward> findByConvertCode(String convertCode) {
		
		return this.getEntityDao().findByConvertCode(convertCode);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#statistics(java.util.Map, java.util.Map)
	 */
	@Override
	public List<SzcUserAwardDto> statistics(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		
		//return this.getEntityDao().statistics(conditions, sortMap);
		return this.getEntityDao().newStatistics();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#statisticsUser()
	 */
	@Override
	public List<SzcUserAwardDto> statisticsUser() {
		
		return this.getEntityDao().statisticsUser();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcUserAwardManager#statisticsJoinNum()
	 */
	@Override
	public long statisticsJoinNum() {
		
		return this.getEntityDao().statisticsJoinNum();
	}

}
