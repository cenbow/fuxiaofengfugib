/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.message.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cqliving.cloud.online.message.dao.MessageInfoDaoCustom;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年5月9日
 */
@Repository
public class MessageInfoDaoImpl implements MessageInfoDaoCustom {
	
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public PageInfo<MessageInfoDto> queryDtoForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap) {
		//查询数据
		StringBuilder sql = new StringBuilder();
//		sql.append(""
//				+ "SELECT "
//				+ "	a.*, "
//				+ "	b.name app_name "
//				+ "FROM	"
//				+ "	message_info a "
//				+ "LEFT JOIN app_info b ON a.app_id = b.id "
//				+ "ORDER BY "
//				+ "	a.send_time DESC");
		sql.append(""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.name app_name "
				+ "FROM	"
				+ "	message_info a "
				+ "LEFT JOIN app_info b ON a.app_id = b.id");
		mysqlPagedJdbcTemplateV2.queryForPage(MessageInfoDto.class, sql.toString().toUpperCase().replaceAll("	", " "), searchMap, pageInfo, sortMap);
		return pageInfo;
	}
	
	/**
     * <p>Description: 分页查询站内信</p>
     * @author huxiaoping on 2016年5月9日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
	@Override
	public PageInfo<MessageInfoDto> queryLetterForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
	        Map<String, Boolean> sortMap) {
	    StringBuilder sql = new StringBuilder(" SELECT a.*,b.status read_status,c.name app_name ,b.receiver_id,b.id r_id FROM message_info a ");
	    sql.append(" INNER JOIN message_receive b ON a.id=b.message_id LEFT JOIN app_info c ON a.app_id = c.id ");
	    // 站内信
	    sql.append(" where b.send_type = ").append(MessageReceive.SENDTYPE0);
	    sql.append(" AND a.send_time <=NOW() ") ;
	    mysqlPagedJdbcTemplateV2.queryForPage(MessageInfoDto.class, sql.toString(), searchMap, pageInfo, sortMap);
	    return pageInfo;
	}
	
	/**
	 * <p>Description: 查询接收表站内信通过id</p>
	 * @author huxiaoping on 2016年5月14日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	@Override
	public MessageInfoDto getById(Long id) {
	    StringBuilder sql = new StringBuilder(" SELECT a.*,b.status read_status,c.name app_name ,b.receiver_id,b.id r_id FROM message_info a ");
	    sql.append(" INNER JOIN message_receive b ON a.id=b.message_id LEFT JOIN app_info c ON a.app_id = c.id ");
	    // 站内信
	    sql.append(" where b.send_type = ").append(MessageReceive.SENDTYPE0);
	    sql.append(" and b.id = ? ");
	    return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(MessageInfoDto.class), id);
	}

	/**
     * <p>Description: 分页查询通知</p>
     * SELECT a.*,b.status read_status,b.receiver_id,b.id r_id FROM message_info a INNER JOIN message_receive b ON a.id=b.message_id
     * @author huxiaoping on 2016年5月13日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    public PageInfo<MessageInfoDto> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap) {
        StringBuilder sql = new StringBuilder(" SELECT a.*,b.receiver_id,b.id r_id ");
        sql.append(" FROM message_info a INNER JOIN message_receive b ON a.id=b.message_id ");
        // 查询未读的站内信
        sql.append(" where b.status = ").append(MessageReceive.STATUS0).append(" and a.send_type = ").append(MessageReceive.SENDTYPE0);
        sql.append(" AND a.send_time <=NOW() ") ;
        mysqlPagedJdbcTemplateV2.queryForPage(MessageInfoDto.class, sql.toString(), searchMap, pageInfo, sortMap);
        return pageInfo;
    }

}
