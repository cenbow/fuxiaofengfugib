/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月4日
 */
public interface InfoTempleteImageConfigDaoCustom {

	public List<InfoTempleteImageConfig> getByAppId(Long appId);
	
	public List<InfoTempleteImageConfig> getByAppColumnsId(Long appColumnsId,Long appId);
	
	public InfoTempleteImageConfigDto getById(Long id);
}
