/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao;

import java.util.Map;

import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月28日
 */
public interface InfoSourceDaoCustom {

	public PageInfo<InfoSourceDto> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map);
}
