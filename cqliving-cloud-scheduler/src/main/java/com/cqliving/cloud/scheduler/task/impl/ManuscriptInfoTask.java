package com.cqliving.cloud.scheduler.task.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.manuscript.service.ManuscriptInfoClassifyService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:重庆APP新闻栏目内容自动抓稿
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月4日
 */
@Component("manuscriptInfoTask")
public class ManuscriptInfoTask extends TaskScheduler{
	private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoTask.class);
	
	@Autowired
	private ManuscriptInfoClassifyService manuscriptInfoClassifyService;
	
	@Override
	public void execute() {
		Long a = new Date().getTime();
		logger.info("抓稿开始---start---->");
		manuscriptInfoClassifyService.importData();
		Long b = new Date().getTime();
		logger.info(String.format("抓稿结束---end---->共耗时=%d", b - a));
		
	}
}
