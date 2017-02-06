package com.cqliving.cloud.online.message.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.message.domain.MessagePushLog;
import com.cqliving.cloud.online.message.manager.MessagePushLogManager;
import com.cqliving.cloud.online.message.service.MessagePushLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("messagePushLogService")
@ServiceHandleMapping(managerClass = MessagePushLogManager.class)
public class MessagePushLogServiceImpl implements MessagePushLogService {

	//private static final Logger logger = LoggerFactory.getLogger(MessagePushLogServiceImpl.class);
	
	@Autowired
	private MessagePushLogManager messagePushLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询推送日志表失败")})
	public Response<PageInfo<MessagePushLog>> queryForPage(PageInfo<MessagePushLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询推送日志表失败")})
	public Response<MessagePushLog> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除推送日志表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存推送日志表失败")})
	public Response<Void> save(MessagePushLog messagePushLog) {
		return null;
	}
}
