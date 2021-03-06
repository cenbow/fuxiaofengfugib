package com.cqliving.cloud.online.config.dao;

import java.util.Map;

import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月26日
 */
public interface RecommendAppDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<RecommendAppDto> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	ScrollPage<RecommendAppDto> queryDtoForPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions);
	
}