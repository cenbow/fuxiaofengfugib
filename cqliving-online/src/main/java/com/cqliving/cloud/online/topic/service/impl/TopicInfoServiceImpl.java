package com.cqliving.cloud.online.topic.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.cloud.online.topic.manager.TopicInfoManager;
import com.cqliving.cloud.online.topic.service.TopicInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("topicInfoService")
@ServiceHandleMapping(managerClass = TopicInfoManager.class)
public class TopicInfoServiceImpl implements TopicInfoService {

	private static final Logger logger = LoggerFactory.getLogger(TopicInfoServiceImpl.class);
	
	@Autowired
	private TopicInfoManager topicInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询话题表失败")})
	public Response<PageInfo<TopicInfo>> queryForPage(PageInfo<TopicInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询话题表失败")})
	public Response<TopicInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除话题表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除话题表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存话题表失败")})
	public Response<Void> save(TopicInfo topicInfo) {
		return null;
	}

	@Override
	public Response<Void> saveByAdmin(TopicInfo topicInfo, String[] imageUrls) {
		Response<Void> res = Response.newInstance();
		try {
			topicInfoManager.saveByAdmin(topicInfo, imageUrls);
		} catch (BusinessException e) {
			logger.error("cms保存话题失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("cms保存话题失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("保存话题失败");
		}
		return res;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateTopStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改置顶失败")})
	public Response<Void> updateTopStatus(Byte status,Long id, String imageUrl, Long userId, String userName) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="updateRecommendStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改推荐失败")})
	public Response<Void> updateRecommendStatus(Byte status, Long id, String imageUrl) {
		return null;
	}

	@Override
	public Response<List<TopicInfo>> getTopList(Long appId) {
		Response<List<TopicInfo>> response = Response.newInstance();
		try {
			List<TopicInfo> data = topicInfoManager.getTopList(appId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取置顶话题失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取置顶话题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取置顶话题失败");
		}
		return response;
	}

	@Override
	public Response<ScrollPage<TopicInfo>> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, Long type, String name, Byte isRecommend) {
		Response<ScrollPage<TopicInfo>> response = Response.newInstance();
		try {
			ScrollPage<TopicInfo> data = topicInfoManager.queryForScrollPage(scrollPage, appId, type, name, isRecommend);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取话题列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取话题列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取话题列表失败");
		}
		return response;
	}

	@Override
	public Response<ScrollPage<TopicInfo>> queryMyScrollPage(ScrollPage<TopicInfo> scrollPage, Long appId, String sessionId, String token) {
		Response<ScrollPage<TopicInfo>> response = Response.newInstance();
		try {
			ScrollPage<TopicInfo> data = topicInfoManager.queryMyScrollPage(scrollPage, appId, sessionId, token);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取我的话题失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取我的话题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取我的话题失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> add(Long appId, String sessionId, String token, String name, String content, String imgs, String typeIds) {
		Response<Boolean> response = Response.newInstance();
		try {
			Byte status = topicInfoManager.add(appId, sessionId, token, name, content, imgs, typeIds);
			response.setData(true);
			response.setMessage(TopicInfo.STATUS1.equals(status) ? "发布成功，请等待审核通过" : "发布成功");
		} catch (BusinessException e) {
			logger.error("发布话题失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("发布话题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("发布话题失败");
		}
		return response;
	}

	@Override
	public Response<Boolean> remove(Long appId, String sessionId, String token, Long id) {
		Response<Boolean> response = Response.newInstance();
		try {
			Boolean data = topicInfoManager.remove(appId, sessionId, token, id);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("删除我的话题失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除我的话题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("删除我的话题失败");
		}
		return response;
	}
	
}
