package com.cqliving.cloud.online.analysis.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.tool.common.Response;

/**
 * 资讯统计表 Service
 * Date: 2016-11-08 14:27:42
 * @author Code Generator
 */
public interface AnalysisNewsService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AnalysisNews>> queryForPage(PageInfo<AnalysisNews> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AnalysisNews> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AnalysisNews domain);
	/** @author Code Generator *****end*****/
	public Response<Map<String,List<Map<String,String>>>> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap);
	
	//调用存储过程统计新闻
	public Response<Void> callProcedure();
}
