package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.cloud.online.account.manager.UserSmsLogManager;
import com.cqliving.cloud.online.account.service.UserSmsLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userSmsLogService")
@ServiceHandleMapping(managerClass = UserSmsLogManager.class)
public class UserSmsLogServiceImpl implements UserSmsLogService {

	private static final Logger logger = LoggerFactory.getLogger(UserSmsLogServiceImpl.class);
	
	@Autowired
	private UserSmsLogManager userSmsLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户验证码表失败")})
	public Response<PageInfo<UserSmsLog>> queryForPage(PageInfo<UserSmsLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户验证码表失败")})
	public Response<UserSmsLog> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户验证码表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户验证码表失败")})
	public Response<Void> save(UserSmsLog userSmsLog) {
		return null;
	}

	@Override
	public Response<Void> sendCaptcha(Long appId, String sessionId, String phone, Byte type) {
		Response<Void> response = Response.newInstance();
		try {
			userSmsLogManager.sendCaptcha(appId, sessionId, phone, type);
		} catch (BusinessException e) {
			logger.error("发送验证码失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("发送验证码失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发送验证码失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<SmsStatisticsDto>> getStatistic(PageInfo<SmsStatisticsDto> pageInfo) {
		Response<PageInfo<SmsStatisticsDto>> response = Response.newInstance();
		try {
			PageInfo<SmsStatisticsDto> data = userSmsLogManager.getStatistic(pageInfo);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取短信统计失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取短信统计失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取短信统计失败");
		}
		return response;
	}
	
}