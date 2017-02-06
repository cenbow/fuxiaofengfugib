package com.cqliving.cloud.online.topic.dao;

import java.util.Map;

import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月26日
 */
public interface TopicInfoDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月18日
	 * @param pageInfo
	 * @param searchMap
	 * @param name 
	 * @return
	 */
	ScrollPage<TopicInfo> queryForScrollPage(ScrollPage<TopicInfo> scrollPage, Map<String, Object> searchMap, String name);

}