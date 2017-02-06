package com.cqliving.cloud.online.account.manager.impl;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.AppInfoUtil;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.RequestLimitUtil;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserFavoriteDao;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.Session;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.domain.UserVisitLog;
import com.cqliving.cloud.online.account.manager.SessionManager;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserInfoManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.manager.UserSmsLogManager;
import com.cqliving.cloud.online.account.manager.UserVisitLogManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.dao.ConfigSensitiveWordsDao;
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.interfacc.dto.UploadResult;
import com.cqliving.cloud.online.interfacc.dto.VisitResult;
import com.cqliving.cloud.online.shop.dao.ShopInfoDao;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.google.common.collect.Maps;

@Service("userInfoManager")
public class UserInfoManagerImpl extends EntityServiceImpl<UserInfo, UserInfoDao> implements UserInfoManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private ConfigSensitiveWordsDao configSensitiveWordsDao;
	@Autowired
	private RequestLimitUtil requestLimitUtil;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private ShopInfoDao shopInfoDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private UserAccountManager userAccountManager;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserFavoriteDao userFavoriteDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserSmsLogManager userSmsLogManager;
	@Autowired
	private UserVisitLogManager userVisitLogManager;

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void register(Long appId, String sessionId, String loginName, String pwd, String captcha, String actSource) {
		//控制操作频率	By Tangtao 2016-12-09
		Boolean exceedLimit = requestLimitUtil.isExceedLimit(loginName, UserSmsLog.TYPE0, 10, 24 * 3600);
		if (exceedLimit) {
			throw new BusinessException(ErrorCodes.FAILURE, "操作太频繁");
		}
		
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户名是否已注册
		UserAccount userAccount = userAccountManager.getByUserName(loginName);
		if (userAccount != null) {	//已注册
			throw new BusinessException(
					ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED.getCode(), 
					ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED.getDesc());
		}
		//获取验证码
		//UserSmsLog userSmsLog = userSmsLogManager.getLastSms(appInfo.getId(), sessionId, UserSmsLog.TYPE0, UserSmsLog.STATUS0); 	
		UserSmsLog userSmsLog = userSmsLogManager.getLastSms(loginName,appInfo.getId(), UserSmsLog.TYPE0, UserSmsLog.STATUS0); 	
		if (userSmsLog == null || DateUtil.toDifferMinute(userSmsLog.getCreateTime(), DateUtil.now()) >= 30) {	//验证码已失效
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getCode(), 
					ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getDesc());
		}
		String code = userSmsLog.getCaptcha();
		if (!captcha.equals(code)) {	//验证码不正确
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getCode(), 
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getDesc());
		}
		//验证是否是接收验证码的手机号
		if (!(userSmsLog.getTelephone().equals(loginName))) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getCode(), 
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getDesc());
		}
		
		Date now = DateUtil.now();
		//验证通过
		userSmsLog.setStatus(UserSmsLog.STATUS1);
		userSmsLog.setUseTime(now);
		userSmsLogManager.update(userSmsLog);
		//创建账户
		userAccount = new UserAccount();
		userAccount.setPassword(pwd.toLowerCase());	//密码统一存为小写 By Tangtao 2016-12-13
		userAccount.setRegistTime(now);
		userAccount.setSource("手机");
		userAccount.setAppId(appInfo.getId());	
		userAccount.setStatus(UserAccount.STATUS0);
		userAccount.setType(UserAccount.TYPE0);
		userAccount.setUserName(loginName);
		userAccount.setTelephone(loginName);
		userAccount.setLastLoginTime(now);
		userAccountManager.save(userAccount);
		//创建用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setActSource(StringUtils.defaultString(actSource));
		userInfo.setId(userAccount.getId());
//		userInfo.setName(StringUtil.getHideMiddlePhone(loginName));
		userInfo.setName(AppInfoUtil.getRandomNickname(appInfo.getNamePrefix()));
		userInfo.setAnonymousName(AppInfoUtil.getRandomNickname(appInfo.getNamePrefix()));
		userInfo.setSex(UserInfo.SEX0);
		userInfo.setSpeciality("");
		userInfo.setUpdateTime(DateUtil.now());
		getEntityDao().save(userInfo);
	}

	@Override
	public void updatePwd(Long appId, String token, String pwd, String newPwd) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//验证原密码是否正确
		if (!userAccount.getPassword().equals(pwd)) {	//密码错误
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.PWD_WRONG.getCode(), 
					ErrorCodes.CommonErrorEnum.PWD_WRONG.getDesc());
		}
		//修改密码
		userAccount.setPassword(newPwd);
		userAccountManager.update(userAccount);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public VisitResult visit(Long appId, String sessionId, String token, String phoneCode, String place, String lat, String lng, String userAgent) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		Date now = DateUtil.now();
		UserVisitLog userVisitLog = new UserVisitLog();
		UserSession userSession = userSessionManager.get(sessionId, token);
		Byte status = 0;	//用户账户状态
		if (userSession != null) {	//已登录
			phoneCode = userSession.getPhoneCode();
			sessionId = userSession.getSessionCode();
			//此接口返回的账户状态均为正常 By Tangtao 2017-01-11
//			UserAccount userAccount = userAccountManager.get(userSession.getUserId());
//			status = userAccount == null ? 0 : userAccount.getStatus();
			//记录访问日志
			userVisitLog.setToken(userSession.getToken());
			userVisitLog.setUserId(userSession.getUserId());
		}
		if (StringUtils.isNotBlank(sessionId)) {	//存在会话 id
			Session session = sessionManager.getBySession(appId, sessionId);
			if (session == null) {
				//生成会话
				sessionId = StringUtils.isBlank(sessionId) ? StringUtil.getUUID() : sessionId;
				session = new Session();
				session.setCreateTime(now);
				session.setPhoneCode(phoneCode);
				session.setSessionCode(sessionId);
				session.setSourceAppId(appId);
				sessionManager.save(session);
			}
		} else {	//不存在会话
			//生成会话
			sessionId = StringUtils.isBlank(sessionId) ? StringUtil.getUUID() : sessionId;
			Session session = new Session();
			session.setCreateTime(now);
			session.setPhoneCode(phoneCode);
			session.setSessionCode(sessionId);
			session.setSourceAppId(appId);
			sessionManager.save(session);
		}
		//记录访问日志
		userVisitLog.setAppId(appId);
		userVisitLog.setCreateTime(now);
		userVisitLog.setPhoneCode(phoneCode);
		userVisitLog.setPlace(place);
		userVisitLog.setLat(lat);
		userVisitLog.setLng(lng);
		userVisitLog.setSessionCode(sessionId);
		userVisitLog.setUserAgent(userAgent);
		userVisitLogManager.save(userVisitLog);
		//返回会话
		VisitResult result = new VisitResult();
		result.setPhoneCode(phoneCode);
		result.setSessionId(sessionId);
		result.setStatus(status);
		return result;
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void modifyNickname(Long appId, String token, String nickname) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		UserInfo userInfo = getEntityDao().get(userAccount.getId());
		if (userInfo == null) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//查询昵称是否已被使用
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_name", nickname);
		map.put("NOTEQ_id", userInfo.getId());
		List<UserInfo> userInfos = userInfoDao.query(map, null);
		if (CollectionUtils.isNotEmpty(userInfos)) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NICKNAME_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.NICKNAME_EXIST.getDesc());
		}
		
		//判断是否包含敏感词
		List<ConfigSensitiveWords> sensitiveWordsList = configSensitiveWordsDao.getByAppId(appId);
		for (ConfigSensitiveWords obj : sensitiveWordsList) {
			if (ConfigSensitiveWords.STATUS3.equals(obj.getStatus()) && nickname.contains(obj.getName())) {
				throw new BusinessException(ErrorCodes.FAILURE, "昵称包含敏感词，请更换一个");
			}
		}
		
		//修改
		userInfo.setName(nickname);
		userInfo.setUpdateTime(DateUtil.now());
		getEntityDao().update(userInfo);
	}

	@Override
	public void modifyGender(Long appId, String token, Byte gender) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//修改性别
		UserInfo userInfo = getEntityDao().get(userAccount.getId());
		if (userInfo == null) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		userInfo.setSex(gender);
		userInfo.setUpdateTime(DateUtil.now());
		getEntityDao().update(userInfo);
	}

	@Override
	public void modifyEmail(Long appId, String token, String email) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//修改邮箱
		userAccount.setEmail(email);
		userAccountManager.update(userAccount);
	}

	@Override
	public void modifyHead(Long appId, String token, String head) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户未登录
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		UserAccount userAccount = userAccountManager.get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//修改头像
		UserInfo userInfo = getEntityDao().get(userAccount.getId());
		if (userInfo == null) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		userInfo.setImgUrl(head);
		userInfo.setUpdateTime(DateUtil.now());
		getEntityDao().update(userInfo);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void removeFavorites(Long appId, String sessionId, String token, List<Long> idList) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		Long userId = null;
		if (StringUtils.isNotBlank(token)) {		//注册用户
			UserSession userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId = userSession.getUserId();
		} else {	//游客用户
			UserSession userSession = userSessionManager.getTourist(sessionId);
			if (userSession == null) {	
				throw new BusinessException(ErrorCodes.FAILURE, "删除收藏失败");
			}
			userId = userSession.getUserId();
		}
		
		//校验用户
		Iterable<UserFavorite> favoritelist = userFavoriteDao.findAll(idList);
		for (UserFavorite obj : favoritelist) {
			if (!obj.getUserId().equals(userId)) {
				throw new BusinessException(ErrorCodes.FAILURE, "不能删除其他用户的收藏");
			}
		}
		
		//删除收藏
		userFavoriteDao.changeStatus(idList, UserFavorite.STATUS99);
		
		//递减收藏数
		Map<String, Object> map = Maps.newHashMap();
		map.put("IN_id", idList);
		List<UserFavorite> list = userFavoriteDao.query(map, null);
		for (UserFavorite obj : list) {
			if (UserFavorite.SOURCETYPE2.equals(obj.getSourceType())) {	//商情
				shopInfoDao.decreaseCollectedCount(obj.getSourceId());
			} else if (UserFavorite.SOURCETYPE13.equals(obj.getSourceType())) {	//商城商品
				shoppingGoodsDao.decreaseCollectedCount(obj.getSourceId());
			}
		}
	}

	@Override
	public UploadResult uploadImg(String image, Long appId) {
		UploadResult result = new UploadResult();
		String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
		String modulePath = handleModulePath("", appId);
		if (!filePath.endsWith(File.separator)) {
			filePath += File.separator;
		}
		File destFile = new File(filePath);
		destFile.mkdirs();
		String fileName = StringUtil.getUUID() + ".jpg";
        File file = Base64Util.decodeBase64(image.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
        String url = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
        if (!url.endsWith(File.separator)) {
        	url += File.separator;
		}
        String fIleUrl = file.getPath().replace(filePath, url);
        result.setUrl(fIleUrl.replace("\\", "/"));
		return result;
	}
	
	/**
	 * <p>Description: 处理图片保存路径</p>
	 * @author Tangtao on 2016年7月4日
	 * @param modulePath
	 * @param appId
	 * @return
	 */
	private String handleModulePath(String modulePath, Long appId) {
		if (StringUtil.isEmpty(modulePath)) {
			modulePath = "common";
		}
		StringBuilder modulePathBuilder = new StringBuilder();
		modulePathBuilder.append(File.separator).append("app_").append(null == appId ? 0 : appId);
		modulePathBuilder.append(File.separator).append("server");
//		modulePathBuilder.append(File.separator).append(DateUtil.formatDate(DateUtil.now(), DateUtil.FORMAT_YYYY_MM_DD));
//		modulePathBuilder.append(File.separator).append(modulePath);
		return modulePathBuilder.toString();
    }
	
}