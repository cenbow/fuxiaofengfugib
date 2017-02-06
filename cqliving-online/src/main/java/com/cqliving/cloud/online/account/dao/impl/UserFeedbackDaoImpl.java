/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.account.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.account.dao.UserFeedbackDaoCustom;
import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月19日
 */
@Repository
public class UserFeedbackDaoImpl implements UserFeedbackDaoCustom {
	
//	@Resource(name = "onlinePagedJdbcTemplate")
//	private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public ScrollPage<UserFeedback> queryForScrollPage(ScrollPage<UserFeedback> page, Long appId, Long userId) {
		//查询数据
//		String sql = "SELECT * FROM user_feedback ORDER BY id DESC";
		String sql = "SELECT uf.* FROM user_feedback uf";
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_userId", userId);
		conditions.put("NOTEQ_status", UserFeedback.STATUS99);
//		mysqlPagedJdbcTemplate.queryPage(UserFeedback.class, page, sql.toString(), conditions);
		mysqlPagedJdbcTemplateV2.queryPage(UserFeedback.class, page, sql.toString(), conditions);
		return page;
	}

	/**
	 * SELECT a.*,b.telephone FROM user_feedback a LEFT JOIN user_account b ON a.user_id = b.id WHERE a.status <> 99
     * <p>Description:分页查询反馈信息</p>
     * @author huxiaoping on 2016年5月28日
     * @param pageInfo
     * @param conditions
     * @param orders
     * @return
     */
    @Override
    public PageInfo<UserFeedbackDto> queryByPage(PageInfo<UserFeedbackDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.telephone FROM user_feedback a LEFT JOIN user_account b ON a.user_id = b.id WHERE a.status <>  ");
        sql.append(UserFeedback.STATUS99);
        mysqlPagedJdbcTemplateV2.queryForPage(UserFeedbackDto.class, sql.toString(), conditions, pageInfo, orders);
        return pageInfo;
    }
    
    /**
     * SELECT a.*,b.telephone,c.name app_name FROM user_feedback a LEFT JOIN user_account b ON a.user_id = b.id LEFT JOIN app_info c ON a.app_id = c.id
     * <p>Description:通过id查询</p>
     * @author huxiaoping on 2016年5月28日
     * @param id
     * @return
     */
    @Override
    public UserFeedbackDto getById(Long id) {
        StringBuffer sql = new StringBuffer("SELECT a.*,b.telephone,c.name app_name FROM user_feedback a LEFT JOIN user_account b ON a.user_id = b.id LEFT JOIN app_info c ON a.app_id = c.id " );
        sql.append(" where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(UserFeedbackDto.class), id);
    }

}