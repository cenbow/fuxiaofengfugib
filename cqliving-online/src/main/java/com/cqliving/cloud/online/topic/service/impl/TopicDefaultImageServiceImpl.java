package com.cqliving.cloud.online.topic.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.topic.domain.TopicDefaultImage;
import com.cqliving.cloud.online.topic.manager.TopicDefaultImageManager;
import com.cqliving.cloud.online.topic.service.TopicDefaultImageService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("topicDefaultImageService")
@ServiceHandleMapping(managerClass = TopicDefaultImageManager.class)
public class TopicDefaultImageServiceImpl implements TopicDefaultImageService {

	private static final Logger logger = LoggerFactory.getLogger(TopicDefaultImageServiceImpl.class);
	
	@Autowired
	private TopicDefaultImageManager topicDefaultImageManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询话题默认图片表失败")})
	public Response<PageInfo<TopicDefaultImage>> queryForPage(PageInfo<TopicDefaultImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询话题默认图片表失败")})
	public Response<TopicDefaultImage> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除话题默认图片表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除话题默认图片表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存话题默认图片表失败")})
	public Response<Void> save(TopicDefaultImage topicDefaultImage) {
		return null;
	}

	@Override
	public Response<TopicDefaultImage> getByApp(Long appId) {
		Response<TopicDefaultImage> response = Response.newInstance();
		try {
			TopicDefaultImage data = topicDefaultImageManager.getByAppId(appId);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取话题默认图片失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取话题默认图片失败");
		}
		return response;
	}

	@Override
	public Response<Void> save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, String defaultImage) {
		Response<Void> response = Response.newInstance();
		try {
			 topicDefaultImageManager.save(appId, userId, nickname, parma, defaultImage);
		} catch (Exception e) {
			logger.error("保存话题配置失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存话题配置失败");
		}
		return response;
	}
}
