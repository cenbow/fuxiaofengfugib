package com.cqliving.log.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.domain.LogPage;
import com.cqliving.log.service.LogOperateService;
import com.cqliving.log.service.LogPageService;

/**
 * com.CQLIVING.edu.log.aop.
 * User: wangyx
 * Date: 14-4-3
 * Time: 下午4:40
 */
public class WorkEduLog implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(WorkEduLog.class);

    private LogPageService logPageService;
    
    private LogOperateService logOperateService;

    private List<LogPage> listPagedLog;
    
    private List<LogOperate> listOperateLog;

    public WorkEduLog(LogPageService logPageService, List<LogPage> list){
        this.logPageService=logPageService;
        this.listPagedLog=list;
    }
    
    public WorkEduLog(LogOperateService logOperateService,List<LogOperate> list){
    	this.logOperateService = logOperateService;
    	this.listOperateLog = list;
    }

    @Override
    public void run() {
    	if(listPagedLog!=null && listPagedLog.size()>0){
    		try {
    			logPageService.saves(listPagedLog);
    		} catch (Exception e) {
    			logger.error("写入页面日志失败,"+listPagedLog.toString(), e);
    		}
    	}
    	if(listOperateLog!=null && listOperateLog.size()>0){
    		try {
				logOperateService.saves(listOperateLog);
			} catch (Exception e) {
				logger.error("写入操作日志失败,"+listOperateLog.toString(), e);
			}
    	}
    }
}
