package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.UserActQrcode;
import com.cqliving.cloud.online.act.manager.UserActQrcodeManager;
import com.cqliving.cloud.online.act.service.UserActQrcodeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActQrcodeService")
@ServiceHandleMapping(managerClass = UserActQrcodeManager.class)
public class UserActQrcodeServiceImpl implements UserActQrcodeService {

	private static final Logger logger = LoggerFactory.getLogger(UserActQrcodeServiceImpl.class);
	
	@Autowired
	private UserActQrcodeManager userActQrcodeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户参与二维码扫描活动表失败")})
	public Response<PageInfo<UserActQrcode>> queryForPage(PageInfo<UserActQrcode> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户参与二维码扫描活动表失败")})
	public Response<UserActQrcode> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户参与二维码扫描活动表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户参与二维码扫描活动表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户参与二维码扫描活动表失败")})
	public Response<Void> save(UserActQrcode userActQrcode) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.UserActQrcodeService#verify(java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<UserActQrcode> verify(String code, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActQrcodeService#findByAppId(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<UserActQrcode> findByCode(String actCode, String token) {
		return null;
	}
}
