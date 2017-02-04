package com.org.weixin.module.jywth.service;

import java.text.ParseException;
import java.util.Map;

import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityService;

import com.org.weixin.module.jywth.domain.JywthAward;

/**
 * jywth_揭阳万泰汇奖品表 Service
 *
 * Date: 2016-04-02 13:11:45
 *
 * @author Acooly Code Generator
 *
 */
public interface JywthAwardService extends EntityService<JywthAward> {

	public JywthAward getAward(String phone,String code) throws BusinessException;
	
	//更新数量
	public void updateAwardNum(Map<String,Integer> codenum) throws ParseException;
}
