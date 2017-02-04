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
import com.org.weixin.module.zjchj.dao.ZjchjAwardDaoCustom;
import com.org.weixin.module.zjchj.domain.ZjchjAward;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月26日
 */
@Repository
public class ZjchjAwardDaoImpl implements ZjchjAwardDaoCustom {
	
	@Resource(name = "extendJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;

	@Override
	public List<ZjchjAwardDto> queryData(Date beginTime, Date endTime, Map<String, Boolean> sortMap) {
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.level, "
//				+ "	a.award_name AS name, "
//				+ "	COUNT(a.id) AS award_num, "
//				+ "	SUM(a.is_reward) AS reward_num "
//				+ "FROM "
//				+ "	zjchj_user_award a "
//				+ "WHERE "
//				+ "	a.is_true_award = " + ZjchjUserAward.ISTRUEAWARD1 + " ");
//		if (beginTime != null) {
//			sql.append("	AND a.create_time >= '" + DateUtil.toString(beginTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
//		}
//		if (endTime != null) {
//			sql.append("	AND a.create_time <= '" + DateUtil.toString(endTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
//		}
//		sql.append(""
//				+ "GROUP BY "
//				+ "	a.level, "
//				+ "	a.award_name "
//				+ "ORDER BY "
//				+ "	a. LEVEL, "
//				+ "	a.award_name");
		sql.append(""
				+ "SELECT "
				+ "	a.level, "
				+ "	b.name, "
				+ "	COUNT(a.id) AS draw_num, "
				+ "	SUM(CASE WHEN a.is_true_award THEN 1 ELSE 0 END) AS award_num, "
				+ "	SUM(a.is_reward) AS reward_num "
				+ "FROM "
				+ "	zjchj_user_award a "
				+ "LEFT JOIN zjchj_award b ON a.level = b.level AND b.is_true_award = " + ZjchjAward.ISTRUEAWARD1 + " "
				+ "WHERE "
				+ "	a.level < " + ZjchjAward.LEVEL4 + " ");	//只统计正常三个奖项，终极大奖单独统计
		if (beginTime != null) {
			sql.append("	AND a.create_time >= '" + DateUtil.toString(beginTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
		}
		if (endTime != null) {
			sql.append("	AND a.create_time <= '" + DateUtil.toString(endTime, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + "' ");
		}
		sql.append(""
				+ "GROUP BY "
				+ "	a.level "
				+ "ORDER BY "
				+ "	a.level");
		Map<String, Object> map = Maps.newHashMap();
		return mysqlPagedJdbcTemplate.queryForList(ZjchjAwardDto.class, sql.toString(), map, sortMap);
	}

}