package com.cqliving.cloud.online.config.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.cloud.online.shop.dto.ShopInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年8月1日
 */
public interface RecommendInfoDaoCustom {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月1日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @param sourceType
	 * @return
	 */
	PageInfo<RecommendInfoDto> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap, Byte sourceType);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月1日
	 * @param id
	 * @param sourceType
	 * @return
	 */
	RecommendInfoDto getDetail(Long id, Byte sourceType);
	
	/**
	 * 获取推荐到首页的商情
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月13日下午3:34:53
	 */
	List<ShopInfoDto> getShopRecommendIndex(Long appId);
}
