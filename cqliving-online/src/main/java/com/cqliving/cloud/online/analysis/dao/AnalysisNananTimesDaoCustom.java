package com.cqliving.cloud.online.analysis.dao;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年11月10日
 */
public interface AnalysisNananTimesDaoCustom {
	
	/**
	 * <p>Description: 调用存储过程，生成期数数据</p>
	 * @author Tangtao on 2016年11月10日
	 * @param beginDate
	 * @param endDate
	 * @param columnsId
	 */
	void createTimes(String beginDate, String endDate, Long columnsId);
	
}