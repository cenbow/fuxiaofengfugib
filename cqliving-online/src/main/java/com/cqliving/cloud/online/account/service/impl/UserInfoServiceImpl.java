package com.cqliving.cloud.online.account.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.manager.UserInfoManager;
import com.cqliving.cloud.online.account.service.UserInfoService;
import com.cqliving.cloud.online.interfacc.dto.UploadResult;
import com.cqliving.cloud.online.interfacc.dto.VisitResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userInfoService")
@ServiceHandleMapping(managerClass = UserInfoManager.class)
public class UserInfoServiceImpl implements UserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户信息表失败")})
	public Response<PageInfo<UserInfo>> queryForPage(PageInfo<UserInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户信息表失败")})
	public Response<UserInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户信息表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户信息表失败")})
	public Response<Void> save(UserInfo userInfo) {
		return null;
	}

	@Override
	public Response<Void> register(Long appId, String sessionId, String loginName, String pwd, String captcha, String actSource) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.register(appId, sessionId, loginName, pwd, captcha, actSource);
		} catch (BusinessException e) {
			logger.error("注册失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("注册失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("注册失败");
		}
		return response;
	}

	@Override
	public Response<Void> updatePwd(Long appId, String token, String pwd, String newPwd) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.updatePwd(appId, token, pwd, newPwd);
		} catch (BusinessException e) {
			logger.error("修改密码失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改密码失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改密码失败");
		}
		return response;
	}

	@Override
	public Response<VisitResult> visit(Long appId, String sessionId, String token, String phoneCode, String place, String lat, String lng, String userAgent) {
		Response<VisitResult> response = Response.newInstance();
		try {
			VisitResult data = userInfoManager.visit(appId, sessionId, token, phoneCode, place, lat, lng, userAgent);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("记录访问日志失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("记录访问日志失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("记录访问日志失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifyNickname(Long appId, String token, String nickname) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.modifyNickname(appId, token, nickname);
		} catch (BusinessException e) {
			logger.error("修改昵称失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改昵称失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改昵称失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifyGender(Long appId, String token, Byte gender) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.modifyGender(appId, token, gender);
		} catch (BusinessException e) {
			logger.error("修改性别失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改性别失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改性别失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifyEmail(Long appId, String token, String email) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.modifyEmail(appId, token, email);
		} catch (BusinessException e) {
			logger.error("修改邮箱失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改邮箱失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改邮箱失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifyHead(Long appId, String token, String head) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.modifyHead(appId, token, head);
		} catch (BusinessException e) {
			logger.error("修改头像失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改头像失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改头像失败");
		}
		return response;
	}

	@Override
	public Response<Void> removeFavorites(Long appId, String sessionId, String token, List<Long> idList) {
		Response<Void> response = Response.newInstance();
		try {
			userInfoManager.removeFavorites(appId, sessionId, token, idList);
		} catch (BusinessException e) {
			logger.error("删除收藏失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除收藏失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("删除收藏失败");
		}
		return response;
	}

	@Override
	public Response<UploadResult> uploadImg(String image, Long appId) {
		Response<UploadResult> response = Response.newInstance();
		try {
			UploadResult data = userInfoManager.uploadImg(image, appId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("上传图片失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("上传图片失败");
		}
		return response;
	}
	
}
