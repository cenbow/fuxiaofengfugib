/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.info.dao.InfoSourceDaoCustom;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月28日
 */
public class InfoSourceDaoImpl implements InfoSourceDaoCustom {

	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoSourceDaoCustom#queryForPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	public PageInfo<InfoSourceDto> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		sql.append("select source.app_id,source.ID,source.`name`,source.sort_no ");
		sql.append(",ai.`name` app_name,source.update_time from info_source source ,app_info ai ");
		sql.append("where source.app_id = ai.id and source.`STATUS` = 3 ${WHERE} ");
		sql.append("ORDER BY source.sort_no,source.update_time desc,source.id desc");
		map.put("LIKE_source.name", map.get("LIKE_name"));
		map.remove("LIKE_name");
		mysqlPagedJdbcTemplateV2.queryForPage(InfoSourceDto.class, sql.toString(), map, pageInfo);
		return pageInfo;
	}

}
