package com.cqliving.cloud.online.analysis.service;

import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisNananData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 南岸讲学赞栏目统计 Service
 * Date: 2016-11-10 09:23:31
 * @author Code Generator
 */
public interface AnalysisNananDataService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AnalysisNananData>> queryForPage(PageInfo<AnalysisNananData> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AnalysisNananData> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AnalysisNananData domain);
	/** @author Code Generator *****end*****/
	
}