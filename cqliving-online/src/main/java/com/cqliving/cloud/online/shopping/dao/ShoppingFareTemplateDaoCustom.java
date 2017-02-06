/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.shopping.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年12月2日
 */
public interface ShoppingFareTemplateDaoCustom {

	public List<ShoppingFareTemplateDto> findConditions(Map<String, Object> conditions);
}
