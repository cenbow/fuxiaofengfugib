/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.weixin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wechat.framework.utils.Dates;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.module.jywth.domain.JywthAward;
import com.org.weixin.module.jywth.service.JywthAwardService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月3日
 */
@Component
public class JYWTHTask {

	@Autowired
	JywthAwardService jywthAwardService;
	
	private Logger logger = LoggerFactory.getLogger(JYWTHTask.class);
	
	@Scheduled(cron = "0 0 0 3-5,26-30 4,5 ? ")
	public void updateAwardNum(){
		
		int year = DateUtil.getYear(Dates.now());
		
		if(year != 2016)return;
		
		logger.info("开始执行1462红包奖品数量更新>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			jywthAwardService.updateAwardNum(JywthAward.codeNumMap_1462);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("开始执行1462红包奖品数量更新执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Scheduled(cron = "0 0 0 1 5 ? ")
	public void update2462AwardNum(){
		
		int year = DateUtil.getYear(Dates.now());
		
		if(year != 2016)return;
		
		logger.info("开始执行4462红包奖品数量更新>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			jywthAwardService.updateAwardNum(JywthAward.codeNumMap_4462);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("开始执行4462红包奖品数量更新执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Scheduled(cron = "0 0 0 2 5 ? ")
	public void update4462AwardNum(){
		
		int year = DateUtil.getYear(Dates.now());
		
		if(year != 2016)return;
		
		logger.info("开始执行2462红包奖品数量更新>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			jywthAwardService.updateAwardNum(JywthAward.codeNumMap_2462);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		logger.info("开始执行2462红包奖品数量更新执行结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
