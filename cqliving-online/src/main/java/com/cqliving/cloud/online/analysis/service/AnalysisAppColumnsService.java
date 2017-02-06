package com.cqliving.cloud.online.analysis.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.tool.common.Response;

/**
 * 资讯栏目统计表 Service
 * Date: 2016-11-11 11:56:02
 * @author Code Generator
 */
public interface AnalysisAppColumnsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AnalysisAppColumns>> queryForPage(PageInfo<AnalysisAppColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AnalysisAppColumns> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AnalysisAppColumns domain);
	/** @author Code Generator *****end*****/
	//调用存储过程统计新闻栏目
	public Response<Void> callProcedure();
	
	public Response<Map<String,List<Map<String,String>>>> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap);
	
	public Response<PageInfo<AnalysisAppColumnsDto>> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,
			Map<String, Object> conditions);
}
