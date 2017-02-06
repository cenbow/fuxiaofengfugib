package com.cqliving.cloud.online.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.cloud.online.order.manager.OrderIncomeRecordsManager;
import com.cqliving.cloud.online.order.service.OrderIncomeRecordsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("orderIncomeRecordsService")
@ServiceHandleMapping(managerClass = OrderIncomeRecordsManager.class)
public class OrderIncomeRecordsServiceImpl implements OrderIncomeRecordsService {

	private static final Logger logger = LoggerFactory.getLogger(OrderIncomeRecordsServiceImpl.class);
	
	@Autowired
	private OrderIncomeRecordsManager orderIncomeRecordsManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询订单收支记录表失败")})
	public Response<PageInfo<OrderIncomeRecords>> queryForPage(PageInfo<OrderIncomeRecords> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询订单收支记录表失败")})
	public Response<OrderIncomeRecords> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除订单收支记录表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存订单收支记录表失败")})
	public Response<Void> save(OrderIncomeRecords orderIncomeRecords) {
		return null;
	}

	/**
     * 分页查询收支记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:22:09
     */
    @Override
    public Response<PageInfo<OrderIncomeRecordsDto>> queryByPage(PageInfo<OrderIncomeRecordsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        Response<PageInfo<OrderIncomeRecordsDto>> res = Response.newInstance();
        try {
            PageInfo<OrderIncomeRecordsDto> data = orderIncomeRecordsManager.queryByPage(pageInfo, map, orderMap);
            res.setData(data);
        } catch (BusinessException e) {
            logger.error("分页查询收支记录失败：" + e.getMessage(), e);
            res.setCode(e.getErrorCode());
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("分页查询收支记录失败", e);
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage("分页查询收支记录失败");
        }
        return res;
    }
}
