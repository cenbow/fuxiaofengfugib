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

import com.cqliving.cloud.online.analysis.domain.AnalysisNews;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月8日
 */
public interface AnalysisNewsDaoCustom {

	//根据条件查找数据
	public List<AnalysisNews> findByConditions(Map<String,Object> conditions,Map<String,Boolean> sortMap);
	
	public void callProcedure();
}
