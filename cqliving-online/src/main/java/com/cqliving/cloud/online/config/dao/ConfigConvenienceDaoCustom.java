package com.cqliving.cloud.online.config.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月13日
 */
public interface ConfigConvenienceDaoCustom {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @return
	 */
	public PageInfo<ConfigConvenienceDto> queryPage(PageInfo<ConfigConvenienceDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月15日
	 * @param appId
	 * @return
	 */
	List<ConfigConvenienceDto> getByApp(Long appId);

}
