/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
/**
 * 
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.account.dao.UserPraiseDaoCustom;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author Tangtao on 2016年5月2日
 */
@Repository
public class UserPraiseDaoImpl implements UserPraiseDaoCustom {
	
//	@Resource(name = "onlinePagedJdbcTemplate")
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<UserPraise> getMyPraisePage(ScrollPage<UserPraise> page, Long appId, Long userId) {
		//查询数据
//		String sql = "SELECT * FROM User_Praise	ORDER BY id DESC";
		String sql = "SELECT up.* FROM user_praise up";
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_userId", userId);
		conditions.put("EQ_status", UserPraise.STATUS3);
//		mysqlPagedJdbcTemplate.queryPage(UserPraise.class, page, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(UserPraise.class, page, sql.toString(), conditions);
		return page;
	}

}