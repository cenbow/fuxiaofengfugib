package com.cqliving.cloud.scheduler.work;
/**
 * 工作接口
 */
public interface WorkService  {
	/**
	 * Title:工作名称
	 * @author yuwu on 2016年5月26日
	 * @return
	 */
	public String getWorkName();
			
	/**
	 * Title:工作方法
	 * @author yuwu on 2016年5月26日
	 * @return
	 */
	public boolean doWork();
}