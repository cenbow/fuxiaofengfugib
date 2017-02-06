package com.cqliving.cloud.scheduler.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.analysis.service.AnalysisInformationService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:统计任务
 * <p>Description:</p>
 * @author yuwu on 2016年6月25日
 */
@Component("statisticsTask")
public class StatisticsTask extends TaskScheduler{
	private static final Logger logger = LoggerFactory.getLogger(StatisticsTask.class);

    @Autowired
    private AnalysisInformationService analysisInformationService;

    /**
	 * <p>Description:</p>
	 * @see com.cqliving.cloud.scheduler.task.TaskScheduler#execute()
	 * @author yuwu on 2016年6月25日
	 */
	@Override
	public void execute() {
		logger.info("*****************编辑考核统计数据开始*******************");
		analysisInformationService.callProcInfoStatistics();
		logger.info("*****************编辑考核统计数据结束*******************");
	}
}
