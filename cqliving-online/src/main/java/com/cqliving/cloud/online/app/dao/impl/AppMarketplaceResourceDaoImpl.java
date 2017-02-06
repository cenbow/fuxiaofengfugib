/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
/**
 * 
 */
package com.cqliving.cloud.online.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.app.dao.AppMarketplaceResourceDaoCustom;
import com.cqliving.cloud.online.app.dto.AppMarketplaceResourceDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月1日
 */
@Repository
public class AppMarketplaceResourceDaoImpl implements AppMarketplaceResourceDaoCustom {
	
//	@Autowired
//    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public List<AppMarketplaceResourceDto> getUpdateImgs(Long appId, Integer type, Integer version) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.version_no, "
				+ "FROM	"
				+ "	app_marketplace_resource a "
				+ "LEFT JOIN app_image_version b ON a.version_id = b.id "
				+ "WHERE "
				+ "	b.version_no > ? "
				+ "AND a.appId = ? "
				+ "AND b.type LIKE ? "
				+ "ORDER BY "
				+ "	b.version_no desc,	"
				+ "	a.sort_no";
//		return mysqlPagedJdbcTemplate.queryForList(AppMarketplaceResourceDto.class, sql, version, appId, "%," + type + ",%");
		return mysqlPagedJdbcTemplateV2.queryForList(AppMarketplaceResourceDto.class, sql, version, appId, "%," + type + ",%");
	}

}
