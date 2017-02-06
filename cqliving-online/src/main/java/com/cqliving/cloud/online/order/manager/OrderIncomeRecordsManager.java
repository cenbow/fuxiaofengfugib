package com.cqliving.cloud.online.order.manager;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

import java.util.Map;

import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;

/**
 * 订单收支记录表 Manager
 * Date: 2016-12-07 11:09:38
 * @author Code Generator
 */
public interface OrderIncomeRecordsManager extends EntityService<OrderIncomeRecords> {
    
    /**
     * 分页查询收支记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:22:09
     */
    public PageInfo<OrderIncomeRecordsDto> queryByPage(PageInfo<OrderIncomeRecordsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap);
}
