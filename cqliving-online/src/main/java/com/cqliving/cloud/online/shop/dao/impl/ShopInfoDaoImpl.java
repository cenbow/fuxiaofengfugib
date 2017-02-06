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

import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.shop.dao.ShopInfoDaoCustom;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月28日
 */
@Repository
public class ShopInfoDaoImpl implements ShopInfoDaoCustom {
	
//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<ShopInfoDto> queryForScrollPageByDistance(ScrollPage<ShopInfoDto> scrollPage, Map<String, Object> map, double lat, double lng) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	getDistance(a.lat, a.lng, " + lat + ", " + lng + ") distance, "
//				+ "	b.name info_label "
//				+ "FROM "
//				+ "	shop_info a "
//				+ "LEFT JOIN info_lable b ON a.info_label_id = b.id");
		sql.append(""
				+ "SELECT "
				+ "	t1.* "
				+ "FROM ("
				+ "	SELECT "
				+ "		a.*, "
				+ "		getDistance(a.lat, a.lng, " + lat + ", " + lng + ") distance, "
				+ "		b.name info_label "
				+ "	FROM "
				+ "		shop_info a "
				+ "	LEFT JOIN info_lable b ON a.info_label_id = b.id"
				+ ") t1");
//		mysqlPagedJdbcTemplate.queryPage(ShopInfoDto.class, scrollPage, sql.toString(), map);
		mysqlPagedJdbcTemplateV2.queryPage(ShopInfoDto.class, scrollPage, sql.toString(), map);
		return scrollPage;
	}

	@Override
	public ScrollPage<ShopInfoDto> queryForScrollPage(ScrollPage<ShopInfoDto> scrollPage, Map<String, Object> map, String columnName) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name info_label "
				+ "FROM "
				+ "	shop_info a "
				+ "LEFT JOIN info_lable b ON a.info_label_id = b.id");
//		mysqlPagedJdbcTemplate.queryPage(ShopInfoDto.class, scrollPage, sql.toString(), map);
		mysqlPagedJdbcTemplateV2.queryPage(ShopInfoDto.class, scrollPage, sql.toString(), map);
		return scrollPage;
	}

	/**
     * 分页查询商情信息
        SELECT a.*,b.id recommend_id FROM shop_info a LEFT JOIN recommend_info b ON a.id = b.source_id AND b.source_type = 3
     * @Description 
     * @Company 
     * @parameter 
     * @return
     */
    @Override
    public PageInfo<ShopInfoListDto> queryByPage(PageInfo<ShopInfoListDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.id recommend_id FROM shop_info a LEFT JOIN recommend_info b ON a.id = b.source_id ");
        sql.append(" AND b.source_type =  ").append(RecommendInfo.SOURCETYPE3).append(" ");
        mysqlPagedJdbcTemplateV2.queryForPage(ShopInfoListDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
}
