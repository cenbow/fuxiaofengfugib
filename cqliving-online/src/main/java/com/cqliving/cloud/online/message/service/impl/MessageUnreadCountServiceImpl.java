package com.cqliving.cloud.online.message.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.cloud.online.message.manager.MessageUnreadCountManager;
import com.cqliving.cloud.online.message.service.MessageUnreadCountService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("messageUnreadCountService")
@ServiceHandleMapping(managerClass = MessageUnreadCountManager.class)
public class MessageUnreadCountServiceImpl implements MessageUnreadCountService {

	private static final Logger logger = LoggerFactory.getLogger(MessageUnreadCountServiceImpl.class);
	
	@Autowired
	private MessageUnreadCountManager messageUnreadCountManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询消息未读数量表失败")})
	public Response<PageInfo<MessageUnreadCount>> queryForPage(PageInfo<MessageUnreadCount> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询消息未读数量表失败")})
	public Response<MessageUnreadCount> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除消息未读数量表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存消息未读数量表失败")})
	public Response<Void> save(MessageUnreadCount messageUnreadCount) {
		return null;
	}

	@Override
	public Response<Integer> getCount(Long appId, String sessionId, String token, Byte type) {
		Response<Integer> response = Response.newInstance();
		try {
			Integer data = messageUnreadCountManager.getCount(appId, sessionId, token, type);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取未读消息数失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取未读消息数失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取未读消息数失败");
		}
		return response;
	}

	@Override
	public Response<Void> clear(Long appId, String sessionId, String token, Byte type) {
		Response<Void> response = Response.newInstance();
		try {
			messageUnreadCountManager.clear(appId, sessionId, token, type);
		} catch (BusinessException e) {
			logger.error("清空未读消息数失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("清空未读消息数失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("清空未读消息数失败");
		}
		return response;
	}
	
}
