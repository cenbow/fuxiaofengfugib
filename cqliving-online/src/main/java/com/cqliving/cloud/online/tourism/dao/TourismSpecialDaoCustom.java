/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.tourism.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年8月24日
 */
public interface TourismSpecialDaoCustom {

	public PageInfo<TourismInfoDto> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	public PageInfo<TourismInfoDto> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	/**
	 * <p>Description: 子景点列表</p>
	 * @author Tangtao on 2016年8月25日
	 * @param appId
	 * @param tourismId
	 * @param lat
	 * @param lng
	 * @return
	 */
	List<TourismInfoDto> queryForSubList(Long appId, Long tourismId, double lat, double lng);
	
}
