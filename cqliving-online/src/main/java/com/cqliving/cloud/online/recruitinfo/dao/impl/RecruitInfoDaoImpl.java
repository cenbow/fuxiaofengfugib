/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.recruitinfo.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.recruitinfo.dao.RecruitInfoDaoCustom;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
@Repository
public class RecruitInfoDaoImpl implements RecruitInfoDaoCustom {
	
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<RecruitInfo> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.* "
				+ "FROM "
				+ "	recruit_info a");
		mysqlPagedJdbcTemplateV2.queryPage(RecruitInfo.class, scrollPage, sql.toString(), conditions);
		return scrollPage;
	}

}