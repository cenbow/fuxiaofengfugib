/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;
import org.wechat.framework.utils.Dates;

import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.dao.UserTicketDaoCustom;
import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.dto.UserTicketDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
public class UserTicketDaoImpl implements UserTicketDaoCustom {

	@Resource(name="extendJdbcTemplate")
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.dao.UserTicketDaoCustom#findByUserIdDaily(java.util.Date, java.util.Date, java.lang.Long)
	 */
	@Override
	public List<UserTicket> findByUserIdDaily(Long userId,Long musicaleId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ut.* from user_ticket ut,musicale_ticket mt ");
		sql.append("where ut.musicale_ticket_id=mt.id and (mt.id=? or ");
		sql.append("mt.`name` like ?) and ut.grab_time>=? and ut.grab_time<=? ");
		sql.append("and ut.user_id=? ");
		String ticketName = "%"+MusicaleConstant.NOT_TICKET_PREFIX+musicaleId+"%";
		return mysqlPagedJdbcTemplate.queryForList(UserTicket.class, sql.toString(), musicaleId,ticketName,Dates.todayStart(),Dates.todayEnd(),userId);
	}
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.dao.UserTicketDaoCustom#findByVerifyCode(java.lang.String)
	 */
	@Override
	public UserTicketDto findByVerifyCode(String verifyCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ut.phone,ut.grab_time,ut.take_status,ut.verify_code,mt.`name` ticket_name,");
		sql.append("mt.duration,mt.image_url from user_ticket ut,musicale_ticket mt ");
		sql.append("where ut.musicale_ticket_id=mt.id and ut.verify_code=?");
		
		List<UserTicketDto> data = mysqlPagedJdbcTemplate.queryForList(UserTicketDto.class, sql.toString(), verifyCode);
		if(CollectionUtils.isEmpty(data))return null;
		return data.get(0);
	}
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.dao.UserTicketDaoCustom#findByUserId(java.lang.Long)
	 */
	@Override
	public List<UserTicketDto> findByUserId(Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ut.phone,ut.grab_time,ut.take_status,ut.verify_code,mt.`name` ticket_name,");
		sql.append("mt.id musicale_id,mt.duration,mt.image_url from user_ticket ut,musicale_ticket mt ");
		sql.append("where ut.musicale_ticket_id=mt.id and ut.take_status<>0 and ut.user_id=?");
		List<UserTicketDto> data = mysqlPagedJdbcTemplate.queryForList(UserTicketDto.class, sql.toString(),userId);
		if(CollectionUtils.isEmpty(data))return null;
		return data;
	}
	
	
	
	/*select mt.`name`,mt.id,t1.award_num,t2.not_award_num,t3.verify_num,
	t4.award_user_num,t5.not_award_user_num,t6.verify_user_num 
	from musicale_ticket mt  LEFT JOIN 
	-- 中奖人次
	(select ut.musicale_ticket_id,COUNT(ut.id) award_num from user_ticket ut 
	where ut.take_status <>0 group by ut.musicale_ticket_id) t1 
	on t1.musicale_ticket_id=mt.id LEFT JOIN
	-- 未中奖人次
	(select ut.musicale_ticket_id,COUNT(ut.id) not_award_num from user_ticket ut 
	where ut.take_status =0 group by ut.musicale_ticket_id) t2 
	on t2.musicale_ticket_id = mt.id LEFT JOIN
	-- 核销人次
	(select ut.musicale_ticket_id,COUNT(ut.id) verify_num from user_ticket ut 
	where ut.take_status =2 group by ut.musicale_ticket_id) t3 
	on t3.musicale_ticket_id = mt.id LEFT JOIN
	-- 中奖人数
	(select t7.musicale_ticket_id,COUNT(t7.musicale_ticket_id) award_user_num from  
	(select ut.musicale_ticket_id,ut.user_id from user_ticket ut 
	where ut.take_status <>0 group by ut.musicale_ticket_id,ut.user_id) t7 
	GROUP BY t7.musicale_ticket_id) t4
	on t4.musicale_ticket_id = mt.id LEFT JOIN
	-- 未中奖人数
	(select t8.musicale_ticket_id,COUNT(t8.musicale_ticket_id) not_award_user_num from 
	(select ut.musicale_ticket_id,ut.user_id from user_ticket ut 
	where ut.take_status =0 group by ut.musicale_ticket_id,ut.user_id) t8
	GROUP BY t8.musicale_ticket_id )t5 
	on t5.musicale_ticket_id = mt.id LEFT JOIN
	-- 核销人数
	(select t9.musicale_ticket_id,COUNT(t9.user_id) verify_user_num from
	(select ut.musicale_ticket_id,ut.user_id from user_ticket ut 
	where ut.take_status =2 group by ut.musicale_ticket_id,ut.user_id) t9
	GROUP BY t9.musicale_ticket_id)t6 
	on t6.musicale_ticket_id = mt.id where name not like '%NOT_TICKET%';*/
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.dao.UserTicketDaoCustom#statistics()
	 */
	@Override
	public List<UserTicketDto> statistics(Map<String,Object> conditions) {
		StringBuilder sql = new StringBuilder();
		sql.append("select mt.`name` ticket_name,mt.id musicale_id,t1.award_num,t2.not_award_num,t3.verify_num,");
		sql.append("t4.award_user_num,t5.not_award_user_num,t6.verify_user_num  ");
		sql.append("from musicale_ticket mt  LEFT JOIN ");
		sql.append("(select ut.musicale_ticket_id,COUNT(ut.id) award_num from user_ticket ut ");
		sql.append("where ut.take_status <>0 group by ut.musicale_ticket_id) t1 ");
		sql.append("on t1.musicale_ticket_id=mt.id LEFT JOIN ");
		sql.append("(select ut.musicale_ticket_id,COUNT(ut.id) not_award_num from user_ticket ut ");
		sql.append("where ut.take_status =0 group by ut.musicale_ticket_id) t2 ");
		sql.append("on t2.musicale_ticket_id = mt.id LEFT JOIN ");
		sql.append("(select ut.musicale_ticket_id,COUNT(ut.id) verify_num from user_ticket ut  ");
		sql.append("where ut.take_status =2 group by ut.musicale_ticket_id) t3 ");
		sql.append("on t3.musicale_ticket_id = mt.id LEFT JOIN ");
		sql.append("(select t7.musicale_ticket_id,COUNT(t7.musicale_ticket_id) award_user_num from  ");
		sql.append("(select ut.musicale_ticket_id,ut.user_id from user_ticket ut ");
		sql.append("where ut.take_status <>0 group by ut.musicale_ticket_id,ut.user_id) t7 ");
		sql.append("GROUP BY t7.musicale_ticket_id) t4 ");
		sql.append("on t4.musicale_ticket_id = mt.id LEFT JOIN ");
		sql.append("(select t8.musicale_ticket_id,COUNT(t8.musicale_ticket_id) not_award_user_num from ");
		sql.append("(select ut.musicale_ticket_id,ut.user_id from user_ticket ut ");
		sql.append("where ut.take_status =0 group by ut.musicale_ticket_id,ut.user_id) t8 ");
		sql.append("GROUP BY t8.musicale_ticket_id )t5 ");
		sql.append("on t5.musicale_ticket_id = mt.id LEFT JOIN ");
		sql.append("(select t9.musicale_ticket_id,COUNT(t9.user_id) verify_user_num from ");
		sql.append("(select ut.musicale_ticket_id,ut.user_id from user_ticket ut ");
		sql.append("where ut.take_status =2 group by ut.musicale_ticket_id,ut.user_id) t9 ");
		sql.append("GROUP BY t9.musicale_ticket_id)t6 ");
		sql.append("on t6.musicale_ticket_id = mt.id where name not like '%NOT_TICKET%' ");
		
		return mysqlPagedJdbcTemplate.queryForList(UserTicketDto.class, sql.toString(), conditions);
	}

}
