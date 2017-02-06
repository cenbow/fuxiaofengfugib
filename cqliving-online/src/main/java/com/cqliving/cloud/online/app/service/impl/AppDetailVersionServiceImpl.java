package com.cqliving.cloud.online.app.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.app.manager.AppDetailVersionManager;
import com.cqliving.cloud.online.app.service.AppDetailVersionService;
import com.cqliving.cloud.online.interfacc.dto.InitStartResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appDetailVersionService")
@ServiceHandleMapping(managerClass = AppDetailVersionManager.class)
public class AppDetailVersionServiceImpl implements AppDetailVersionService {

	private static final Logger logger = LoggerFactory.getLogger(AppDetailVersionServiceImpl.class);
	
	@Autowired
	private AppDetailVersionManager appDetailVersionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端内容版本表失败")})
	public Response<PageInfo<AppDetailVersion>> queryForPage(PageInfo<AppDetailVersion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端内容版本表失败")})
	public Response<AppDetailVersion> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端内容版本表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端内容版本表失败")})
	public Response<Void> save(AppDetailVersion appDetailVersion) {
		return null;
	}

	@Override
	public Response<InitStartResult> getInitStartInfo(Long appId, String sessionId, String token, Integer appVersion,
			Integer loadingImgVersion, Integer columnsVersion, Integer type) {
		Response<InitStartResult> response = Response.newInstance();
		try {
			InitStartResult data = appDetailVersionManager.getInitStartInfo(appId, sessionId, token, appVersion, loadingImgVersion, columnsVersion, type);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取客户端初始化信息失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取客户端初始化信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取客户端初始化信息失败");
		}
		return response;
	}
	
}
