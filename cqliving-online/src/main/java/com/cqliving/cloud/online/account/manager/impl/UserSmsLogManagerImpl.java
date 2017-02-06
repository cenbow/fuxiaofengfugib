package com.cqliving.cloud.online.account.manager.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.SmsTemplateDao;
import com.cqliving.cloud.online.account.dao.UserAccountDao;
import com.cqliving.cloud.online.account.dao.UserSmsLogDao;
import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.cloud.online.account.manager.UserSmsLogManager;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.manager.AppInfoManager;
import com.cqliving.cloud.online.sms.domain.SmsLog;
import com.cqliving.cloud.online.sms.manager.SmsLogManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Crypt;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.cqliving.tool.utils.RandomUtils;
import com.google.common.collect.Maps;

@Service("userSmsLogManager")
public class UserSmsLogManagerImpl extends EntityServiceImpl<UserSmsLog, UserSmsLogDao> implements UserSmsLogManager {
	
	@Autowired
	private AppInfoManager appInfoManager;
	@Autowired
	private SmsLogManager smsLogManager;
	@Autowired
	private SmsTemplateDao smsTemplateDao;
	@Autowired
	private UserAccountDao userAccountDao;

	@Override
	//public UserSmsLog getLastSms(Long appId, String sessionId, Byte type, Byte status) {
	public UserSmsLog getLastSms(String telephone,Long appId, Byte type, Byte status) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_telephone", telephone);
		map.put("EQ_status", status);
		map.put("EQ_type", type);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("id", false);
		List<UserSmsLog> list = getEntityDao().query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	@SuppressWarnings("unchecked")
	public void sendCaptcha(Long appId, String sessionId, String phone, Byte type) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoManager.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		if (StringUtils.isBlank(appInfo.getSmsSignature())) {	//未配置短信签名
			throw new BusinessException(ErrorCodes.FAILURE, "短信签名未配置");
		}
		
		//控制每日短信总数
		int limit = 5;		
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_telephone", phone);
		map.put("EQ_type", type);
		map.put("GTE_createTime", DateUtil.getTodayFirstTime());
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("createTime", false);
		List<UserSmsLog> smsLogs = getEntityDao().query(map, sortMap);
		if (CollectionUtils.isNotEmpty(smsLogs) && smsLogs.size() >= limit) {
			throw new BusinessException(ErrorCodes.FAILURE, "已达到验证码发送上限，请明日再试");
		}
		//发送间隔控制，1分钟之内不重新发送验证码
		if (CollectionUtils.isNotEmpty(smsLogs)) {
			if (DateUtil.toDifferMinute(smsLogs.get(0).getCreateTime(), DateUtil.now()) <= 0) {
				throw new BusinessException(ErrorCodes.FAILURE, "您拉取验证码太过频繁，请稍后再试");
			}
		}
		
		if (UserSmsLog.TYPE0.equals(type)) {	//注册
			//判断手机号是否已注册
			List<UserAccount> accounts = userAccountDao.getByUserName(phone);
			UserAccount userAccount = CollectionUtils.isNotEmpty(accounts) ? accounts.get(0) : null;
			if (userAccount != null) {	//手机号已注册
				AppInfo info = appInfoManager.get(userAccount.getAppId());
				String errorMessage;
				if (appId.equals(info.getId())) {
					//在本app注册过
					errorMessage = "手机号已注册，可直接登录";
				} else {
					//提示手机号已在哪个app注册
					errorMessage = ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED_WITH_APP.getDesc().replace("#", info.getName());
				}
				throw new BusinessException(ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED_WITH_APP.getCode(), errorMessage);
			}
		}
		
		//生成6位随机验证码
		String captcha = RandomUtils.generateIntString(6);
//		String captcha = "111111";
		//获取短信模板
		List<SmsTemplate> templates = smsTemplateDao.getByAppAndType(appId, type);
		if (CollectionUtils.isEmpty(templates)) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.TYPE_NOT_EXIST.getCode(), 
					ErrorCodes.SmsErrorEnum.TYPE_NOT_EXIST.getDesc());
		}
		SmsTemplate template = templates.get(0);
		String content = template.getContent() + "【" + appInfo.getSmsSignature() + "】";
		content = content.replace("#", captcha);
		//调用接口发送验证码
		String smsUrl = PropertiesConfig.getString(PropertyKey.SMS_INTERFACE_URL);
		if (StringUtils.isBlank(smsUrl)) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.SMS_URL_NOT_EXIST.getCode(), 
					ErrorCodes.SmsErrorEnum.SMS_URL_NOT_EXIST.getDesc());
		}
		Date now = DateUtil.now();
		String smsAccount = PropertiesConfig.getString(PropertyKey.SMS_ACCOUNT);
		String smsPwd = PropertiesConfig.getString(PropertyKey.SMS_PWD);
		String smsUserId = PropertiesConfig.getString(PropertyKey.SMS_USERID);
		String timestamp = DateUtil.format(now, DateUtil.YYYYMMDDHHMMSS);
		String sign = Crypt.MD5(smsAccount + smsPwd + timestamp).toLowerCase();
		Map<String, String> params = Maps.newHashMap();
		params.put("userId", smsUserId);
		params.put("sign", sign);
		params.put("timestamp", timestamp);
		params.put("mobileList", phone);
		params.put("content", content);
		params.put("extno", appInfo.getSmsCode());
		String result = HttpClientUtil.sendPostRequest(smsUrl, params, null, null);
//		String result = "{\"code\": 0, \"data\": {\"taskId\": \"testTaskId\"}}";
		
		//处理返回结果
		Map<String, Object> jsonResult = (Map<String, Object>) JSONUtils.parse(result);
		if (jsonResult.get("code").equals(0)) {	//调用成功
			Map<String, Object> data = (Map<String, Object>) jsonResult.get("data");
			String taskId = data.get("taskId").toString();
			//记录发送日志
			SmsLog smsLog = new SmsLog();
			smsLog.setContext(content);
			smsLog.setCreateTime(now);
			smsLog.setExtendsCode(appInfo.getSmsCode());
			smsLog.setResponse(result);
			smsLog.setSendTime(now);
			smsLog.setSendType(SmsLog.SENDTYPE0);
			smsLog.setSourceAppId(appId);
			smsLog.setStatus(SmsLog.STATUS1);
			smsLog.setTaskid(taskId);
			smsLog.setTelephone(phone);
			smsLogManager.save(smsLog);
			//保存到数据库
			UserSmsLog obj = new UserSmsLog();
			obj.setCreateTime(DateUtil.now());
			obj.setPhoneCode(sessionId);
			obj.setAppId(appInfo.getId());
			obj.setStatus(UserSmsLog.STATUS0);
			obj.setTelephone(phone);
			obj.setType(type);
			obj.setCaptcha(captcha);
			save(obj);
		}
		
	}

	@Override
	public PageInfo<SmsStatisticsDto> getStatistic(PageInfo<SmsStatisticsDto> pageInfo) {
		return getEntityDao().getStatistic(pageInfo);
	}
	
}