package com.cqliving.cloud.online.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlExtendJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月8日
 */
public class OrderFlowDaoImpl implements OrderFlowDaoCustom {

	@Autowired
	private MysqlExtendJdbcTemplateV2 mysqlExtendJdbcTemplateV2;
	
	@Override
	public OrderFlow getOneRecoreByOrderAndStatus(Long orderId, Byte operateType) {
		String sql = "select * from order_flow where order_id=? and operate_type=? order by id desc limit 1;";
		try {
			return mysqlExtendJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(OrderFlow.class), orderId, operateType);
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}

}
