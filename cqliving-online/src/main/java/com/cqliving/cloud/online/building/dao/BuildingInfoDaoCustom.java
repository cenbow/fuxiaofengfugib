/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.building.dao;

import java.util.Map;

import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年10月12日
 */
public interface BuildingInfoDaoCustom {

	public ScrollPage<BuildingInfo> queryScrollPage(ScrollPage<BuildingInfo> scrollPage, Map<String, Object> conditions);
	
}
