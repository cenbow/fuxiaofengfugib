package com.cqliving.cloud.online.order.manager.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.order.dao.OrderIncomeRecordsDao;
import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.cloud.online.order.manager.OrderIncomeRecordsManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("orderIncomeRecordsManager")
public class OrderIncomeRecordsManagerImpl extends EntityServiceImpl<OrderIncomeRecords, OrderIncomeRecordsDao> implements OrderIncomeRecordsManager {

    /**
     * 分页查询收支记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:22:09
     */
    @Override
    public PageInfo<OrderIncomeRecordsDto> queryByPage(PageInfo<OrderIncomeRecordsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryByPage(pageInfo, map, orderMap);
    }
}
