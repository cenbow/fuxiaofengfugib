package com.org.weixin.module.jywth.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.jywth.domain.JywthAwardHis;

/**
 * 红包领取历史表 Service
 *
 * Date: 2016-04-02 13:11:46
 *
 * @author Acooly Code Generator
 *
 */
public interface JywthAwardHisService extends EntityService<JywthAwardHis> {

	//当天是否已中奖(控制一天只能中奖一次)false:未中，true:当天已中
	public boolean hasGetAwardToday(String phone);
	//修改状态(是否已领奖)
	public int updateStatus(String phone,String exchangeCode,byte status);
	//中奖后放入历史表
	public JywthAwardHis saveAwardHis(String phone,String awardCode,String awardName);
	//查询当天中奖
	public List<JywthAwardHis> queryAwardByDate(Date date);
	//查询当天核销
	public List<JywthAwardHis> queryVerifyAwardByDate(Date date);
	//核销
	public void verifyAward(String exchangeCode);
	
	public int takeAward(Long hisId); 
	
	public List<JywthAwardHis> queryAwardByConditions(Map<String,String> searchMap,Map<String,Boolean> sortMap);
}
