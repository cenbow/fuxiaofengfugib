/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.scheduler.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.analysis.service.AnalysisNewsService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月9日
 */
@Component("statisticsNewsTask")
public class StatisticsNewsTask extends TaskScheduler{

	private static final Logger logger = LoggerFactory.getLogger(StatisticsNewsTask.class);
	
	@Autowired
	AnalysisNewsService analysisNewsService; 
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.scheduler.task.TaskScheduler#execute()
	 */
	@Override
	public void execute() {
		logger.info("开始统计新闻浏览量收藏量点赞量分享量..................");
		
		analysisNewsService.callProcedure();
		
		logger.info("统计新闻浏览量收藏量点赞量分享量........结束..........");
	}

}
