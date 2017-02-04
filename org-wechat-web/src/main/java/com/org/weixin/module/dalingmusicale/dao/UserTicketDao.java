package com.org.weixin.module.dalingmusicale.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.UserTicket;

/**
 * 用户抢票表 JPA Dao
 * Date: 2016-09-16 09:10:01
 * @author Code Generator
 */
public interface UserTicketDao extends EntityJpaDao<UserTicket, Long>,UserTicketDaoCustom {
	
	//用户总共获取到的音乐会门票
	public List<UserTicket> findByUserIdAndMusicaleTicketId(Long userId,Long musicaleId);
	//查找当日用户总共的抢票次数
	@Query(value="from UserTicket where grabTime>=?1 and grabTime<=?2 and userId=?3")
	public List<UserTicket> findByUserIdDaily(Date dayStart,Date dayEnd,Long userId);
	
	@Query(value="from UserTicket where verifyCode=?1")
	public List<UserTicket> findFromVerifyCode(String verifyCode);
	
	//用户参与次数
	@Query(value="select count(t) from UserTicket t")
	public long findUserJoinNum();
	//参与的用户量
	@Query(value="from UserTicket t group by t.userId")
	public List<UserTicket> findJoinedUserTotal();
}
