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

import com.cqliving.cloud.online.shop.dao.ShopCategoryDaoCustom;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月17日
 */
@Repository
public class ShopCategoryDaoImpl implements ShopCategoryDaoCustom {
	
//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<ShopCategoryDto> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.name type_name "
//				+ "FROM	"
//				+ "	shop_category a "
//				+ "LEFT JOIN shop_type b ON a.type_id = b.id "
//				+ "ORDER BY "
//				+ "	a.sort_no, "
//				+ "	a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name type_name "
				+ "FROM	"
				+ "	shop_category a "
				+ "LEFT JOIN shop_type b ON a.type_id = b.id");
//		mysqlPagedJdbcTemplate.queryForPage(ShopCategoryDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(ShopCategoryDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

}