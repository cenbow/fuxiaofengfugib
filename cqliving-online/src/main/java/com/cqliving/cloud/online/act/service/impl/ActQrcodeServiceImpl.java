package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.act.domain.ActQrcode;
import com.cqliving.cloud.online.act.manager.ActQrcodeManager;
import com.cqliving.cloud.online.act.service.ActQrcodeService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("actQrcodeService")
@ServiceHandleMapping(managerClass = ActQrcodeManager.class)
public class ActQrcodeServiceImpl implements ActQrcodeService {

	private static final Logger logger = LoggerFactory.getLogger(ActQrcodeServiceImpl.class);
	
	@Autowired
	private ActQrcodeManager actQrcodeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询二维码扫描活动失败")})
	public Response<PageInfo<ActQrcode>> queryForPage(PageInfo<ActQrcode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询二维码扫描活动失败")})
	public Response<ActQrcode> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除二维码扫描活动失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除二维码扫描活动失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存二维码扫描活动失败")})
	public Response<Void> save(ActQrcode actQrcode) {
		return null;
	}
}
