package com.cqliving.cloud.online.shop.dao;

import java.util.Map;

import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月17日
 */
public interface ShopCategoryDaoCustom {
	
	public PageInfo<ShopCategoryDto> queryDtoForPage(PageInfo<ShopCategoryDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap);
	
}