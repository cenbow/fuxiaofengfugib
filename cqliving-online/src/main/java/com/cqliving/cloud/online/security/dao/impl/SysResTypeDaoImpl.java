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

import com.cqliving.cloud.online.security.dao.SysResTypeDaoCustom;
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.dto.SysResTypeDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月30日
 */
public class SysResTypeDaoImpl implements SysResTypeDaoCustom {

	@Autowired
	MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysResTypeDaoCustom#findExistsRes()
	 */
	@Override
	public List<SysResType> findExistsRes() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select srt.* from sys_res_type srt where EXISTS ");
		sql.append("(select sr.id from sys_resource sr where sr.sys_res_type_id = srt.ID) ");
		sql.append("and srt.`STATUS` = 3 order by srt.sort_num");
		return mysqlPagedJdbcTemplate.queryForList(sql.toString(), SysResType.class);
	}

	@Override
	public List<SysResType> findExistsResByUserId(Long userId){
		
        StringBuilder sql = new StringBuilder();
		sql.append("select srt.* from sys_res_type srt where EXISTS ");
		sql.append("(select sr.id from sys_resource sr,sys_role_resc srr,sys_user_role sur ");
		sql.append("where sr.id = srr.RESC_ID and srr.role_id = sur.role_id and ");
		sql.append("  sr.sys_res_type_id = srt.ID and sur.user_id=? ) ");
		sql.append(" and srt.`STATUS` = 3 order by srt.sort_num ");
		return mysqlPagedJdbcTemplate.queryForList(SysResType.class, sql.toString(), userId);
		
	}

	/**
     * 查询前台资源类型
     * SELECT a.* FROM sys_res_type a,permission b WHERE a.id=b.sys_res_type_id AND a.STATUS=3 GROUP BY a.id 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月20日下午5:14:46
     */
    @Override
    public List<SysResTypeDto> findAppResType() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.* FROM sys_res_type a,permission b WHERE a.id=b.sys_res_type_id AND a.STATUS=").append(SysResType.STATUS3).append(" GROUP BY a.id ");
        return mysqlPagedJdbcTemplate.queryForList(SysResTypeDto.class, sql.toString());
    }
}
