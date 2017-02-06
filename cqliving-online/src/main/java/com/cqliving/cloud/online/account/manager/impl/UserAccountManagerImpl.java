package com.cqliving.cloud.online.account.manager.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.RequestLimitUtil;
import com.cqliving.cloud.online.account.dao.UserAccountDao;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserLoginInfoDao;
import com.cqliving.cloud.online.account.domain.Session;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserLoginInfo;
import com.cqliving.cloud.online.account.domain.UserLoginLog;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.cloud.online.account.manager.SessionManager;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserLoginLogManager;
import com.cqliving.cloud.online.account.manager.UserOtherAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.account.manager.UserSmsLogManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.StringUtil;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.RandomUtils;

@Service("userAccountManager")
public class UserAccountManagerImpl extends EntityServiceImpl<UserAccount, UserAccountDao> implements UserAccountManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private AbstractRedisClient abstractRedisClient;
	@Autowired
	private RequestLimitUtil requestLimitUtil;
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserLoginInfoDao userLoginInfoDao;
	@Autowired
	private UserLoginLogManager userLoginLogManager;
	@Autowired
	private UserOtherAccountManager userOtherAccountManager;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserSmsLogManager userSmsLogManager;

	@Override
	public UserAccount getByUserName(String loginName) {
		List<UserAccount> list = getEntityDao().getByUserName(loginName);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void check(Long appId, String sessionId, String token, String phone, String pwd) {
		//获取登录用户信息
		UserSession userSession =  userSessionManager.get(sessionId, token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		UserAccount userAccount = get(userSession.getUserId());
		if (userAccount == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		if (!phone.equals(userAccount.getTelephone())) {	//原手机号错误
			throw new BusinessException(
					ErrorCodes.ChangePhoneErrorEnum.ORIGINAL_PHONE_WRONG.getCode(), 
					ErrorCodes.ChangePhoneErrorEnum.ORIGINAL_PHONE_WRONG.getDesc());
		}
		if (!pwd.equals(userAccount.getPassword())) {	//密码错误
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.PWD_WRONG.getCode(), 
					ErrorCodes.CommonErrorEnum.PWD_WRONG.getDesc());
		}
		
		//验证通过，将信息放入缓存（key: sessionId; value: phone）5分钟有效
		abstractRedisClient.set(sessionId	, phone, 60 * 5);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void changePhone(Long appId, String sessionId, String phone, String captcha) {
		//验证验证码是否正确
		//UserSmsLog userSmsLog = userSmsLogManager.getLastSms(appId, sessionId, UserSmsLog.TYPE4, UserSmsLog.STATUS0);
		UserSmsLog userSmsLog = userSmsLogManager.getLastSms(phone, appId, UserSmsLog.TYPE4, UserSmsLog.STATUS0);
		if (userSmsLog == null || !userSmsLog.getCaptcha().equals(captcha)) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getCode(), 
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getDesc());
		}
		//验证是否是接收验证码的手机号
		if (!(userSmsLog.getTelephone().equals(phone))) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getCode(), 
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getDesc());
		}
		
		//验证通过，更新状态
		userSmsLog.setStatus(UserSmsLog.STATUS1);
		userSmsLog.setUseTime(DateUtil.now());
		userSmsLogManager.update(userSmsLog);
		
		//验证新手机号是否已注册
		UserAccount userAccount = getByTelephone(phone);
		if (userAccount != null) {	//手机已注册
			throw new BusinessException(
					ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED.getCode(), 
					ErrorCodes.RegisterErrorEnum.TELEPHONE_IS_REGISTED.getDesc());
		}
		
		//从缓存中获取原手机号
		String originalPhone = (String) abstractRedisClient.get(sessionId, "");
		if (StringUtils.isBlank(originalPhone)) {	//超时
			throw new BusinessException(
					ErrorCodes.ChangePhoneErrorEnum.TIME_OUT.getCode(), 
					ErrorCodes.ChangePhoneErrorEnum.TIME_OUT.getDesc());
		}
		
		//修改手机号及用户名
		userAccount = getByTelephone(originalPhone);
		userAccount.setUserName(phone);
		userAccount.setTelephone(phone);
		update(userAccount);
	}

	@Override
	public UserAccount getByTelephone(String phone) {
		List<UserAccount> list = getEntityDao().getByTelephone(phone);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public LoginResult login(Long appId, String sessionId, String loginName, String pwd, String place, 
			String lat, String lng, String openId, Byte type, String nickName, String imgUrl, String ip) {
		//控制操作频率	By Tangtao 2016-12-09
		String phone = loginName;
		if (type != null && StringUtils.isNotBlank(openId)) {	//第三方登录
			phone = type + "_" + openId;
		} 
		Boolean exceedLimit = requestLimitUtil.isExceedLimit(phone, UserSmsLog.TYPE0, 50, 24 * 3600);
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
		
		LoginResult result = new LoginResult();
		Date now = DateUtil.now();
		Long userId;
		UserAccount userAccount = null;
		Byte loginType = 0;
		if (type != null && StringUtils.isNotBlank(openId)) {	//第三方登录
			loginType = 2;
			UserOtherAccount userOtherAccount = userOtherAccountManager.getByUserToken(openId);
			UserInfo userInfo;
			if (userOtherAccount == null) {	//首次登录，创建帐号
				userAccount = new UserAccount();
				userAccount.setAppId(appId);
				userAccount.setLastLoginTime(now);
				userAccount.setLat(lat);
				userAccount.setLng(lng);
				userAccount.setLoginIp(ip);
				userAccount.setPlace(place);
				userAccount.setRegistTime(now);
				userAccount.setSource(UserOtherAccount.allTypes.get(type));
				userAccount.setStatus(UserAccount.STATUS0);
				userAccount.setType(UserAccount.TYPE0);
				userAccount = save(userAccount);
				userId = userAccount.getId();	//获取用户 id
				userInfo = new UserInfo();
				userInfo.setId(userId);
				userInfo.setImgUrl(imgUrl);
				userInfo.setName(nickName);
				userInfo.setAnonymousName(StringUtils.defaultString(appInfo.getNamePrefix(), "") + RandomUtils.generateString(8));
				userInfo.setSex(UserInfo.SEX0);
				userInfo.setSpeciality("");
				userInfo.setUpdateTime(now);
				userInfoDao.save(userInfo);
				userOtherAccount = new UserOtherAccount();
				userOtherAccount.setRegistTime(now);
				userOtherAccount.setType(type);
				userOtherAccount.setUserId(userId);
				userOtherAccount.setUserToken(openId);
				userOtherAccountManager.save(userOtherAccount);
			} else {
				userId = userOtherAccount.getUserId();
				userAccount = get(userId);
				//判断是否已被禁用（禁用用户允许登录 By Tangtao 2017-01-11）
//				if (UserAccount.STATUS1.equals(userAccount.getStatus())) {
//					throw new BusinessException(
//							ErrorCodes.CommonErrorEnum.USER_WAS_FORBIDDEN.getCode(), 
//							ErrorCodes.CommonErrorEnum.USER_WAS_FORBIDDEN.getDesc());
//				}
				//更新头像及昵称（无需更新 By Tangtao 2016-06-20）
				if(UserOtherAccount.TYPE10.equals(type)){//第三方登录更新头像昵称
					userInfo = userInfoDao.get(userOtherAccount.getUserId());
					userInfo.setImgUrl(imgUrl);
					userInfo.setName(nickName);
					userInfoDao.update(userInfo);
				}
			}
		} else if (StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(pwd)) {	//用户名和密码登录
			loginType = 1;
			userAccount = getByUserName(loginName);
			if (userAccount == null || !userAccount.getPassword().equalsIgnoreCase(pwd)) {	//用户名或密码错误
				throw new BusinessException(
						ErrorCodes.LoginErrorEnum.USERNAME_OR_PWD_WRONG.getCode(), 
						ErrorCodes.LoginErrorEnum.USERNAME_OR_PWD_WRONG.getDesc());
			}
			//判断是否已被禁用（禁用用户允许登录 By Tangtao 2017-01-11）
//			if (UserAccount.STATUS1.equals(userAccount.getStatus())) {
//				throw new BusinessException(
//						ErrorCodes.CommonErrorEnum.USER_WAS_FORBIDDEN.getCode(), 
//						ErrorCodes.CommonErrorEnum.USER_WAS_FORBIDDEN.getDesc());
//			}
			userId = userAccount.getId();
		} else {	//参数错误
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), 
					ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		
		//登录成功，生成 token
		String token = StringUtil.getUUID();
		//记录最后登录时间
		userAccount.setLastLoginTime(now);
		getEntityDao().update(userAccount);
		//记录 user_session 表
		UserSession userSession = new UserSession();
		userSession.setAppId(appId);
		userSession.setCreateTime(now);
		userSession.setSessionCode(sessionId);
		userSession.setToken(token);
		userSession.setUserId(userId);
		userSessionManager.save(userSession);
		//记录登录日志
		UserLoginLog userLoginLog = new UserLoginLog();
		userLoginLog.setAppId(appId);
		userLoginLog.setCreateTime(now);
		userLoginLog.setLat(lat);
		userLoginLog.setLng(lng);
		userLoginLog.setLoginIp(ip);
		userLoginLog.setPlace(place);
		userLoginLog.setSessionCode(sessionId);
		userLoginLog.setToken(token);
		userLoginLog.setUserId(userId);
		userLoginLogManager.save(userLoginLog);
		//记录 user_login_info 表
		UserLoginInfo obj;
		List<UserLoginInfo> userLoginInfos = userLoginInfoDao.getByAppAndUser(appId, userId);
		if (CollectionUtils.isNotEmpty(userLoginInfos)) {
			obj = userLoginInfos.get(0);
			obj.setLastLoginTime(now);
			obj.setLoginIp(ip);
		} else {
			obj = new UserLoginInfo();
			obj.setAppId(appId);
			obj.setCreateTime(now);
			obj.setLastLoginTime(now);
			obj.setLoginIp(ip);
			obj.setPhoneCode(sessionId);
			obj.setPosition(place);
			obj.setUserId(userId);
		}
		userLoginInfoDao.save(obj);
		//返回登录用户信息
		UserInfo userInfo = userInfoDao.get(userId);
		if (userInfo != null) {
			result.setImgUrl(userInfo.getImgUrl());
			result.setNickName(userInfo.getName());
			result.setEmail(userAccount == null ? "" : StringUtils.defaultString(userAccount.getEmail()));
			result.setGender(userInfo.getSex());
		}
		result.setToken(token);
		result.setLoginType(loginType);
		return result;
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void logout(Long appId, String sessionId, String token) {
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession != null) {	
			userSessionManager.remove(userSession);
		}
	}
	
	/**
     * 逻辑删除
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public int deleteLogic(Long[] ids){
        List<Long> idList = Arrays.asList(ids);
        return this.getEntityDao().updateStatus(UserAccount.STATUS1, idList);
    }
    
    /**
     * 修改状态
     * @param status 状态
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public int updateStatus(Byte status,Long... ids){
        List<Long> idList = Arrays.asList(ids);
        return this.getEntityDao().updateStatus(status, idList);
    }
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    @Override
    public PageInfo<UserDto> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap){
        return this.getEntityDao().queryPage(pageInfo, map, orderMap);
    }
    
    /**
     * 保存用户
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void saveUser(UserAccount userAccount, UserInfo userInfo) {
        //校验登录账号是否存在
        List<UserAccount> userAccountList = null;
        if(null==userAccount.getId()){
            userAccountList = this.getEntityDao().getByUserName(userAccount.getUserName());
        }else{
            userAccountList = this.getEntityDao().getByUserName(userAccount.getUserName(),userAccount.getId());
        }
        if(null!=userAccountList && userAccountList.size()>0){
            throw new BusinessException("用户登录账号已存在，请更换！");
        }
        this.save(userAccount);
        userInfo.setId(userAccount.getId());
        userInfoDao.save(userInfo);
    }
    
    /**
     * 查询单个记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月9日下午5:13:06
     */
    @Override
    public UserDto getById(Long id,Long AppId){
        return this.getEntityDao().getById(id,AppId);
    }
    
    /**
     * 重置密码
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月10日下午6:58:24
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void resetPwd(Long id ,String password){
        this.getEntityDao().updatePassword(id,password);
    }

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void findPwd(Long appId, String sessionId, String pwd, String captcha, String phone) {
		//控制操作频率	By Tangtao 2016-12-09
		Boolean exceedLimit = requestLimitUtil.isExceedLimit(phone, UserSmsLog.TYPE2, 10, 24 * 3600);
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
		//查询用户是否存在
		List<UserAccount> accounts = userAccountDao.getByUserName(phone);
		if (CollectionUtils.isEmpty(accounts)) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
		}
		//验证验证码是否正确
		//UserSmsLog userSmsLog = userSmsLogManager.getLastSms(appId, sessionId, UserSmsLog.TYPE2, UserSmsLog.STATUS0);
		UserSmsLog userSmsLog = userSmsLogManager.getLastSms(phone, appId, UserSmsLog.TYPE2, UserSmsLog.STATUS0);
		if (userSmsLog == null || !userSmsLog.getCaptcha().equals(captcha)) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getCode(), 
					ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getDesc());
		}
		//验证是否是接收验证码的手机号
		if (!(userSmsLog.getTelephone().equals(phone))) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getCode(), 
					ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getDesc());
		}
		//验证码是否过期
		if (DateUtil.toDifferMinute(userSmsLog.getCreateTime(), DateUtil.now()) >= 30) {
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getCode(), 
					ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getDesc());
		}
		//验证通过，更新状态
		userSmsLog.setStatus(UserSmsLog.STATUS1);
		userSmsLog.setUseTime(DateUtil.now());
		userSmsLogManager.update(userSmsLog);
		//修改密码
		UserAccount account = accounts.get(0);
		account.setPassword(pwd);
		update(account);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public UserAccount createTourist(Long appId, String sessionId) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		Date now = DateUtil.now();
		//判断是否存在 session 记录	By Tangtao 2016-12-09
		Session session = sessionManager.getBySession(appId, sessionId);
		if (session == null) {
			session = new Session();
			session.setCreateTime(now);
			session.setPhoneCode(sessionId);
			session.setSessionCode(sessionId);
			session.setSourceAppId(appId);
			sessionManager.save(session);
		}
		//保存 user_account
		UserAccount userAccount = new UserAccount();
		userAccount.setAppId(appId);
		userAccount.setLastLoginTime(now);
		userAccount.setRegistTime(now);
		userAccount.setSource("手机");
		userAccount.setStatus(UserAccount.STATUS0);
		userAccount.setType(UserAccount.TYPE2);
		save(userAccount);
		Long userId = userAccount.getId();
		//保存 user_info
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo.setName(StringUtils.defaultString(appInfo.getNamePrefix(), "") + RandomUtils.generateString(8));
		userInfo.setSex(UserInfo.SEX0);
		userInfo.setSpeciality("");
		userInfo.setUpdateTime(now);
		userInfoDao.save(userInfo);
		//保存 user_session
		UserSession userSession = new UserSession();
		userSession.setAppId(appId);
		userSession.setCreateTime(now);
		userSession.setSessionCode(sessionId);
		userSession.setUserId(userId);
		userSessionManager.save(userSession);
		//保存 user_login_info
		UserLoginInfo obj = new UserLoginInfo();
		obj.setAppId(appId);
		obj.setCreateTime(now);
		obj.setLastLoginTime(now);
		obj.setLoginIp("");
		obj.setPhoneCode(sessionId);
		obj.setPosition("");
		obj.setUserId(userId);
		return userAccount;
	}
	
}