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

import com.cqliving.cloud.online.analysis.dao.AnalysisAppColumnsDaoCustom;
import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月11日
 */
public class AnalysisAppColumnsDaoImpl implements AnalysisAppColumnsDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.dao.AnalysisAppColumnsDaoCustom#callProcedure()
	 */
	@Override
	public void callProcedure() {
		String nowStr = DateUtil.formatDateNowDefault();
		String dayStr = DateUtil.formatDate(Dates.now(), DateUtil.FORMAT_YYYY_MM_DD);
		mysqlPagedJdbcTemplateV2.execute(String.format("call app_columns_statistics('%s','%s')", dayStr,nowStr));
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.dao.AnalysisAppColumnsDaoCustom#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public List<AnalysisAppColumns> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from analysis_app_columns");
		
		return mysqlPagedJdbcTemplateV2.queryForList(AnalysisAppColumns.class, sql.toString(), conditions, sortMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.dao.AnalysisAppColumnsDaoCustom#findPageInfo(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	public PageInfo<AnalysisAppColumnsDto> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,
			Map<String, Object> conditions) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ac.app_columns_id,ac.APP_ID,ac.comment_num,ac.favorite_num,ac.share_num,");
		sql.append("ac.statistics_date,ac.user_type,ac.view_num,ai.`name` app_name,acol.`name` app_columns_name ");
		sql.append("from analysis_app_columns ac ,app_info ai,app_columns acol ");
		sql.append("where ac.app_columns_id = acol.ID and ac.APP_ID = ai.ID ${WHERE} order by ac.statistics_date DESC,ac.app_columns_id");
		mysqlPagedJdbcTemplateV2.queryForPage(AnalysisAppColumnsDto.class, sql.toString(), conditions, pageInfo);
		return pageInfo;
	}
}
