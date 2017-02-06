package com.cqliving.cloud.online.analysis.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.analysis.dao.AnalysisNananDataDao;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananData;
import com.cqliving.cloud.online.analysis.manager.AnalysisNananDataManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("analysisNananDataManager")
public class AnalysisNananDataManagerImpl extends EntityServiceImpl<AnalysisNananData, AnalysisNananDataDao> implements AnalysisNananDataManager {
}
