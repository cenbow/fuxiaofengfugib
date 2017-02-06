package com.cqliving.cloud.scheduler.task.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.county.service.CountyHousesService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * 区县楼盘相关任务
 * <p>Title:CountyHousesTask </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2017年1月5日上午9:55:52
 *
 */
@Component("countyHousesTask")
public class CountyHousesTask extends TaskScheduler{
	
	private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoTask.class);
	
	@Autowired
	private CountyHousesService countyHousesService;
	
	public void execute(){
		Long a = new Date().getTime();
		logger.info("-----------处理获取区县楼盘任务----start----------->>>");
		//获取数据
		countyHousesService.getAndSaveTask();
		//将获取的数据上线,删除原有数据
		countyHousesService.online();
		Long b = new Date().getTime();
		logger.info("-----------处理获取区县楼盘任务-----end---------->>>用时：" + (b - a));
	}

}
