/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.dao.SysRoleDaoCustom;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月21日
 */
public class SysRoleDaoImpl implements SysRoleDaoCustom {

	@Autowired
	MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysRoleDaoCustom#findSysUserRoleByRoleId()
	 */
	@Override
	public List<SysRole> findSysUserRoleByRoleId(Long roleId) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select sr.* from sys_user_role sur,sys_role sr ");
        sql.append("where sur.ROLE_ID = sr.ID and sr.id=? ");
		
		return mysqlPagedJdbcTemplate.queryForList(SysRole.class, sql.toString(), roleId);
	}

}
