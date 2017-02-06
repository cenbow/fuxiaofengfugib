/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.app.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月29日
 */
public interface AppColumnsDaoCustom {

	public List<AppColumnsDto> getByConditions(Map<String, Object> conditions);
	
	public List<AppColumnsDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	// Long appId , Byte status
	public List<GetColumnsData> getByAppId(Map<String, Object> conditions, Map<String, Boolean> orderMap);
}
