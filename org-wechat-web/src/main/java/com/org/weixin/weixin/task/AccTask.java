package com.org.weixin.weixin.task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.Constants;
import com.org.weixin.client.api.TicketAPI;
import com.org.weixin.client.api.TokenAPI;
import com.org.weixin.client.bean.base.ticket.Ticket;
import com.org.weixin.client.bean.base.token.Token;
import com.org.weixin.system.domain.SysAccountWechats;
import com.org.weixin.system.dto.AccInfoDto;
import com.org.weixin.system.service.SysAccountWechatsService;

/**
 * Title:微信账户定时任务
 * <p>Description:提供微信账户定时刷新微信token和ticket定时刷新功能</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月26日
 */
@Component
public class AccTask{

	private Logger logger = LoggerFactory.getLogger(AccTask.class);
	
	@Resource
	SpyMemcachedClient spyMemcachedClient;//memcache service
	@Resource
	SysAccountWechatsService accountWechatsService;//微信账户 service
	
	/**
	 * 微信账户信息定时刷新
	 * <p>Description:微信账户信息定时刷新(延时30秒等配置项刷新，每隔118分钟刷新)</p>
	 * @author fengshi on 2015年7月26日
	 */
	@Scheduled(initialDelay=1000 * 30, fixedRate=1000 * 60 * 59 * 2)
	public void RefreshAcc() {
		List<SysAccountWechats> list = accountWechatsService.queryAccountList();
		Map<Long,AccInfoDto> accInfoMap = new LinkedHashMap<Long,AccInfoDto>();
		//循环刷新acc信息
		for (SysAccountWechats account : list) {
			if(StringUtils.isNotBlank(account.getAppid()) && StringUtils.isNotBlank(account.getSecret())){
				//缓存基本信息
				AccInfoDto info = new AccInfoDto();
				BeanUtils.copyProperties(account, info);
				//获取AccessToken
				try{
					Token token = TokenAPI.token(account.getAppid(), account.getSecret());
					info.setAccessToken(token.getAccess_token());
					
					//获取ticket
					Ticket ticket = TicketAPI.ticketGetticket(token.getAccess_token());
					info.setTicket(ticket.getTicket());
					
					accInfoMap.put(account.getId(), info);
					
					//设置状态
					info.setStatus(1);
				} catch(Exception e) {
					e.printStackTrace();
					logger.error("刷新微信账户信息失败 account={},错误信息...>>>>>>{}",account,e);
				}
			} else{
				logger.error("刷新微信账户信息失败，appid或secret为空！account:{}",account);
			}
		}
		spyMemcachedClient.set(Constants.Weixin.WEIXIN_ACC_MAP, Constants.Weixin.WEIXIN_TOKEN_MAP_TIME, accInfoMap);
		logger.debug("刷新微信账户信息失败完毕！accInfoMap=",accInfoMap);
	}

}
