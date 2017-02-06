package com.cqliving.cloud.online.order.dao;

import java.util.Map;

import com.cqliving.cloud.online.order.dto.OrderRefundDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月5日
 */
public interface OrderRefundDaoCustom {

	/**
	 * Title:订单中心-退款列表
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月5日
	 * @param scrollPage
	 * @param conditions
	 * @param appId
	 * @param userId
	 * @return
	 */
	ScrollPage<OrderRefundDto> queryScrollPage(ScrollPage<OrderRefundDto> scrollPage, Map<String, Object> conditions, Long appId, Long userId);
}
