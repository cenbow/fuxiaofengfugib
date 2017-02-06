package com.cqliving.cloud.online.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.order.dao.OrderRefundDaoCustom;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderRefundDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月5日
 */
public class OrderRefundDaoImpl implements OrderRefundDaoCustom {
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	@Override
	public ScrollPage<OrderRefundDto> queryScrollPage(ScrollPage<OrderRefundDto> scrollPage, Map<String, Object> conditions, Long appId, Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select");
		sql.append(" a.id, a.order_id, a.order_no, a.goods_id, a.goods_name, a.goods_price, a.user_pay_account, a.refund_amount, a.quantity, a.receipt_no, a.refund_images_url, a.refund_reason, a.create_time, a.refuse_time, a.refuse_reason, a.agree_time, a.agree_reason, a.original_price, a.goods_image_url");
		sql.append(", b.app_id, b.user_id, b.payfor_status as order_payfor_status, b.status, b.create_time as orderCreateTime");
		sql.append(", c.refund_status");
		sql.append(" from order_refund as a left join order_info b on a.order_id=b.id");
		sql.append(" left join order_goods as c on a.order_id=c.order_id and a.goods_id=c.goods_id");
		sql.append(" where b.status=").append(OrderInfo.STATUS3);
		return mysqlPagedJdbcTemplateV2.queryPage(OrderRefundDto.class, scrollPage, sql.toString(), conditions);
	}

}
