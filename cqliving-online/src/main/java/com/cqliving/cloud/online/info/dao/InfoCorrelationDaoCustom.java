package com.cqliving.cloud.online.info.dao;

import java.util.List;

import com.cqliving.cloud.online.info.dto.InfoClassifyDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月1日
 */
public interface InfoCorrelationDaoCustom {
	
	/**
	 * <p>Description: 获取相关新闻</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	List<InfoClassifyDto> getCorrelation(Long appId, Long infoClassifyId);

}