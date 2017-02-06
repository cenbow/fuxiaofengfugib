package com.cqliving.cloud.scheduler.task;

public abstract class TaskScheduler {
	// 默认批量扫描数据列表大小
	protected static final int DEFAULT_BATCH_SIZE = 100;

	/**
	 * 定时执行方法
	 */
	public abstract void execute();

}
