package com.cqliving.cloud.online.analysis.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.analysis.dao.AnalysisNananTimesDao;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananTimes;
import com.cqliving.cloud.online.analysis.manager.AnalysisNananTimesManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("analysisNananTimesManager")
public class AnalysisNananTimesManagerImpl extends EntityServiceImpl<AnalysisNananTimes, AnalysisNananTimesDao> implements AnalysisNananTimesManager {

	@Override
	public void createTimes(String beginDate, String endDate, Long columnsId) {
		getEntityDao().createTimes(beginDate, endDate, columnsId);
	}
	
}