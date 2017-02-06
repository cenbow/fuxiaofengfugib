/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.shoot.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.shoot.dao.ShootInfoDaoCustom;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月8日
 */
@Repository
public class ShootInfoDaoImpl implements ShootInfoDaoCustom {

//	@Resource(name = "onlinePagedJdbcTemplate")
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	@Override
	public ScrollPage<ShootInfoDto> queryForScrollPage(ScrollPage<ShootInfoDto> scrollPage, Map<String, Object> conditions, Long praiseUserId) {
		//查询数据
//		String sql = ""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.img_url user_img, "
//				+ "	b.name nickname, "
//				+ "	GROUP_CONCAT(c.image_url ORDER BY c.id) imgs, "
//				+ "	GROUP_CONCAT(c.description ORDER BY c.id) descs "
//				+ "FROM	"
//				+ "	shoot_info a "
//				+ "LEFT JOIN user_info b ON a.user_id = b.id "
//				+ "LEFT JOIN shoot_images c ON a.id = c.shoot_id "
//				+ "GROUP BY "
//				+ "	a.id "
//				+ "ORDER BY "
//				+ "	a.id DESC";
		String sql = ""
//				+ "SELECT "
//				+ "	t1.* "
//				+ "FROM ("
				+ "	SELECT "
				+ "		a.*, "
				+ "		b.img_url user_img, "
				+ "		b.name nickname, "
				+ "		GROUP_CONCAT(c.image_url ORDER BY c.id) imgs, "
				+ "		GROUP_CONCAT(c.description ORDER BY c.id) descs "
				+ "	FROM	"
				+ "		shoot_info a "
				+ "	LEFT JOIN user_info b ON a.user_id = b.id "
				+ "	LEFT JOIN shoot_images c ON a.id = c.shoot_id "
				+ "	${WHERE} GROUP BY "
				+ "		a.id" ;
//				+ ") t1";
//		mysqlPagedJdbcTemplate.queryPage(ShootInfoDto.class, scrollPage, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(ShootInfoDto.class, scrollPage, sql.toString(), conditions);
		return scrollPage;
	}

	@Override
	public PageInfo<ShootInfoDto> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		//查询数据
//		String sql = ""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.user_name login_name, "
//				+ "	c.name nickname "
//				+ "FROM	"
//				+ "	shoot_info a "
//				+ "LEFT JOIN user_account b ON a.user_id = b.id "
//				+ "LEFT JOIN user_info c ON a.user_id = c.id "
//				+ "ORDER BY "
//				+ "	a.id DESC";
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.user_name login_name, "
				+ "	c.name nickname "
				+ "FROM	"
				+ "	shoot_info a "
				+ "LEFT JOIN user_account b ON a.user_id = b.id "
				+ "LEFT JOIN user_info c ON a.user_id = c.id";
//		mysqlPagedJdbcTemplate.queryForPage(ShootInfoDto.class, sql, searchMap, pageInfo, sortMap);
		mysqlPagedJdbcTemplateV2.queryForPage(ShootInfoDto.class, sql.toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}

}
