package com.org.weixin.module.szc.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.wechat.framework.service.EntityService;

import com.org.common.SessionUser;
import com.org.weixin.module.szc.domain.SzcUserAward;
import com.org.weixin.module.szc.dto.SzcUserAwardDto;

/**
 * 奖品领取历史表 Manager
 * Date: 2016-09-02 11:24:23
 * @author Code Generator
 */
public interface SzcUserAwardManager extends EntityService<SzcUserAward> {
	
	//核销奖品
	public void verifyAward(String convertCode);
	//抽奖
	public SzcUserAward luckDraw(String phone,SessionUser sessionUser);
	//查找该奖品的所有核销数量
	public long findVerifyAll(String awardCode,Date beginTime,Date endTime);
	//查找指定时间的中奖数量
	public long findAwardNumDaily(Date date,Date date2,Integer district);
	//查找指定时间的核销数量
	public long findVerifyNumDaily(Date date,Date date2,Integer district);
	//根据条件查找中奖统计
	public List<SzcUserAward> queryByConditions(Map<String,Object> conditions,Map<String,Boolean> sortMap);
	//获取用户中奖情况
	public List<SzcUserAward> findByConvertCode(String convertCode);
	//统计奖品发放情况
	public List<SzcUserAwardDto> statistics(Map<String,Object> conditions,Map<String,Boolean> sortMap);
	//统计用户参与情况
	public List<SzcUserAwardDto> statisticsUser();
	//统计总得用户参与数,不分区域
	public long statisticsJoinNum();
}
