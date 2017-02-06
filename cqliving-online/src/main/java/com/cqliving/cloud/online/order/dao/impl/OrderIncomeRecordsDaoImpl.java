package com.cqliving.cloud.online.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.order.dao.OrderIncomeRecordsDaoCustom;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

public class OrderIncomeRecordsDaoImpl implements OrderIncomeRecordsDaoCustom {

    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    @Autowired
    private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
    
    /**
     * 分页查询收支记录
     * SELECT a.*,FORMAT(a.money/100,2) money_export,FORMAT(a.total_money/100,2) total_money_export FROM order_income_records a
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:22:09
     */
    @Override
    public PageInfo<OrderIncomeRecordsDto> queryByPage(PageInfo<OrderIncomeRecordsDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,FORMAT(a.money/100,2) money_export,FORMAT(a.total_money/100,2) total_money_export FROM order_income_records a ");
        mysqlPagedJdbcTemplateV2.queryForPage(OrderIncomeRecordsDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }

	@Override
	public Integer getTotalByAppId(Long appId) {
		String sql = "select sum(money) as total_money from order_income_records where app_id=?";
		return mysqlExtendJdbcTemplateV2.queryForObject(sql, Integer.class, appId);
	}
    

}
