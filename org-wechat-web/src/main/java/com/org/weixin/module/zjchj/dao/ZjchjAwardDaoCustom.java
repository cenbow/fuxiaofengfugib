package com.org.weixin.module.zjchj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月21日
 */
public interface ZjchjAwardDaoCustom {
	
	/**
	 * <p>Description: 中奖统计</p>
	 * @author Tangtao on 2016年10月21日
	 * @param beginTime
	 * @param endTime
	 * @param sortMap
	 * @return
	 */
	List<ZjchjAwardDto> queryData(Date beginTime, Date endTime, Map<String, Boolean> sortMap);
	
}