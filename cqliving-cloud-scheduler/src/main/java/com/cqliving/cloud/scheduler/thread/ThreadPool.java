package com.cqliving.cloud.scheduler.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 线程池，池和队列满的策略：阻塞等有空闲线程后继续添加
 *
 */
public class ThreadPool {
	
	//private Logger logger = Logger.getLogger(ThreadPool.class);
	
	private ThreadPoolExecutor threadPool;
	 	
	public ThreadPool () {
		
	}

	/**
	 * <p>Title: </p>
	 * <p>Description: 构造函数</p>
	 * @param minPoolSize 运行线程大小
	 * @param maxPoolSize 线程池的最大运行线程数
	 * @param idleSeconds 空闲线程清楚时间
	 * @param queueBlockSize 运行线程满时，等待队列的大小
	 */
	public ThreadPool(int minPoolSize, int maxPoolSize, int idleSeconds, 
		int queueBlockSize){
		threadPool =  new ThreadPoolExecutor(
					minPoolSize, //运行线程大小
					maxPoolSize, //线程池的最大运行线程数
					idleSeconds, //空闲线程清楚时间
					TimeUnit.SECONDS, //空闲线程清楚时间的单位
					new ArrayBlockingQueue<Runnable>(queueBlockSize), //运行线程满时，等待队列的大小
					new ThreadPoolExecutor.CallerRunsPolicy());//池和队列满的策略
	}
	
	/**
	 * <p>Title: execute</p>
	 * <p>Description: 加入线程池</p>
	 * @param workerThead
	 */
	public void execute(WorkerThead workerThead) {
		//logger.debug("任务加入线程池");
		threadPool.execute(workerThead);
		//logger.info("活动线程数："+threadPool.getActiveCount());
		//logger.info("等待队列："+threadPool.getQueue().size());
	}
	
	public void shutdown() {		
		threadPool.shutdownNow();		
	}
}