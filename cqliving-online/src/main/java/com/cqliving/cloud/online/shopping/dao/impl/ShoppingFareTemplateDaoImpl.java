/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.shopping.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDaoCustom;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年12月2日
 */
public class ShoppingFareTemplateDaoImpl implements ShoppingFareTemplateDaoCustom {
	
	 @Autowired
	 private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDaoCustom#findConditions(java.util.Map)
	 */
	@Override
	public List<ShoppingFareTemplateDto> findConditions(Map<String, Object> conditions) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from shopping_fare_template ");
		return mysqlPagedJdbcTemplateV2.queryForList(ShoppingFareTemplateDto.class, sql.toString(), conditions);
	}

}
