package com.cqliving.cloud.online.recruitinfo.dao;

import java.util.Map;

import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
public interface RecruitInfoDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月12日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	ScrollPage<RecruitInfo> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions);
	
}