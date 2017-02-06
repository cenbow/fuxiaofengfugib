package com.cqliving.cloud.online.analysis.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯栏目统计表 Manager
 * Date: 2016-11-11 11:56:02
 * @author Code Generator
 */
public interface AnalysisAppColumnsManager extends EntityService<AnalysisAppColumns> {
	
	public void callProcedure();
	
	public Map<String, List<Map<String, String>>> findByConditions(Map<String, Object> conditions,Map<String, Boolean> sortMap);
	
	public PageInfo<AnalysisAppColumnsDto> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,
			Map<String, Object> conditions);
}
