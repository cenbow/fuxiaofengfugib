package com.cqliving.cloud.online.analysis.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananTimes;

/**
 * 南岸讲学赞栏目统计期数表 Manager
 * Date: 2016-11-10 09:23:57
 * @author Code Generator
 */
public interface AnalysisNananTimesManager extends EntityService<AnalysisNananTimes> {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月10日
	 * @param beginDate
	 * @param endDate
	 * @param columnsId
	 */
	void createTimes(String beginDate, String endDate, Long columnsId);
	
}