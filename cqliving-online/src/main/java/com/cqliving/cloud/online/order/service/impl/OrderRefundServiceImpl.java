package com.cqliving.cloud.online.order.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.cloud.online.order.manager.OrderRefundManager;
import com.cqliving.cloud.online.order.service.OrderRefundService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderRefundService")
@ServiceHandleMapping(managerClass = OrderRefundManager.class)
public class OrderRefundServiceImpl implements OrderRefundService {

	private static final Logger logger = LoggerFactory.getLogger(OrderRefundServiceImpl.class);
	
	@Autowired
	private OrderRefundManager orderRefundManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单商品退货表，针对订单里面的商品退货失败")})
	public Response<PageInfo<OrderRefund>> queryForPage(PageInfo<OrderRefund> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单商品退货表，针对订单里面的商品退货失败")})
	public Response<OrderRefund> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单商品退货表，针对订单里面的商品退货失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单商品退货表，针对订单里面的商品退货失败")})
	public Response<Void> save(OrderRefund orderRefund) {
		return null;
	}

    @Override
    public Response<List<OrderRefund>> getRefundingByOrderId(Long orderId) {
        Response<List<OrderRefund>> rs = Response.newInstance();
        try {
            rs.setData(orderRefundManager.getRefundingByOrderId(orderId));
        }catch(BusinessException e){
            logger.error("获取订单正在退货的商品失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取订单正在退货的商品失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取订单正在退货的商品失败");
        }
        return rs;
    }
}
