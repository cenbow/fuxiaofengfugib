package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.cloud.online.order.manager.OrderFlowManager;
import com.cqliving.cloud.online.order.service.OrderFlowService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderFlowService")
@ServiceHandleMapping(managerClass = OrderFlowManager.class)
public class OrderFlowServiceImpl implements OrderFlowService {

	private static final Logger logger = LoggerFactory.getLogger(OrderFlowServiceImpl.class);
	
	@Autowired
	private OrderFlowManager orderFlowManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单操作流水记录表失败")})
	public Response<PageInfo<OrderFlow>> queryForPage(PageInfo<OrderFlow> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单操作流水记录表失败")})
	public Response<OrderFlow> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单操作流水记录表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单操作流水记录表失败")})
	public Response<Void> save(OrderFlow orderFlow) {
		return null;
	}

	@Override
	public Response<OrderFlow> getOneRecoreByOrderAndStatus(Long orderId, Byte operateType) {
		Response<OrderFlow> rs = Response.newInstance();
		try {
			rs.setData(orderFlowManager.getOneRecoreByOrderAndStatus(orderId, operateType));
		}catch(BusinessException e){
			logger.error("订单获取流水记录失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("订单获取流水记录失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("订单获取流水记录失败");
		}
		return rs;
	}
}
