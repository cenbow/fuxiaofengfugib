/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.tourism.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.tourism.dao.TourismInfoDaoCustom;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年8月24日
 */
@Repository
public class TourismInfoDaoImpl implements TourismInfoDaoCustom {
	
//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<TourismInfoDto> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.name region_name "
//				+ "FROM	"
//				+ "	tourism_info a "
//				+ "LEFT JOIN config_region b ON a.region_code = b.code "
//				+ "	AND a.app_id = b.app_id "
//				+ "ORDER BY "
//				+ "	a.sort_no, "
//				+ "	a.update_time DESC, "
//				+ "	a.id DESC");
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name region_name "
				+ "FROM	"
				+ "	tourism_info a "
				+ "LEFT JOIN config_region b ON a.region_code = b.code "
				+ "	AND a.app_id = b.app_id");
//		mysqlPagedJdbcTemplate.queryForPage(TourismInfoDto.class, sql.toString(), searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(TourismInfoDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}
	
	@Override
	public ScrollPage<TourismInfoDto> queryForScrollPageByDistance(ScrollPage<TourismInfoDto> scrollPage, Map<String, Object> map, double lat, double lng) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	getDistance(a.lat, a.lng, " + lat + ", " + lng + ") distance "
//				+ "FROM "
//				+ "	tourism_info a ");
		//如果无经纬度，则坐标默认为南极
		sql.append(""
				+ "SELECT "
				+ "	t1.* "
				+ "FROM ("
				+ "	SELECT "
				+ "		a.*, "
				+ "		getDistance(IFNULL(a.lat, -74), IFNULL(a.lng, 0), " + lat + ", " + lng + ") distance "
				+ "	FROM "
				+ "		tourism_info a"
				+ ") t1 ");
//		mysqlPagedJdbcTemplate.queryPage(TourismInfoDto.class, scrollPage, sql.toString(), map);
		mysqlPagedJdbcTemplateV2.queryPage(TourismInfoDto.class, scrollPage, sql.toString(), map);
		return scrollPage;
	}

}