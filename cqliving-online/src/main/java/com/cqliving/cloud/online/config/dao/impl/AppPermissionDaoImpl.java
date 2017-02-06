/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.config.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.config.dao.AppPermissionDaoCustom;
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月15日
 */
@Repository
public class AppPermissionDaoImpl implements AppPermissionDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public List<AppPermissionDto> getDtoOfAll() {
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.url "
				+ "FROM	"
				+ "	app_permission a,"
				+ "	permission b "
				+ "WHERE "
				+ "	a.config_permission_id = b.id";
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_status", AppPermission.STATUS3);
		Map<String, Boolean> orders = Maps.newLinkedHashMap();
		orders.put("appId", true);
		return mysqlPagedJdbcTemplateV2.queryForList(AppPermissionDto.class, sql, conditions, orders);
	}

	/**
     * 查询App的所有资源权限
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月23日上午10:35:37
     */
    @Override
    public List<AppPermissionDto> getByAppId(Long appId) {
        String sql = "SELECT a.*,b.url FROM app_permission a ,permission b WHERE a.config_permission_id=b.id AND app_id = ? and a.status=? ORDER BY a.update_time DESC ,a.id ASC ";
        return mysqlPagedJdbcTemplateV2.queryForList(AppPermissionDto.class, sql.toString(), appId,AppPermission.STATUS3);
    }

}