package com.cqliving.cloud.online.message.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.manager.MessageReceiveManager;
import com.cqliving.cloud.online.message.service.MessageReceiveService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("messageReceiveService")
@ServiceHandleMapping(managerClass = MessageReceiveManager.class)
public class MessageReceiveServiceImpl implements MessageReceiveService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询消息通知接收表失败")})
	public Response<PageInfo<MessageReceive>> queryForPage(PageInfo<MessageReceive> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询消息通知接收表失败")})
	public Response<MessageReceive> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除消息通知接收表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除消息通知接收表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存消息通知接收表失败")})
	public Response<Void> save(MessageReceive messageReceive) {
		return null;
	}
}
