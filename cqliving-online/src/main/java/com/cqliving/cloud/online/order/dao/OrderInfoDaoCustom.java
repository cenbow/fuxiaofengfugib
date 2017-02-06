package com.cqliving.cloud.online.order.dao;

import java.util.Map;

import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月22日
 */
public interface OrderInfoDaoCustom {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月22日
	 * @param scrollPage
	 * @param conditions
	 * @param payforStatus
	 * @return
	 */
	ScrollPage<OrderInfo> queryScrollPage(ScrollPage<OrderInfo> scrollPage, Map<String, Object> conditions);
	
	/**
	 * Title:cms分页查询
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月6日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<OrderInfoDto> queryForPage(PageInfo<OrderInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
}
