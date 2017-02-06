package com.cqliving.cloud.online.account.dao;

import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月9日
 */
public interface UserSmsLogDaoCustom {
	
	/**
	 * <p>Description: 获取短信统计</p>
	 * @author Tangtao on 2016年10月9日
	 * @param pageInfo 
	 * @return
	 */
	PageInfo<SmsStatisticsDto> getStatistic(PageInfo<SmsStatisticsDto> pageInfo);

}