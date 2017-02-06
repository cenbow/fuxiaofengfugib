package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("commentAppConfigService")
@ServiceHandleMapping(managerClass = CommentAppConfigManager.class)
public class CommentAppConfigServiceImpl implements CommentAppConfigService {

	private static final Logger logger = LoggerFactory.getLogger(CommentAppConfigServiceImpl.class);
	
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询评论APP配置表失败")})
	public Response<PageInfo<CommentAppConfig>> queryForPage(PageInfo<CommentAppConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询评论APP配置表失败")})
	public Response<CommentAppConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除评论APP配置表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存评论APP配置表失败")})
	public Response<Void> save(CommentAppConfig shootAppConfig) {
		return null;
	}

	@Override
	public Response<List<CommentAppConfig>> getByAppAndType(Long appId, Byte type) {
		Response<List<CommentAppConfig>> response = Response.newInstance();
		try {
			List<CommentAppConfig> data = commentAppConfigManager.getByAppAndType(appId, type);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取评论配置失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取评论配置失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取评论配置失败");
		}
		return response;
	}

	@Override
	public Response<Void> save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, Byte type) {
		Response<Void> response = Response.newInstance();
		try {
			commentAppConfigManager.save(appId, userId, nickname, parma, type);
		} catch (BusinessException e) {
			logger.error("保存评论App配置失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("保存评论App配置失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存评论App配置失败");
		}
		return response;
	}

	@Override
	public Response<Byte> getConfigValueByName(Long appId, String name, Byte type) {
		Response<Byte> response = Response.newInstance();
		try {
			Byte data = commentAppConfigManager.getConfigValueByName(appId, name, type);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取App配置失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取App配置失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取App配置失败");
		}
		return response;
	}
	
}