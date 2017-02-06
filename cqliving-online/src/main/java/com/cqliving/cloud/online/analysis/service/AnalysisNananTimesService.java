package com.cqliving.cloud.online.analysis.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisNananTimes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 南岸讲学赞栏目统计期数表 Service
 * Date: 2016-11-10 09:23:57
 * @author Code Generator
 */
public interface AnalysisNananTimesService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AnalysisNananTimes>> queryForPage(PageInfo<AnalysisNananTimes> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AnalysisNananTimes> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AnalysisNananTimes domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月10日
	 * @return
	 */
	Response<List<AnalysisNananTimes>> getAll();
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月10日
	 * @param beginDate
	 * @param endDate
	 * @param columnsId
	 * @return
	 */
	Response<Void> createTimes(String beginDate, String endDate, Long columnsId);
	
}