package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderPayConfig;
import com.cqliving.cloud.online.order.manager.OrderPayConfigManager;
import com.cqliving.cloud.online.order.service.OrderPayConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderPayConfigService")
@ServiceHandleMapping(managerClass = OrderPayConfigManager.class)
public class OrderPayConfigServiceImpl implements OrderPayConfigService {

	private static final Logger logger = LoggerFactory.getLogger(OrderPayConfigServiceImpl.class);
	
	@Autowired
	private OrderPayConfigManager orderPayConfigManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单支付配置表失败")})
	public Response<PageInfo<OrderPayConfig>> queryForPage(PageInfo<OrderPayConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单支付配置表失败")})
	public Response<OrderPayConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单支付配置表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单支付配置表失败")})
	public Response<Void> save(OrderPayConfig orderPayConfig) {
		return null;
	}

	@Override
	public Response<OrderPayConfig> getByAppId(Long appId, Byte payModel) {
		Response<OrderPayConfig> rs = Response.newInstance();
		try {
			rs.setData(orderPayConfigManager.getByAppId(appId, payModel));
		}catch(BusinessException e){
			logger.error("查询支付配置失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询支付配置失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("查询支付配置失败");
		}
		return rs;
	}
}
