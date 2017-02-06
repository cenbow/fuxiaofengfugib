/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.account.dao.UserSmsLogDaoCustom;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月9日
 */
@Repository
public class UserSmsLogDaoImpl implements UserSmsLogDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<SmsStatisticsDto> getStatistic(PageInfo<SmsStatisticsDto> pageInfo) {
		String sql = ""
				+ "SELECT "
				+ "	t1.* "
				+ "FROM ("
				+ "	SELECT "
				+ "		a.app_id, "
				+ "		b.name app_name, "
				+ "		COUNT(a.id) as total_send, "
				+ "		SUM(CASE WHEN a.type = 0 THEN 1 ELSE 0 END) type0, "
				+ "		SUM(CASE WHEN a.type = 1 THEN 1 ELSE 0 END) type1, "
				+ "		SUM(CASE WHEN a.type = 2 THEN 1 ELSE 0 END) type2, "
				+ "		SUM(CASE WHEN a.type = 3 THEN 1 ELSE 0 END) type3, "
				+ "		SUM(CASE WHEN a.type = 4 THEN 1 ELSE 0 END) type4 "
				+ "	FROM "
				+ "		user_sms_log a "
				+ "	LEFT JOIN app_info b ON a.app_id = b.id "
				+ "		GROUP BY "
				+ "		a.app_id "
				+ ") t1";
		Map<String, Boolean> orders = Maps.newLinkedHashMap();
		orders.put("app_id", true);
        mysqlPagedJdbcTemplateV2.queryForPage(SmsStatisticsDto.class, sql.toUpperCase().replaceAll("	", " "), null, pageInfo, orders);
        return pageInfo;
	}

}