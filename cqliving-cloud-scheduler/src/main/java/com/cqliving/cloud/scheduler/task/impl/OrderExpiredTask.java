package com.cqliving.cloud.scheduler.task.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.order.service.OrderInfoService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月8日
 */
@Component("orderExpiredTask")
public class OrderExpiredTask extends TaskScheduler{
	
	private static final Logger logger = LoggerFactory.getLogger(ManuscriptInfoTask.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	public void execute(){
		Long a = new Date().getTime();
		logger.info("-----------处理订单超时支付任务----start----------->>>");
		orderInfoService.payExpiredTask();
		Long b = new Date().getTime();
		logger.info("-----------处理订单超时支付任务-----end---------->>>用时：" + (b - a));
	}

}
