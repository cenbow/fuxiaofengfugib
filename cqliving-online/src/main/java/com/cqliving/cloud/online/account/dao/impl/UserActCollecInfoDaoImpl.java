package com.cqliving.cloud.online.account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.cqliving.cloud.online.account.dao.UserActCollectInfoCustom;
import com.cqliving.cloud.online.account.dto.UserActCollectInfoDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public class UserActCollecInfoDaoImpl implements UserActCollectInfoCustom{

//	@Autowired
//    private MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
	/**
	 * Title:获得答题用户收集信息和收集信息的值
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月22日
	 * @param userId
	 * @param actInfoListId
	 * @return
	 */
    public List<UserActCollectInfoDto> getCollectInfoList(Long userId, Long actInfoListId){
    	StringBuilder sql = new StringBuilder();
    	sql.append("select d.id as act_collect_info_id, c.id as act_test_collect_id, c.is_required as is_required, d.name as name, d.`type` as type, d.length as length, e.ID as option_id, e.value as option_value, b.ID as act_test_id, g.value as input_value, f.act_collect_option_id as selectValueId");
    	sql.append(" from act_info_list a ");
    	sql.append(" left join act_test b on a.ID=b.act_info_list_id");
    	sql.append(" left join act_test_collect c on b.ID=c.act_test_id");
    	sql.append(" left join act_collect_info d on c.act_collect_info_id=d.ID");
    	sql.append(" left join act_collect_option e on d.ID=e.act_collect_info_id"); 
    	sql.append(" left join (select b.act_collect_info_id, b.act_collect_option_id from user_act_list a left join user_act_collec_info b on a.ID=b.user_act_list_id where a.user_id=? and a.act_info_list_id=? and b.value is null) f on e.ID=f.act_collect_option_id");
    	sql.append(" left join (select b.act_collect_info_id, b.value from user_act_list a left join user_act_collec_info b on a.ID=b.user_act_list_id where a.user_id=? and a.act_info_list_id=? and b.act_collect_option_id is null) g on d.ID=g.act_collect_info_id");
    	sql.append(" where a.ID=?");
    	sql.append(" order by d.ID asc, e.id asc");
    	List<UserActCollectInfoDto> list = null;
    	try {
			list = mysqlPagedJdbcTemplateV2.queryForList(UserActCollectInfoDto.class, sql.toString(), userId, actInfoListId, userId, actInfoListId, actInfoListId);
		} catch (EmptyResultDataAccessException e) {
		}
    	return list;
    }
}
