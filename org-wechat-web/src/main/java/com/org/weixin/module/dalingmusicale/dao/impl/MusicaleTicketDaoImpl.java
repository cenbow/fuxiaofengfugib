/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.dao.MusicaleTicketDaoCustom;
import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public class MusicaleTicketDaoImpl implements MusicaleTicketDaoCustom {

	@Resource(name="extendJdbcTemplate")
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.dao.MusicaleTicketDaoCustom#luckDrawTicket(java.lang.Long)
	 */
	@Override
	public List<MusicaleTicket> findAllTicketsById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select mt.* from musicale_ticket mt ");
		sql.append("where mt.id=? or mt.`name` like ?");
		String ticketName = "%"+MusicaleConstant.NOT_TICKET_PREFIX+id+"%";
		return mysqlPagedJdbcTemplate.queryForList(MusicaleTicket.class, sql.toString(),id,ticketName);
	}

}
