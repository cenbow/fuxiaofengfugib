package com.org.weixin.module.dalingmusicale.manager;

import java.util.List;
import java.util.Map;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.dto.UserTicketDto;

/**
 * 用户抢票表 Manager
 * Date: 2016-09-16 09:10:01
 * @author Code Generator
 */
public interface UserTicketManager extends EntityService<UserTicket> {
	
	//抢票
	public UserTicket grabTicket(String phone,Long userId,Long musicaleId);
	//核销门票
	public UserTicket verifyTicket(String verifyCode);
	//根据核销码查询中奖用户信息
	public UserTicketDto findByVerifyCode(String verifyCode);
	//根据用户微信的openid查找用户的门票信息
	public List<UserTicketDto> findMyTicket(Long userId);
	//用户参与次数
	public long findUserJoinNum();
	//参与的用户量
	public long findJoinedUserTotal();
	
	 public List<UserTicketDto> statistics(Map<String,Object> conditions);
}
