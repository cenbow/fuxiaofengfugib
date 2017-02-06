package com.cqliving.cloud.online.config.dao;

import java.util.List;

import com.cqliving.cloud.online.config.dto.AppPermissionDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月15日
 */
public interface AppPermissionDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月15日
	 * @return
	 */
	List<AppPermissionDto> getDtoOfAll();
	
	/**
	 * 查询App的所有资源权限
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月23日上午10:35:37
	 */
	List<AppPermissionDto> getByAppId(Long appId);
	
}