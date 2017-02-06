package com.cqliving.cloud.scheduler.task.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.manuscript.service.ManuscriptInfoClassifyService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;

/**
 * Title:秀山抓稿
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月4日
 */
@Component("xiuShanManuscriptTask")
public class XiuShanManuscriptTask extends TaskScheduler{
    private static final Logger logger = LoggerFactory.getLogger(XiuShanManuscriptTask.class);
    
    @Autowired
    private ManuscriptInfoClassifyService manuscriptInfoClassifyService;
    
    @Override
    public void execute() {
        Long a = new Date().getTime();
        logger.info("秀山抓稿开始---start---->");
        manuscriptInfoClassifyService.impotXiuShan();
        Long b = new Date().getTime();
        logger.info(String.format("秀山抓稿结束---end---->共耗时=%d", b - a));
        
    }
}
