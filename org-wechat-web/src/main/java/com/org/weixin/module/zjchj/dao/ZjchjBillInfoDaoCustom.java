package com.org.weixin.module.zjchj.dao;


import java.util.Date;
import java.util.List;

import com.org.weixin.module.zjchj.dto.ZjchjBillInfoDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年9月26日
 */
public interface ZjchjBillInfoDaoCustom {

	/**
	 * <p>Description: 获取菜品排行统计数据</p>
	 * @author Tangtao on 2016年9月26日
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ZjchjBillInfoDto> getStatistics(Date beginTime, Date endTime);
	
}