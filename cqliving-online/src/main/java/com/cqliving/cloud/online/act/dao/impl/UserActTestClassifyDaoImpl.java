package com.cqliving.cloud.online.act.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.act.dao.UserActTestClassifyDaoCustom;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.framework.common.dao.jdbc.MysqlExtendJdbcTemplate;

public class UserActTestClassifyDaoImpl implements UserActTestClassifyDaoCustom{

	@Autowired
    private MysqlExtendJdbcTemplate mysqlExtendJdbcTemplate;
	
	public UserActTestClassify getLast(Long userId, Long actInfoListId){
		StringBuilder sql = new StringBuilder();
		sql.append("select c.* from user_act_list a"); 
		sql.append(" left join act_test b on a.act_info_id=b.act_info_id and a.act_info_list_id=b.act_info_list_id");
		sql.append(" left join user_act_test_classify c on b.ID=c.act_test_id");
		sql.append(" where a.act_info_list_id=? and c.user_id=?");
		sql.append(" order by c.create_time desc limit 1");
		UserActTestClassify res = null;
		try {
			res = mysqlExtendJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(UserActTestClassify.class), actInfoListId, userId);
		} catch (EmptyResultDataAccessException e) {
		}
		return res;
	}
	
	public UserActTestClassify getByClassify(Long userId, Long classifyId){
		StringBuilder sql = new StringBuilder();
		sql.append("select test_classify_history_id, act_test_id, act_test_classify_id, user_id, score, start_time, create_time, is_finish from user_act_test_classify where act_test_classify_id=? and user_id=? limit 1;");
		UserActTestClassify res = null;
		try {
			res = mysqlExtendJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(UserActTestClassify.class), classifyId, userId);
		} catch (EmptyResultDataAccessException e) {
		}
		return res;
	}
}
