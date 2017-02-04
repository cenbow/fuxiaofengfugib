/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.cqliving.tool.common.util.StringUtil;
import com.org.weixin.module.jywth.dao.JywthAwardHisDaoCustom;
import com.org.weixin.module.jywth.domain.JywthAwardHis;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月5日
 */
@Repository
public class JywthAwardDaoImpl implements JywthAwardHisDaoCustom{

	@Resource(name="extendJdbcTemplate")
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	/**
	 * @param searchMap
	 * @param sortMap
	 * @return
	 * @see com.org.weixin.module.jywth.dao.JywthAwardHisDaoCustom#queryAwardByConditions(java.util.Map, java.util.Map)
	 * @author fuxiaofeng on 2016年4月5日
	 */
	@Override
	public List<JywthAwardHis> queryAwardByConditions(Map<String, String> searchMap, Map<String, Boolean> sortMap) {
       
		StringBuilder sql = new StringBuilder();
		sql.append("select * from jywth_award_his where  1=1 ");
		
		String code = searchMap.get("EQ_exchangeCode");
		String dateStr = searchMap.get("EQ_dateStr");
		
		if(!StringUtil.isEmpty(code)){
			sql.append("and exchange_code = ? ");
			sql.append(" order by award_time desc,verify_time desc");
			return mysqlPagedJdbcTemplate.queryForList(JywthAwardHis.class, sql.toString(), new Object[]{code});
		}
		
		if(!StringUtil.isEmpty(dateStr)){
			sql.append("and (date(award_time)=date(?) or date(verify_time)=date(?)) ");
			sql.append(" order by award_time desc,verify_time desc");
			return mysqlPagedJdbcTemplate.queryForList(JywthAwardHis.class, sql.toString(), new Object[]{dateStr,dateStr});
		}
		sql.append(" order by award_time desc,verify_time desc");
		return mysqlPagedJdbcTemplate.queryForList(JywthAwardHis.class, sql.toString(), new Object[]{});
	}

}
