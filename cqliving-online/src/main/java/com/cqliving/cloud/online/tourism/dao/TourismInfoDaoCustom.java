package com.cqliving.cloud.online.tourism.dao;

import java.util.Map;

import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * 旅游表 JPA Dao
 * Date: 2016-08-23 13:55:25
 * @author Code Generator
 */
public interface TourismInfoDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年8月25日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<TourismInfoDto> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年8月25日
	 * @param scrollPage
	 * @param map
	 * @param lat
	 * @param lng
	 * @return
	 */
	ScrollPage<TourismInfoDto> queryForScrollPageByDistance(ScrollPage<TourismInfoDto> scrollPage, Map<String, Object> map, double lat, double lng);
	
}