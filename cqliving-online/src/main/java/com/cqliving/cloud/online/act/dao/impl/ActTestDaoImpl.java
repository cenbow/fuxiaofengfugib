package com.cqliving.cloud.online.act.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.act.dao.ActTestDaoCustom;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.framework.common.dao.jdbc.MysqlExtendJdbcTemplate;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月23日
 */
public class ActTestDaoImpl implements ActTestDaoCustom{

    @Autowired
    private MysqlExtendJdbcTemplate mysqlExtendJdbcTemplate;
    
    /**
     * Title:根据actInfoList获得活动配置
     * <p>Description:只获取答题类型 and 是已激活状态</p>
     * @author DeweiLi on 2016年6月23日
     * @param actInfoListId
     * @return
     */
	public ActTest getByActListInfo(Long actInfoListId){
		StringBuilder sql = new StringBuilder();
		sql.append("select b.* from act_info_list a left join act_test b on a.ID=b.act_info_list_id where a.`type`=? and a.`status`=? and a.ID=?");
		ActTest actTest = null;
		try {
			actTest = mysqlExtendJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActTest.class), ActInfoList.TYPE4, ActInfoList.STATUS3, actInfoListId);
		} catch (EmptyResultDataAccessException e) {
		}
		return actTest;
	}
	
	/**
	 * Title:根据actTestClass获得
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月23日
	 * @param actTestClassifyId
	 * @return
	 */
	public ActTest getByActTestClassify(Long actTestClassifyId){
		StringBuilder sql = new StringBuilder();
		sql.append("select b.* from act_test_classify a left join act_test b on a.act_test_id=b.ID where a.ID=?");
		ActTest actTest = null;
		try {
			actTest = mysqlExtendJdbcTemplate.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(ActTest.class), actTestClassifyId);
		} catch (EmptyResultDataAccessException e) {
		}
		return actTest;
	}
}
