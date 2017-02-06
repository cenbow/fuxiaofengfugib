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

import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:
 * <p>Description:同步缓存中的新闻浏览量</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月18日
 */
@Component("syncViewCountTask")
public class SyncViewCountTask extends TaskScheduler{
	
	private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoTask.class);
	@Autowired
	InformationService informationService;

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.scheduler.task.TaskScheduler#execute()
	 */
	@Override
	public void execute() {
		logger.info("同步新闻浏览量开始。。。。。。。。。。。。");
		informationService.syncViewReplyCount(false,null);
		logger.info("同步新闻浏览量。。。。。结束。。。。。。。");
	}
}
