/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.info.dao.InfoCorrelationDaoCustom;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月1日
 */
@Repository
public class InfoCorrelationDaoImpl implements InfoCorrelationDaoCustom {
	
//	@Resource(name = "onlinePagedJdbcTemplate")
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public List<InfoClassifyDto> getCorrelation(Long appId, Long infoClassifyId) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.info_classify_id, "
				+ "	a.sort_no, "
				+ "	a.app_id, "
				+ "	b.id,	"
				+ "	b.information_id,	"
				+ "	b.list_view_type, "
				+ "	b.title, "
				+ "	b.list_title, "	//资讯列表标题 By Tangtao 2016-11-30
				+ "	b.online_time, "
				+ "	b.status, "
				+ "	b.comment_type, "
				+ "	c.content_url, "
				+ "	c.context_type, "
				+ "	c.info_source, "
				+ "	c.reply_count, "
				+ "	c.view_count detail_view_count, "
				+ "	c.type, "
				+ "	c.info_label, "
				+ "	c.init_count, "
				+ "	c.add_type, "
				+ "	c.top_time, "
				+ "	c.synopsis, "
				+ "	d.image_url "
				+ "FROM	"
				+ "	info_correlation a "
				+ "LEFT JOIN info_classify b ON a.ref_info_classify_id = b.id "
				+ "LEFT JOIN information c ON b.information_id = c.id "
				+ "LEFT JOIN info_classify_list d ON b.id = d.classify_id "
				+ "WHERE "
				+ "	a.theme_id IS NULL "
				+ "	AND b.online_time < NOW()");
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_infoClassifyId", infoClassifyId);
		conditions.put("EQ_status", InfoClassify.STATUS3);
		conditions.put("EQ_appId", appId);
		Map<String, Boolean> orders = Maps.newLinkedHashMap();
		orders.put("sort_no", true);
		orders.put("id", false);
//		return mysqlPagedJdbcTemplate.queryForList(InfoClassifyDto.class, sql.toString(), conditions, orders);
		return mysqlPagedJdbcTemplateV2.queryForList(InfoClassifyDto.class, sql.toString(), conditions, orders);
	}

}