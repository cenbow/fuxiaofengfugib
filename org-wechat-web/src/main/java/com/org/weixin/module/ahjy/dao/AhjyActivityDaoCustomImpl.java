/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.wechat.framework.dao.jdbc.MysqlPagedJdbcTemplate;

import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.org.weixin.module.ahjy.dto.AhjyActivityCountDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月21日
 */
@Repository
public class AhjyActivityDaoCustomImpl {

	@Resource(name="extendJdbcTemplate")
	MysqlPagedJdbcTemplate mysqlPagedJdbcTemplate;
	
    /*select a.join_num,b.award_num,c.take_num from (select count(*) join_num,1 a
	from ahjy_user_activity au where date(au.join_time) <=DATE('2016-04-17') and
	date(au.join_time)> date('2016-04-08')) a,
	(select count(*) award_num,1 b from ahjy_activity aa where aa.award_physical is not null
	and date(aa.begin_time) <=DATE('2016-04-17') and date(aa.begin_time) > date('2016-04-08'))b,
	(select count(*) take_num,1 c from ahjy_activity aa where date(aa.begin_time)<=DATE('2016-04-17')
	and aa.is_get_award=1 and date(aa.begin_time)> date('2016-04-08')) c where a.a=b.b and b.b=c.c;

	select a.join_num,b.award_num,c.take_num,c.begin_time from
	(select count(*) join_num,date(au.join_time) join_time from
	ahjy_user_activity au where date(au.join_time) <=DATE('2016-04-17')
	and date(au.join_time) > date('2016-04-08') GROUP BY date(au.join_time)) a,
	(select count(*) award_num,date(au.begin_time) join_time from ahjy_activity au
	where date(au.begin_time) > DATE('2016-04-08') and date(au.begin_time) <=DATE('2016-04-17')
	and au.award_physical is not null group by DATE(au.begin_time )) b,
	(select count(*) take_num,date(aa.begin_time) begin_time from ahjy_activity aa
	 where date(aa.begin_time) > DATE('2016-04-08') and date(aa.begin_time) <=DATE('2016-04-17')
	and aa.is_get_award=1 group by DATE(aa.begin_time)) c
	where date(a.join_time)=date(b.join_time) and date(b.join_time)=date(c.begin_time);

	select aua.user_id,swu.nickname,aua.activity_id,award.`code`,award.`name`,aa.award_gain_time,
	if(aa.is_get_award=1,'已领取','未领取') is_get_award,
	if(aa.award_physical is not null,'已中','未中奖') is_award
	from ahjy_user_activity aua INNER JOIN sys_wechat_user swu on aua.user_id=swu.id
	LEFT JOIN ahjy_activity aa on aua.user_id=aa.award_user_id and aa.ID=aua.activity_id
	LEFT JOIN ahjy_award award on award.`code`=aa.award_physical
	where date(aua.join_time)> DATE('2016-04-08') and date(aua.join_time)<=DATE('2016-04-17')
	ORDER BY aua.user_id ,is_get_award*/
	/**
	 * <p>Description:如果你进入此页面，证明你在修改bug，看到这些sql不要慌张，因为我也是这么过来的
	 *    颤抖吧，孩子。。。。还有更痛苦的事在等你。。。
	 * </p>
	 * @param conditions
	 * @return
	 * @author fuxiaofeng on 2016年4月21日
	 */
	public Map<String,List<AhjyActivityCountDto>> statistics(Map<String,Object> conditions){
		
		Map<String,List<AhjyActivityCountDto>> count = Maps.newHashMap();
		
		List<AhjyActivityCountDto> statics = Lists.newArrayList();
		
		if(null == conditions){
			conditions = Maps.newHashMap();
			conditions.put("LTE_endTime", DateUtil.formatDateNowDefault());
		}
		
		String endTime = (String) conditions.get("LTE_endTime");
		String beginTime = (String) conditions.get("GTE_beginTime");
		
		if(StringUtil.isEmpty(beginTime)){
			beginTime = "2014-04-01";
		}
		
		if(StringUtil.isEmpty(endTime)){
			endTime = DateUtil.formatDateNowDefault();
		}
		
		//用户参与活动明细
		StringBuilder sql = new StringBuilder();
		sql.append("select aa.award_user_id user_id,swu.nickname,aa.id activity_id,award.`code`,award.`name`,aa.award_gain_time,");
		//sql.append("if(aa.is_get_award=1,'已领取','未领取') is_get_award,");
		//sql.append("if(aa.award_physical is not null,'已中','未中奖') is_award ");
		sql.append("aa.is_get_award,");
		sql.append("if(aa.award_physical is not null,'1','0') is_award ");
		sql.append("from sys_wechat_user swu  ");
		sql.append("inner JOIN ahjy_activity aa on swu.id=aa.award_user_id  ");
		sql.append("LEFT JOIN ahjy_award award on award.`code`=aa.award_physical ");
		sql.append("where date(aa.begin_time)>= DATE(?) and date(aa.begin_time)<=DATE(?) ");
		sql.append("ORDER BY aa.award_user_id ,is_get_award ");
		
		statics = mysqlPagedJdbcTemplate.queryForList(AhjyActivityCountDto.class, sql.toString(), new Object[]{beginTime,endTime});
		count.put("activityDetailList", statics);
		
		//每日活动情况数量统计
		sql = new StringBuilder();
		sql.append("select a.join_num,b.award_num,c.take_num,c.begin_time from ");
		sql.append("(select count(*) join_num,date(au.begin_time) join_time from ");
		sql.append("ahjy_activity au where  ");
		sql.append(" date(au.begin_time) >= date(?) and date(au.begin_time) <=DATE(?) GROUP BY date(au.begin_time)) a,");
		sql.append("(select count(*) award_num,date(au.begin_time) join_time from ahjy_activity au ");
		sql.append("where date(au.begin_time) >= DATE(?) and date(au.begin_time) <=DATE(?) ");
		sql.append("and au.award_physical is not null group by DATE(au.begin_time )) b, ");
		sql.append("(select count(*) take_num,date(aa.begin_time) begin_time from ahjy_activity aa ");
		sql.append("where date(aa.begin_time) >= DATE(?) and date(aa.begin_time) <=DATE(?) ");
		sql.append("and aa.is_get_award=1 group by DATE(aa.begin_time)) c ");
		sql.append("where date(a.join_time)=date(b.join_time) and date(b.join_time)=date(c.begin_time) ");
		
		statics = mysqlPagedJdbcTemplate.queryForList(AhjyActivityCountDto.class, sql.toString(), 
				new Object[]{beginTime,endTime,beginTime,endTime,beginTime,endTime});
		count.put("activityDailyCount", statics);
		
		//总的活动情况数量统计
		sql = new StringBuilder();
		sql.append("select a.join_num,b.award_num,c.take_num from (select count(*) join_num,1 a ");
		sql.append("from ahjy_activity au where date(au.begin_time)>= date(?) and date(au.begin_time) <=DATE(?) ) a,");
		sql.append("(select count(*) award_num,1 b from ahjy_activity aa where aa.award_physical is not null ");
		sql.append("and date(aa.begin_time) >= date(?) and date(aa.begin_time) <=DATE(?) )b,");
		sql.append("(select count(*) take_num,1 c from ahjy_activity aa where date(aa.begin_time)>= date(?) ");
		sql.append("and aa.is_get_award=1 and date(aa.begin_time)<=DATE(?) ) c where a.a=b.b and b.b=c.c ");
		
		statics = mysqlPagedJdbcTemplate.queryForList(AhjyActivityCountDto.class, sql.toString(), 
				new Object[]{beginTime,endTime,beginTime,endTime,beginTime,endTime});
		count.put("activityTotalCount", statics);
		
		return count;
	}
}
