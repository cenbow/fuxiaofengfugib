package com.cqliving.cloud.online.analysis.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯统计表 Manager
 * Date: 2016-11-08 14:27:42
 * @author Code Generator
 */
public interface AnalysisNewsManager extends EntityService<AnalysisNews> {
	
	public Map<String,List<Map<String,String>>> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap);
	
	public void callProcedure();
}
