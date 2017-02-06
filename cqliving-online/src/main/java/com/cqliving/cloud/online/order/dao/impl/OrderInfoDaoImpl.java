package com.cqliving.cloud.online.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.order.dao.OrderInfoDaoCustom;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月22日
 */
public class OrderInfoDaoImpl implements OrderInfoDaoCustom{

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	@Override
	public ScrollPage<OrderInfo> queryScrollPage(ScrollPage<OrderInfo> scrollPage, Map<String, Object> conditions) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from order_info");
		return mysqlPagedJdbcTemplateV2.queryPage(OrderInfo.class, scrollPage, sql.toString(), conditions);
	}

	@Override
	public PageInfo<OrderInfoDto> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		StringBuilder sql = new StringBuilder("select * from order_info");
//		sql.append("select");
//		sql.append(" a.id, a.app_id, a.user_id, a.order_no, a.order_amount, a.express_company, a.express_no, a.shipping_fare, a.descn, a.payfor_status, a.status, a.user_name, a.user_phone, a.receiver_address, a.receiver_name, a.receiver_phone, a.pay_mode, a.create_time, a.updater_id, a.operate_time, a.pay_account");
//		sql.append(", b.id as order_goods_id, b.goods_id, b.is_evaluate, b.is_refund, b.goods_name, b.goods_price, b.goods_image_url, b.quantity, b.original_price");
//		sql.append(" from order_info a left join order_goods b on a.id=b.order_id");
		mysqlPagedJdbcTemplateV2.queryForPage(OrderInfoDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

}
