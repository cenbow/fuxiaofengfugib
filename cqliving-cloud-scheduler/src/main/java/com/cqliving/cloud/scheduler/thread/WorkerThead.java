package com.cqliving.cloud.scheduler.thread;

import org.apache.log4j.Logger;

import com.cqliving.cloud.scheduler.work.WorkService;
/**
 * 工作线程
 */
public class WorkerThead extends Thread {
    private Logger logger = Logger.getLogger(WorkerThead.class);

    private WorkService work;

    // stopped( 0: 一直执行  1:只执行一次  2:停止)
 	private int stopped = 1;

    public WorkerThead(WorkService work) {
        this.work = work;
    }

    public WorkerThead(WorkService work,int stopped) {
        this.work = work;
        this.stopped = stopped;
    }
    
    public WorkerThead(WorkService work, ThreadGroup group) {
		super(group, work.getWorkName());
		this.work = work;
	}
    
    public void run() {
        try {
            logger.debug(String.format("工作线程:%s 开始执行", work.getWorkName()));
            while (stopped == 0) {
				work.doWork();
			}
            //只执行一次
			if (stopped == 1) {
				work.doWork();
			}
        } catch (Exception ex) {
            logger.error(work.getWorkName(), ex);
        }
    }
    
    public int isStopped() {
		return stopped;
	}

	public void setStopped(int stopped) {
		this.stopped = stopped;
	}
}