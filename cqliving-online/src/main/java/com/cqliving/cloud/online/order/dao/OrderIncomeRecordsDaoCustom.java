package com.cqliving.cloud.online.order.dao;

import java.util.Map;

import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface OrderIncomeRecordsDaoCustom {

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
    
    /**
     * Title:获取app下金额总和
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月12日
     * @param appId
     * @return
     */
    public Integer getTotalByAppId(Long appId);
}
