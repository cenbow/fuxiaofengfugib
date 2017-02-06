/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.analysis.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.analysis.dao.AnalysisNewsDaoCustom;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.tool.common.util.date.DateUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月8日
 */
public class AnalysisNewsDaoImpl implements AnalysisNewsDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.dao.AnalysisNewsDaoCustom#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public List<AnalysisNews> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from analysis_news");
		
		return mysqlPagedJdbcTemplateV2.queryForList(AnalysisNews.class, sql.toString(), conditions, sortMap);
	}
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.dao.AnalysisNewsDaoCustom#callProcedure()
	 */
	@Override
	public void callProcedure() {
		String nowStr = DateUtil.formatDateNowDefault();
		mysqlPagedJdbcTemplateV2.execute(String.format("call analysis_news_statistics('%s','%s')", nowStr,nowStr));
	}

}
