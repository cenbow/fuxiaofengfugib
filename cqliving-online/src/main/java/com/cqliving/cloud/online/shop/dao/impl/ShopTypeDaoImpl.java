/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.shop.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.shop.dao.ShopTypeDaoCustom;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月18日
 */
@Repository
public class ShopTypeDaoImpl implements ShopTypeDaoCustom {
	
//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<ShopTypeDto> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.name column_name "
//				+ "FROM	"
//				+ "	shop_type a "
//				+ "LEFT JOIN app_columns b ON a.app_columns_id = b.id "
//				+ "ORDER BY "
//				+ "	a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name column_name "
				+ "FROM	"
				+ "	shop_type a "
				+ "LEFT JOIN app_columns b ON a.app_columns_id = b.id");
//		mysqlPagedJdbcTemplate.queryForPage(ShopTypeDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(ShopTypeDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

}
