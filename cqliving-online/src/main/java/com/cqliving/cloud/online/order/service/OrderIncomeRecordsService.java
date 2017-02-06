package com.cqliving.cloud.online.order.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.tool.common.Response;

/**
 * 订单收支记录表 Service
 * Date: 2016-12-07 11:09:38
 * @author Code Generator
 */
public interface OrderIncomeRecordsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<OrderIncomeRecords>> queryForPage(PageInfo<OrderIncomeRecords> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<OrderIncomeRecords> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(OrderIncomeRecords domain);
	/** @author Code Generator *****end*****/
	/**
     * 分页查询收支记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:22:09
     */
    public Response<PageInfo<OrderIncomeRecordsDto>> queryByPage(PageInfo<OrderIncomeRecordsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap);
}
