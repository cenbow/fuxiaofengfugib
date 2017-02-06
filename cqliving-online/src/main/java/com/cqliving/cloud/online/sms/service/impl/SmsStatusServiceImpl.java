package com.cqliving.cloud.online.sms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.sms.domain.SmsStatus;
import com.cqliving.cloud.online.sms.manager.SmsStatusManager;
import com.cqliving.cloud.online.sms.service.SmsStatusService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("smsStatusService")
@ServiceHandleMapping(managerClass = SmsStatusManager.class)
public class SmsStatusServiceImpl implements SmsStatusService {

	//private static final Logger logger = LoggerFactory.getLogger(SmsStatusServiceImpl.class);
	
	@Autowired
	private SmsStatusManager smsStatusManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询状态报告表失败")})
	public Response<PageInfo<SmsStatus>> queryForPage(PageInfo<SmsStatus> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询状态报告表失败")})
	public Response<SmsStatus> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除状态报告表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存状态报告表失败")})
	public Response<Void> save(SmsStatus smsStatus) {
		return null;
	}
}
