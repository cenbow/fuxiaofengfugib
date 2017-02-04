/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.zjchj.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;
import com.org.weixin.module.zjchj.dao.ZjchjBillInfoDaoCustom;
import com.org.weixin.module.zjchj.dto.ZjchjBillInfoDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月26日
 */
@Repository
public class ZjchjBillInfoDaoImpl implements ZjchjBillInfoDaoCustom {
	
	@Resource(name="extendJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;

	@Override
	public List<ZjchjBillInfoDto> getStatistics(Date beginTime, Date endTime) {
		Map<String, Object> map = Maps.newHashMap();
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("consume_time", false);
		sortMap.put("shop_name", true);
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.shop_name, "
				+ "	a.items_name,	"
				+ "	COUNT(a.items_name) consume_time, "
				+ "	COUNT(DISTINCT a.open_id) consume_people "
				+ "FROM "
				+ "	zjchj_bill_info a "
				+ "WHERE 1 = 1 ");
		if (beginTime != null) {
			sql.append("	AND a.create_time >= '" + DateUtil.toString(beginTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
		}
		if (endTime != null) {
			sql.append("	AND a.create_time <= '" + DateUtil.toString(endTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
		}
		sql.append(""
				+ "GROUP BY "
				+ "	a.shop_name, "
				+ "	a.items_name");
		return mysqlPagedJdbcTemplate.queryForList(ZjchjBillInfoDto.class, sql.toString(), map, sortMap);
	}

}