/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.security.dto.SysResourceDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月20日
 */
public interface SysResourceDaoCustom {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年4月20日
	 * @return
	 */
	public List<SysResourceDto> findByConditions(Map<String,Object> searchMap,Map<String,Boolean> sortMap);
	//查找一级资源及下面的子资源
	public SysResourceDto findLevelOneResource(Long resId);
}
