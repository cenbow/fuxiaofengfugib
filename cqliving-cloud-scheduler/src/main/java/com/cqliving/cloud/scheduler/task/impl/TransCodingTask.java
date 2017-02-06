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

import com.cqliving.cloud.online.info.service.InfoFileService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月31日
 */
@Component("transCodingTask")
public class TransCodingTask extends TaskScheduler{

	@Autowired
	InfoFileService infoFileService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.scheduler.task.TaskScheduler#execute()
	 */
	@Override
	public void execute() {
		
		logger.info("七牛视频转码状态查询-------------->>>>>>>>>>开始");
		
		infoFileService.transCoding();
		
		logger.info("七牛视频转码状态查询-------------->>>>>>>>>>结束");
	}

}
