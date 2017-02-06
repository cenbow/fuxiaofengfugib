package com.cqliving.cloud.online.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.order.dao.OrderCallbackLogDaoCustom;
import com.cqliving.cloud.online.order.domain.OrderCallbackLog;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月29日
 */
public class OrderCallbackLogDaoImpl implements OrderCallbackLogDaoCustom {

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	@Override
	public OrderCallbackLog getByOrder(Long orderId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.* FROM order_pay a LEFT JOIN order_callback_log b ON a.id=b.order_pay_on");
		sql.append(" WHERE a.order_id=?");
		return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), OrderCallbackLog.class, orderId);
	}

}
