/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.topic.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.topic.dao.TopicInfoDaoCustom;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月26日
 */
@Repository
public class TopicInfoDaoImpl implements TopicInfoDaoCustom {
	
//	@Autowired
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<TopicInfo> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Map<String, Object> searchMap, String name) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT a.* FROM topic_info a");
//		if (StringUtils.isNotBlank(name)) {
//			sql.append(" WHERE (a.name LIKE '%" + name + "%' OR a.creator LIKE '%" + name + "%')");
//		}
		sql.append("SELECT a.* FROM topic_info a");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" WHERE (a.name LIKE '%" + name + "%' OR a.creator LIKE '%" + name + "%')");
		}
//		mysqlPagedJdbcTemplate.queryPage(TopicInfo.class, scrollPage, sql.toString(), searchMap);
		mysqlPagedJdbcTemplateV2.queryPage(TopicInfo.class, scrollPage, sql.toString(), searchMap);
		return scrollPage;
	}

}
