/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.info.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.info.dao.InfoFileDaoCustom;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月1日
 */
public class InfoFileDaoImpl implements InfoFileDaoCustom{

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.dao.InfoFileDaoCustom#updateInfoAndAppResourse(java.lang.Long)
	 */
	@Override
	public void updateInfoAndAppResourse(Long infoFileId,String qiniuUrl,String fileName) {
		StringBuilder sql = new StringBuilder();
		
		//修改新闻资源表
		sql.append("update app_resouse set file_url=?,name=? where info_file_id=?");
		
		mysqlPagedJdbcTemplate.update(sql.toString(), qiniuUrl,fileName,infoFileId);
		
		//修改新闻内容为已转码状态
		sql = new StringBuilder();
		/*sql.append("update information set information.video_status=3,information.content_url=? ");
		sql.append("where information.id in (select ar.information_id from app_resouse ar where ar.info_file_id=? )");*/
		sql.append("update information info,app_resouse ar set info.video_status = 3,info.content_url = ? ");
		sql.append("where info.ID = ar.information_id and ar.info_file_id = ? ");
		mysqlPagedJdbcTemplate.update(sql.toString(), qiniuUrl,infoFileId);
		
		//修改活动投票项的资源为已转码状态
		sql = new StringBuilder();
		sql.append("update act_vote_item set status=3,video_url=? where info_file_id=? ");
		
		mysqlPagedJdbcTemplate.update(sql.toString(), qiniuUrl,infoFileId);
	}

}
