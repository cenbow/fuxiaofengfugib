/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
/**
 * 
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.account.dao.UserInfoReplyDaoCustom;
import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.cloud.online.account.dto.UserInfoReplyDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */

public class UserInfoReplyDaoImpl implements UserInfoReplyDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<UserInfoReplyDto> queryDtoForScrollPage(ScrollPage<UserInfoReplyDto> page, Map<String, Object> conditions, Long userId, Byte sourceType) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name nickname, "
				+ "	b.anonymous_name, "
				+ "	b.img_url "
				+ "FROM	"
				+ "	user_info_reply a "
				+ "LEFT JOIN user_info b ON a.reply_user_id = b.id");
		mysqlPagedJdbcTemplateV2.queryPage(UserInfoReplyDto.class, page, sql.toString(), conditions);
		return page;
	}
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月12日
     */
    @Override
    public PageInfo<UserInfoReplyDto> queryByPage(PageInfo<UserInfoReplyDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders,Byte sourceType) {
        StringBuffer sql = new StringBuffer();
        if(UserInfoReply.SOURCETYPE1.equals(sourceType)){//新闻
            sql.append(" SELECT a.* ,b.title ,d.type news_type ,c.name reply_user ,e.telephone ,CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN info_classify b ON a.info_classify_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id ");
            sql.append(" LEFT JOIN user_account e ON a.reply_user_id = e.id LEFT JOIN information d ON a.source_id = d.id ");
            sql.append(" WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE1);
        }else if(UserInfoReply.SOURCETYPE2.equals(sourceType)){//问政
            sql.append(" SELECT a.*,b.title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN wz_question b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id ");
            sql.append(" WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE2);
        }else if(UserInfoReply.SOURCETYPE3.equals(sourceType)){//商情
            sql.append(" SELECT a.*,b.name title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN shop_info b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE3);
        }else if(UserInfoReply.SOURCETYPE4.equals(sourceType)){//随手拍
            sql.append(" SELECT a.*,b.content title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN shoot_info b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE4);
        }else if(UserInfoReply.SOURCETYPE5.equals(sourceType)){//段子
            sql.append(" SELECT a.*,b.content title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN joke_info b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE5);
        }else if(UserInfoReply.SOURCETYPE6.equals(sourceType)){//活动
            sql.append(" SELECT a.*,b.title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN act_info_list e ON a.source_id = e.id INNER JOIN act_info b ON e.act_info_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE6);
        }else if(UserInfoReply.SOURCETYPE7.equals(sourceType)){//话题
            sql.append(" SELECT a.*,b.name title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN topic_info b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE7);
        }else if(UserInfoReply.SOURCETYPE10.equals(sourceType)){//旅游
            sql.append(" SELECT a.*,b.name title, c.name reply_user, CASE a.status WHEN -1 THEN 1 WHEN 2 THEN 4 WHEN 3 THEN 3 ELSE -1 END status_new ");
            sql.append(" FROM user_info_reply a INNER JOIN tourism_info b ON a.source_id = b.id LEFT JOIN user_info c ON a.reply_user_id = c.id WHERE a.source_type = ").append(UserInfoReply.SOURCETYPE10);
        }
        mysqlPagedJdbcTemplateV2.queryForPage(UserInfoReplyDto.class, sql.toString(), conditions, pageInfo, orders);
        return pageInfo;
    }
    
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.dao.UserInfoReplyDaoCustom#queryForTopicCommentsPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	public ScrollPage<UserInfoReplyDto> queryForTopicCommentsPage(ScrollPage<UserInfoReplyDto> scrollPage,
			Map<String, Object> conditions) {
		//查询数据
		StringBuilder sql = new StringBuilder();
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name nickname, "
				+ "	b.anonymous_name, "
				+ "	b.img_url, "
				+ "	c.name title "
				+ "FROM	"
				+ "	user_info_reply a "
				+ "LEFT JOIN user_info b ON a.reply_user_id = b.id "
				+ "LEFT JOIN topic_info c ON a.source_id = c.id ");
		mysqlPagedJdbcTemplateV2.queryPage(UserInfoReplyDto.class,scrollPage, sql.toString(), conditions);
		return scrollPage;
	}
}