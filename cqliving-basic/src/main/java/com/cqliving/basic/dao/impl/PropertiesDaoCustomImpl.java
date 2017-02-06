/**
	 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.basic.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cqliving.basic.dao.PropertiesDaoCustom;
import com.cqliving.framework.common.dao.jdbc.PagedJdbcTemplate;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author tangqiang on 2015年5月28日
 */
@Repository
public class PropertiesDaoCustomImpl implements PropertiesDaoCustom{

	@Autowired
	@Qualifier(value="basicPagedJdbcTemplate")
	PagedJdbcTemplate basicPagedJdbcTemplate;
	
	/**
	 * <p>Description:</p>
	 * @param config
	 * @return
	 * @see com.CQLIVING.edu.basic.dao.PropertiesDaoCustom#loadPropertiesWith(java.lang.String)
	 * @author tangqiang on 2015年5月28日
	 */
	@Override
	public List<Map<String,Object>> loadPropertiesWith(String config) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select kkey k,").append(config).append(" ").append(" v");
		sql.append(" from ol_properties");
		return basicPagedJdbcTemplate.queryForList(sql.toString());
	}

}
