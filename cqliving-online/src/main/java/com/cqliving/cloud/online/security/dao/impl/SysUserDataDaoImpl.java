/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.dao.SysUserDataDaoCustom;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年10月11日
 */
public class SysUserDataDaoImpl implements SysUserDataDaoCustom {

	@Autowired
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysUserDataDaoCustom#findPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<SysUserDataDto> findPage(PageInfo<SysUserDataDto> pageInfo, Map<String, Object> map,
			Map<String, Boolean> orderMap) {
		StringBuilder sql = new StringBuilder();
		
		Byte type = (Byte) map.get("EQ_type");
		sql.append("select sd.*,su.NICKNAME user_name,GROUP_CONCAT(vt.name) value_name,su.app_id  ");
		sql.append("from sys_user_data sd INNER JOIN sys_user su on sd.USER_ID = su.ID  ");
		if(SysUserData.TYPE2 == type.byteValue()){
			sql.append(" INNER JOIN app_columns vt on sd.`VALUE` = vt.ID ");
		}else if(SysUserData.TYPE3 == type.byteValue()){
			sql.append("INNER JOIN shop_type vt on sd.`VALUE` = vt.ID ");
		}else{
			sql.append("INNER JOIN sys_role vt on sd.`VALUE` = vt.ID ");
		}
		sql.append("group by sd.user_id,sd.type ");
		mysqlPagedJdbcTemplate.queryForPage(SysUserDataDto.class, sql.toString(), map, pageInfo, orderMap);
		return pageInfo;
	}

}
