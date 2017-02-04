package com.org.weixin.module.szc.dao;


import java.util.List;
import java.util.Map;

import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.dto.SzcUserAwardDto;

/**
 * 奖品领取历史表 JPA Dao
 * Date: 2016-09-02 11:24:23
 * @author Code Generator
 */
public interface SzcUserAwardDaoCustom {
	
	//根据条件查找中奖统计
	public List<SzcUserAward> queryByConditions(Map<String,Object> conditions,Map<String,Boolean> sortMap);
	//统计
	public List<SzcUserAwardDto> statistics(Map<String, Object> conditions, Map<String, Boolean> sortMap);
	
	public List<SzcUserAwardDto> newStatistics();
	
	//统计用户参与情况
	public List<SzcUserAwardDto> statisticsUser();
	//统计总得用户参与数,不分区域
	public long statisticsJoinNum();
}
