/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.joke.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.joke.dao.JokeInfoDaoCustom;
import com.cqliving.cloud.online.joke.dto.JokeInfoDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
@Repository
public class JokeInfoDaoImpl implements JokeInfoDaoCustom {

	@Resource(name = "onlinePagedJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	@Override
	public ScrollPage<JokeInfoDto> queryForScrollPage(ScrollPage<JokeInfoDto> scrollPage, Map<String, Object> conditions, Long praiseUserId) {
		//查询数据
//		String sql = ""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	CASE "
//				+ "		WHEN b.type = 0 THEN 0 "
//				+ "		WHEN b.type = 1 THEN 1 "
//				+ "		ELSE -1 "
//				+ "	END is_praised "
//				+ "FROM	"
//				+ "	joke_info a "
//				+ "LEFT JOIN user_praise b ON b.source_id = a.id "
//				+ "	AND b.source_type = " + UserPraise.SOURCETYPE5 + " "
//				+ "	AND b.source_user_id = " + praiseUserId + " "
//				+ "ORDER BY "
//				+ "	a.online_time DESC, "
//				+ "	a.id DESC";
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	CASE "
				+ "		WHEN b.type = 0 THEN 0 "
				+ "		WHEN b.type = 1 THEN 1 "
				+ "		ELSE -1 "
				+ "	END is_praised "
				+ "FROM	"
				+ "	joke_info a "
				+ "LEFT JOIN user_praise b ON b.source_id = a.id "
				+ "	AND b.source_type = " + UserPraise.SOURCETYPE5 + " "
				+ "	AND b.source_user_id = " + praiseUserId;
//		mysqlPagedJdbcTemplate.queryPage(JokeInfoDto.class, scrollPage, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(JokeInfoDto.class, scrollPage, sql.toString(), conditions);
		return scrollPage;
	}

}