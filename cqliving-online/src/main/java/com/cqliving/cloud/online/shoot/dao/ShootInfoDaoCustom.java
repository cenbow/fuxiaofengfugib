package com.cqliving.cloud.online.shoot.dao;

import java.util.Map;

import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月8日
 */
public interface ShootInfoDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月13日
	 * @param scrollPage
	 * @param conditions
	 * @param praiseUserId 
	 * @return
	 */
	public ScrollPage<ShootInfoDto> queryForScrollPage(ScrollPage<ShootInfoDto> scrollPage, Map<String, Object> conditions, Long praiseUserId);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月13日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	public PageInfo<ShootInfoDto> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
}
