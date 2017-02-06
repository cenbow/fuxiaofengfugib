/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.app.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.app.dao.AllMediaDaoCustom;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月2日
 */
public class AllMediaDaoImpl implements AllMediaDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.dao.AppMediaDaoCustom#queryPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<AllMediaDto> queryPage(PageInfo<AllMediaDto> pageInfo, Map<String, Object> map,
			Map<String, Boolean> orderMap) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT am.*,ai.`name` as app_name,ac.`name` as columns_name ");
		sql.append("FROM `all_media` am INNER JOIN app_info ai on am.app_id=ai.id ");
		sql.append(" LEFT JOIN app_columns ac on am.columns_id = ac.ID");
		mysqlPagedJdbcTemplateV2.queryForPage(AllMediaDto.class, sql.toString(), map, pageInfo, orderMap);
		return pageInfo;
	}

}
