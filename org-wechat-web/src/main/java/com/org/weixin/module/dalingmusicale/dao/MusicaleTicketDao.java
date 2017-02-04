package com.org.weixin.module.dalingmusicale.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;

/**
 * 音乐会门票 JPA Dao
 * Date: 2016-09-16 09:09:40
 * @author Code Generator
 */
public interface MusicaleTicketDao extends EntityJpaDao<MusicaleTicket, Long>,MusicaleTicketDaoCustom {

	//修改虚拟量
	@Modifying
	@Query(value="update MusicaleTicket set virtualNum = ifnull(virtualNum,0) + ?2 where id=?1")
	public int modifyVirtualNum(Long id,Integer num);
	//修改实际量
	@Modifying
	@Query(value="update MusicaleTicket set actualNum = ifnull(actualNum,0) + ?2 where id=?1 ")
	public int modifyActualNum(Long id,Integer num);
	
	//查找不是音乐会票的数量
	@Query(value="from MusicaleTicket where ticketType=1 and name like ?")
	public List<MusicaleTicket> findNotTicket(String name);
	//查找音乐会门票
	@Query(value="from MusicaleTicket where ticketType=2")
	public List<MusicaleTicket> findTicket();
}
