package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.ErrorLogin;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.dto.UserDto;
import com.cqliving.cloud.online.account.manager.ErrorLoginManager;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.service.UserAccountService;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userAccountService")
@ServiceHandleMapping(managerClass = UserAccountManager.class)
public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	
	@Autowired
	private ErrorLoginManager errorLoginManager;
	@Autowired
	private UserAccountManager userAccountManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户账号表失败")})
	public Response<PageInfo<UserAccount>> queryForPage(PageInfo<UserAccount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户账号表失败")})
	public Response<UserAccount> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户账号表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户账号表失败")})
	public Response<Void> deleteLogic(Long... ids) {
	    return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
	    return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户账号表失败")})
	public Response<Void> save(UserAccount userAccount) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="saveUser",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户失败")})
	public Response<Void> saveUser(UserAccount userAccount,UserInfo userInfo) {
	    return null;
	}

	@Override
	public Response<Void> check(Long appId, String sessionId, String token, String phone, String pwd) {
		Response<Void> response = Response.newInstance();
		try {
			 userAccountManager.check(appId, sessionId, token, phone, pwd);
		} catch (BusinessException e) {
			logger.error("验证用户信息失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("验证用户信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("验证用户信息失败");
		}
		return response;
	}

	@Override
	public Response<Void> changePhone(Long appId, String sessionId, String phone, String captcha) {
		Response<Void> response = Response.newInstance();
		try {
			 userAccountManager.changePhone(appId, sessionId, phone, captcha);
		} catch (BusinessException e) {
			logger.error("更换手机号失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("更换手机号失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("更换手机号失败");
		}
		return response;
	}

	@Override
	public Response<LoginResult> login(Long appId, String sessionId, String loginName, String pwd, String place,
			String lat, String lng, String openId, Byte type, String nickName, String imgUrl, String ip) {
		Response<LoginResult> response = Response.newInstance();
		try {
			LoginResult data = userAccountManager.login(appId, sessionId, loginName, pwd, place, lat, lng, openId, type, nickName, imgUrl, ip);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("登录失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			//记录错误日志
			ErrorLogin errorLogin = new ErrorLogin();
			errorLogin.setLoginIp(ip);
			errorLogin.setLoginName(loginName);
			errorLogin.setLoginTime(DateUtil.now());
			errorLogin.setSourceAppId(appId);
			errorLogin.setLat(lat);
			errorLogin.setLng(lng);
			errorLoginManager.save(errorLogin);
		} catch (Exception e) {
			logger.error("登录失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("登录失败");
		}
		return response;
	}

	@Override
	public Response<Void> logout(Long appId, String sessionId, String token) {
		Response<Void> response = Response.newInstance();
		try {
			userAccountManager.logout(appId, sessionId, token);
		} catch (Exception e) {
			logger.error("退出登录失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("退出登录失败");
		}
		return response;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户账号表失败")})
    public Response<PageInfo<UserDto>> queryPage(PageInfo<UserDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
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
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id获取单个用户失败")})
    public Response<UserDto> getById(Long id,Long AppId) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="resetPwd",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="重置密码失败")})
    public Response<Void> resetPwd(Long id, String password) {
        return null;
    }

	@Override
	public Response<Void> findPwd(Long appId, String sessionId, String pwd, String captcha, String phone) {
		Response<Void> response = Response.newInstance();
		try {
			userAccountManager.findPwd(appId, sessionId, pwd, captcha, phone);
		} catch (BusinessException e) {
			logger.error("找回密码失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("找回密码失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("找回密码失败");
		}
		return response;
	}
	
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Response<UserAccount> createTourist(Long appId, String sessionId) {
	    Response<UserAccount> rs = Response.newInstance();
	    try {
	        rs.setData(userAccountManager.createTourist(appId, sessionId));
	    } catch (Exception e) {
	        logger.error("创建游客失败", e);
	        rs.setCode(ErrorCodes.FAILURE);
	        rs.setMessage("创建游客失败");
	    }
	    return rs;
	}
	
}