/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import javax.annotation.Resource;

import com.cqliving.cloud.online.info.dao.InformationContentDaoCustom;
import com.cqliving.framework.common.dao.jdbc.MysqlPagedJdbcTemplate;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年5月9日
 */
public class InformationContentDaoImpl implements InformationContentDaoCustom {

	@Resource(name = "onlinePagedJdbcTemplate")
	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InformationContentDaoCustom#deleteByInformationId(java.lang.Long)
	 */
	@Override
	public int deleteById(Long infoContentId) {
		StringBuilder sql = new StringBuilder();
		int i = 0;
		//删除app_resource
		sql.append("update app_resouse set status=99 where information_content_id=?");
		i = mysqlPagedJdbcTemplate.update(sql.toString(), infoContentId);
		//删除info_content
		sql = new StringBuilder();
		sql.append("update information_content set status=99 where id = ? ");
		i = mysqlPagedJdbcTemplate.update(sql.toString(), infoContentId);
		
		//删除调研
		sql = new StringBuilder();
		sql.append("update survey_info set status=99 where information_content_id = ? ");
		i = mysqlPagedJdbcTemplate.update(sql.toString(), infoContentId);
		
		//删除问题
		sql = new StringBuilder();
		sql.append("update survey_question a,survey_info b set a.status=99 where a.survey_id = b.id and b.information_content_id = ? ");
		i = mysqlPagedJdbcTemplate.update(sql.toString(), infoContentId);
		
		//删除答案
		sql = new StringBuilder();
		sql.append("update survey_question_answer sqa,survey_question sq,survey_info si ");
		sql.append(" set sqa.status = 99 ");
        sql.append("where sqa.question_id=sq.ID and sq.survey_id = si.ID and si.information_content_id = ?");
		i = mysqlPagedJdbcTemplate.update(sql.toString(), infoContentId);
		
		return i;
	}
}
