/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.tourism.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.tourism.dao.TourismSpecialDaoCustom;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月24日
 */
public class TourismSpecialDaoImpl implements TourismSpecialDaoCustom {

	@Autowired
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.dao.TourismSpecialDaoCustom#queryForSpecialSub(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<TourismInfoDto> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		StringBuilder sql = new StringBuilder();
		sql.append("select tour.app_id,tour.create_time,tour.ID,tour.`name`,tour.region_code,");
		sql.append("tour.type,tour.place,tour.price,tour.image_url as image_url,");
		sql.append("special.id as tourism_special_id,special.sort_no as tourism_special_sort_no,");
		sql.append("special.`status` as status,special.tourism_id as tourism_id,region.`name` as region_name ");
		Object lng = conditions.get("lng");
		Object lat = conditions.get("lat");
		conditions.remove("lng");
		conditions.remove("lat");
		if(null != lng && null != lat){
			sql.append(",getDistance(tour.lat, tour.lng, " + lat + ", " + lng + ") distance ");
		}
		sql.append("from  tourism_special as special,config_region region,tourism_info tour ");
		sql.append("where special.ref_tourism_id = tour.ID and region.`code` = tour.region_code and tour.status=3 ");
		mysqlPagedJdbcTemplateV2.queryForPage(TourismInfoDto.class, sql.toString(), conditions, pageInfo, orderMap);
		return pageInfo;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.dao.TourismSpecialDaoCustom#queryForNoJoinSpecial(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<TourismInfoDto> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {

        StringBuilder sql = new StringBuilder();
        sql.append("select tour.*,region.`name` as region_name from tourism_info tour,config_region region where ");
        sql.append("region.code=tour.region_code and tour.type = 1 and tour.`status`=3 and ");
        sql.append("not EXISTS  ");
		sql.append("(select * from tourism_special special where special.ref_tourism_id = tour.ID and special.tourism_id= ");
		
		Long specialId = (Long) conditions.get("EQ_specialId");
		sql.append(specialId).append(")");
		conditions.remove("EQ_specialId");
		
        mysqlPagedJdbcTemplate.queryForPage(TourismInfoDto.class, sql.toString(), conditions, pageInfo, orderMap);
		return pageInfo;
	}

	@Override
	public List<TourismInfoDto> queryForSubList(Long appId, Long tourismId, double lat, double lng) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	getDistance(a.lat, a.lng, " + lat + ", " + lng + ") distance "
				+ "FROM "
				+ "	tourism_special b, "
				+ "	tourism_info a "
				+ "WHERE b.ref_tourism_id = a.id "
				+ "	AND b.tourism_id = ? "
				+ "	AND b.status = " + TourismSpecial.STATUS3 + " "
				+ "	AND a.status = " + TourismInfo.STATUS3 + " "
				+ "ORDER BY b.sort_no");
//		return mysqlPagedJdbcTemplate.queryForList(TourismInfoDto.class, sql.toString(), tourismId);
		return mysqlPagedJdbcTemplateV2.queryForList(TourismInfoDto.class, sql.toString(), tourismId);
	}

}
