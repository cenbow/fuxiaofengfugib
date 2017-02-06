/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.analysis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.analysis.dao.AnalysisNananTimesDaoCustom;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月10日
 */
@Repository
public class AnalysisNananTimesDaoImpl implements AnalysisNananTimesDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public void createTimes(String beginDate, String endDate, Long columnsId) {
		mysqlPagedJdbcTemplateV2.execute("call PROC_ANALYSIS_NANAN_TIMES('" + beginDate + "', '" + endDate + "', " +  columnsId + ")");
	}


}