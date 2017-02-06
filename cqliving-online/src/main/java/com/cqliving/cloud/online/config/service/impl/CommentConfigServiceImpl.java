package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.CommentConfigManager;
import com.cqliving.cloud.online.config.service.CommentConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("commentConfigService")
@ServiceHandleMapping(managerClass = CommentConfigManager.class)
public class CommentConfigServiceImpl implements CommentConfigService {

	private static final Logger logger = LoggerFactory.getLogger(CommentConfigServiceImpl.class);
	
	@Autowired
	private CommentConfigManager commentConfigManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询评论配置表失败")})
	public Response<PageInfo<CommentConfig>> queryForPage(PageInfo<CommentConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询评论配置表失败")})
	public Response<CommentConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除评论配置表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存评论配置表失败")})
	public Response<Void> save(CommentConfig commentConfig) {
		return null;
	}

	@Override
	public Response<List<CommentConfig>> getByType(Byte type) {
		Response<List<CommentConfig>> response = Response.newInstance();
		try {
			List<CommentConfig> data = commentConfigManager.getByType(type);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取评论系统配置失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取评论系统配置失败");
		}
		return response;
	}
	
	@Override
	public Response<CommentConfig> getByTypeAndName(Byte type, String name) {
		Response<CommentConfig> response = Response.newInstance();
		try {
			CommentConfig data = commentConfigManager.getByTypeAndName(type, name);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取评论系统配置失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取评论系统配置失败");
		}
		return response;
	}
}
