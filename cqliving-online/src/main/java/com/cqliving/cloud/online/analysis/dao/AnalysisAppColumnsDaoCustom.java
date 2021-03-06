/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.analysis.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月11日
 */
public interface AnalysisAppColumnsDaoCustom {

	public void callProcedure();
	
	public List<AnalysisAppColumns> findByConditions(Map<String,Object> conditions,Map<String,Boolean> sortMap);
	
	public PageInfo<AnalysisAppColumnsDto> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,Map<String,Object> conditions); 
}
