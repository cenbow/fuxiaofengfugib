package com.org.weixin.module.szc.manager;

import java.util.Date;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.szc.domain.SzcAward;

/**
 * szc_砂之船奖品表 Manager
 * Date: 2016-09-02 11:24:16
 * @author Code Generator
 */
public interface SzcAwardManager extends EntityService<SzcAward> {
	
	//获取当前进行的抽奖时间段(是哪个时间段的奖品)
	public SzcAward getSzcAward(Integer district);
	//根据奖品code查找奖品
	public SzcAward findByCode(String awardCode);
	//获取当前正在抽奖的所有数量
	public long getAllSzcAward(Date date,Integer district);
	//抽奖
	public SzcAward luckDraw(Integer district);
	
	public int minusVirtualNum(String awardCode,int num);
}
