/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.szc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom;
import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.dto.SzcUserAwardDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月4日
 */
public class SzcUserAwardDaoImpl implements SzcUserAwardDaoCustom {

	@Resource(name="extendJdbcTemplate")
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom#queryByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public List<SzcUserAward> queryByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {

       StringBuilder sql =  new StringBuilder();
       sql.append("select * from szc_user_award");
		return mysqlPagedJdbcTemplate.queryForList(SzcUserAward.class, sql.toString(), conditions, sortMap);
	}
	
	/*SELECT t4.*,(sa.actual_num - IFNULL(t3.verify_num,0)) surplus_num,
	IFNULL(t2.award_num,0) award_num,IFNULL(t3.verify_num,0) verify_num
	from szc_user_award t4 INNER JOIN szc_award sa on sa.`code` = t4.award_code
	LEFT JOIN (SELECT COUNT(sua.award_code) award_num,sua.award_code FROM
	szc_user_award sua GROUP BY sua.award_code) t2
	on t4.award_code=t2.award_code LEFT JOIN
	(SELECT COUNT(sua.award_code) verify_num,sua.award_code FROM
	szc_user_award sua where sua.take_status=2 
	group by sua.award_code) t3 on t3.award_code = t4.award_code
	where t4.award_code not like '%NOT_AWARD%'
	group by t4.district,t4.award_code*/
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom#statistics(java.util.Map, java.util.Map)
	 */
	@Override
	public List<SzcUserAwardDto> statistics(Map<String, Object> conditions, Map<String, Boolean> sortMap) {

        StringBuilder sql  = new StringBuilder();
        sql.append("SELECT t4.*,(sa.actual_num - IFNULL(t3.verify_num,0)) surplus_num,");
		sql.append("IFNULL(t2.award_num,0) award_num,IFNULL(t3.verify_num,0) verify_num ");
		sql.append("from szc_user_award t4 INNER JOIN szc_award sa on sa.`code` = t4.award_code ");
		sql.append("LEFT JOIN (SELECT COUNT(sua.award_code) award_num,sua.award_code FROM ");
		sql.append("szc_user_award sua GROUP BY sua.award_code) t2 ");
		sql.append("on t4.award_code=t2.award_code LEFT JOIN ");
		sql.append("(SELECT COUNT(sua.award_code) verify_num,sua.award_code FROM ");
		sql.append("szc_user_award sua where sua.take_status=2  ");
		sql.append("group by sua.award_code) t3 on t3.award_code = t4.award_code ");
		sql.append("where t4.award_code not like '%NOT_AWARD%' ");
		sql.append("group by t4.district,t4.award_code ");
		return mysqlPagedJdbcTemplate.queryForList(SzcUserAwardDto.class, sql.toString(), conditions, sortMap);
	}

	
	/*SELECT 
	  CASE
	    szc_award.district 
	    WHEN 1 
	    THEN '合肥' 
	    WHEN 2 
	    THEN '南京' 
	    WHEN 3 
	    THEN '璧山' 
	  END AS '区域',
	  szc_award.name AS '奖品名称',
	  szc_award.actual_num '实际奖品数',
	  IFNULL(duihuan.cnt,0) '中奖数',
	  IFNULL((
	    szc_award.actual_num - duihuan.cnt
	  ), szc_award.actual_num)'奖品实际剩余数',
	  szc_award.`virtual_num` '奖品剩余预发布数' 
	FROM
	  szc_award 
	  LEFT JOIN 
	    (SELECT 
	      COUNT(*) cnt,
	      award_code 
	    FROM
	      szc_user_award 
	    WHERE szc_user_award.take_status = 2 
	    GROUP BY szc_user_award.award_code) AS duihuan 
	    ON szc_award.`code` = duihuan.award_code 
	WHERE szc_award.`code` NOT IN (
	    'NOT_AWARD1',
	    'NOT_AWARD2',
	    'NOT_AWARD3'
	  ) ORDER BY szc_award.`district`*/
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom#newStatistics()
	 */
	@Override
	public List<SzcUserAwardDto> newStatistics() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT szc_award.district ,szc_award.name AS award_name,szc_award.actual_num,");
		sql.append("IFNULL(duihuan.cnt,0) verify_num,IFNULL((szc_award.actual_num - duihuan.cnt");
		sql.append("), szc_award.actual_num) surplus_num,szc_award.`virtual_num` ");
		sql.append("FROM szc_award LEFT JOIN (SELECT  COUNT(*) cnt, award_code FROM szc_user_award ");
		sql.append("WHERE szc_user_award.take_status = 2  GROUP BY szc_user_award.award_code) AS duihuan ");
		sql.append(" ON szc_award.`code` = duihuan.award_code WHERE szc_award.`code` NOT IN (");
		sql.append("'NOT_AWARD1','NOT_AWARD2','NOT_AWARD3' ) ORDER BY szc_award.`district`");
		return mysqlPagedJdbcTemplate.queryForList(sql.toString(), SzcUserAwardDto.class);
	}

	/*SELECT CASE
    szc_award.`district` 
    WHEN 1 
    THEN '合肥' 
    WHEN 2 
    THEN '南京' 
    WHEN 3 
    THEN '璧山' END AS '区域', szc_award.`name` AS '奖品名称',
    IFNULL(
    CASE zong.take_status
    WHEN 1
    THEN '未核销'
    WHEN  2
    THEN '已核销' END,''
    ) AS '中奖情况',IFNULL(zong.cnt,0) AS '人数'
  FROM szc_award
  LEFT JOIN  
  (SELECT COUNT(*) AS cnt,szc_user_award.`award_code`,szc_user_award.`take_status`
  FROM szc_user_award
  GROUP BY szc_user_award.`award_code`,szc_user_award.`take_status`) AS zong ON zong.award_code = szc_award.`code`
  ORDER BY szc_award.`district`,szc_award.`id`;*/
	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom#statisticsUser()
	 */
	@Override
	public List<SzcUserAwardDto> statisticsUser() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT szc_award.`district` , szc_award.`name` AS award_name,");
		sql.append("zong.take_status,IFNULL(zong.cnt,0) AS user_num ");
		sql.append("FROM szc_award LEFT JOIN  (SELECT COUNT(*) AS cnt,szc_user_award.`award_code`,szc_user_award.`take_status` ");
		sql.append("FROM szc_user_award ");
		sql.append("GROUP BY szc_user_award.`award_code`,szc_user_award.`take_status`) AS zong ON zong.award_code = szc_award.`code` ");
		sql.append("ORDER BY szc_award.`district`,szc_award.`id` ");
		
		return mysqlPagedJdbcTemplate.queryForList(sql.toString(), SzcUserAwardDto.class);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.dao.SzcUserAwardDaoCustom#statisticsJoinNum()
	 */
	@Override
	public long statisticsJoinNum() {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM `sys_wechat_user` WHERE module_id =22");
		
		Number number = mysqlPagedJdbcTemplate.queryForObject(sql.toString(),Long.class);
		return null == number ? 0 : number.longValue();
	}

}
