package com.cqliving.log.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolFactory {
	
	private static Logger logger = LoggerFactory.getLogger(ThreadPoolFactory.class);
	
	public static final int threadSize = 1;

	private static ExecutorService fixedThreadPool;
	 
	public static ExecutorService get(){
		if(fixedThreadPool == null){
			logger.info("初始化线程池,数量为"+threadSize);
			fixedThreadPool = Executors.newFixedThreadPool(threadSize);
		}
		return fixedThreadPool;
	}
}
