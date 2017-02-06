package com.cqliving.cloud.online.shop.dao;

import java.util.Map;

import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年7月18日
 */
public interface ShopTypeDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月18日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	public PageInfo<ShopTypeDto> queryDtoForPage(PageInfo<ShopTypeDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

}