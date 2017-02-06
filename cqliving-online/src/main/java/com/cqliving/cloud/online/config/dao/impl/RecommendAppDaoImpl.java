/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.config.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.config.dao.RecommendAppDaoCustom;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月26日
 */
@Repository
public class RecommendAppDaoImpl implements RecommendAppDaoCustom {
	
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<RecommendAppDto> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name recommend_app_name, "
				+ "	c.name columns_name "
				+ "FROM "
				+ "	recommend_app a "
				+ "LEFT JOIN app_info b ON a.recommend_app_id = b.id "
				+ "LEFT JOIN app_columns c ON a.columns_id = c.id";
		mysqlPagedJdbcTemplateV2.queryForPage(RecommendAppDto.class, sql.toString(), searchMap, pageInfo, sortMap);
        return pageInfo;
	}

	@Override
	public ScrollPage<RecommendAppDto> queryDtoForPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions) {
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name recommend_app_name, "
				+ "	b.logo app_icon, "
				+ "	c.down_load_url download_url "
				+ "FROM "
				+ "	recommend_app a "
				+ "LEFT JOIN app_info b ON a.recommend_app_id = b.id "
				+ "LEFT JOIN app_config c ON a.recommend_app_id = c.id";
		scrollPage = mysqlPagedJdbcTemplateV2.queryPage(RecommendAppDto.class, scrollPage, sql, conditions);
		return scrollPage;
	}

}