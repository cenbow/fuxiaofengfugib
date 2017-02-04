/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.zjchj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.org.weixin.module.zjchj.dao.ZjchjUserGoodsInfoDaoCustom;
import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月24日
 */
@Repository
public class ZjchjUserGoodsInfoDaoImpl implements ZjchjUserGoodsInfoDaoCustom {
	
	@Resource(name = "extendJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;

	@Override
	public List<ZjchjUserGoodsInfo> getStatistics() {
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	user_id, "
				+ "	COUNT(id) goods_id "
				+ "FROM "
				+ "	zjchj_user_goods_info "
				+ "GROUP BY "
				+ "	user_id");
		return mysqlPagedJdbcTemplate.queryForList(ZjchjUserGoodsInfo.class, sql.toString(), null, null);
	}

	@Override
	public Long getCountByQuantity(Integer quantity) {
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	COUNT(user_id) "
				+ "FROM "
				+ "	("
				+ "		SELECT "
				+ "			user_id, "
				+ "			COUNT(id) quantity "
				+ "		FROM "
				+ "			zjchj_user_goods_info "
				+ "		GROUP BY "
				+ "			user_id"
				+ "	) t "
				+ "WHERE "
				+ "	quantity >= ?");
		return mysqlPagedJdbcTemplate.queryForCount(sql.toString(), quantity);
	}

	@Override
	public List<ZjchjAwardDto> getDistribution() {
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	quantity AS virtual_num, "
				+ "	COUNT(user_id) AS actual_num "
				+ "FROM "
				+ "	("
				+ "		SELECT "
				+ "			user_id, "
				+ "			COUNT(id) quantity "
				+ "		FROM "
				+ "			zjchj_user_goods_info "
				+ "		GROUP BY "
				+ "			user_id"
				+ "	) t "
				+ "GROUP BY "
				+ "	quantity "
				+ "ORDER BY "
				+ "	quantity");
		return mysqlPagedJdbcTemplate.queryForList(ZjchjAwardDto.class, sql.toString(), null, null);
	}

}