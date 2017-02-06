package com.cqliving.cloud.online.sms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.sms.domain.SmsLog;
import com.cqliving.cloud.online.sms.manager.SmsLogManager;
import com.cqliving.cloud.online.sms.service.SmsLogService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("smsLogService")
@ServiceHandleMapping(managerClass = SmsLogManager.class)
public class SmsLogServiceImpl implements SmsLogService {

	//private static final Logger logger = LoggerFactory.getLogger(SmsLogServiceImpl.class);
	
	@Autowired
	private SmsLogManager smsLogManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询发送短信表失败")})
	public Response<PageInfo<SmsLog>> queryForPage(PageInfo<SmsLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询发送短信表失败")})
	public Response<SmsLog> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除发送短信表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存发送短信表失败")})
	public Response<Void> save(SmsLog smsLog) {
		return null;
	}
}
