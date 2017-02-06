/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.building.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.building.dao.BuildingInfoDaoCustom;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年10月12日
 */
public class BuildingInfoDaoImpl implements BuildingInfoDaoCustom {

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.building.dao.BuildingInfoDaoCustom#queryScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	public ScrollPage<BuildingInfo> queryScrollPage(ScrollPage<BuildingInfo> scrollPage, Map<String, Object> conditions) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from building_info ");
        String keyword  = (String) conditions.get("LIKE_keyword");
        if(!StringUtil.isEmpty(keyword)){
        	sql.append(" where (name like  '%").append(keyword).append("%'");
        	sql.append(" or address like '%").append(keyword).append("%' ");
        	sql.append(" or developer like '%").append(keyword).append("%' ");
        	sql.append(" or landmark like '%").append(keyword).append("%') ");
        	conditions.remove("LIKE_keyword");
        }
		return mysqlPagedJdbcTemplateV2.queryPage(BuildingInfo.class, scrollPage, sql.toString(), conditions);
	}
}
