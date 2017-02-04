package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.google.common.collect.Lists;
import com.org.weixin.module.dalingmusicale.constant.MusicaleConstant;
import com.org.weixin.module.dalingmusicale.dao.MusicaleTicketDao;
import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;
import com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager;
import com.org.weixin.module.szc.util.RandomUtil;

@Service("musicaleTicketManager")
public class MusicaleTicketManagerImpl extends EntityServiceImpl<MusicaleTicket, MusicaleTicketDao> implements MusicaleTicketManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#getNowTicket()
	 */
	@Override
	public MusicaleTicket findById(Long id) {
		List<MusicaleTicket> tickets = this.getEntityDao().findAllTicketsById(id);
		if(CollectionUtils.isEmpty(tickets)){
			return null;
		}
		List<MusicaleTicket> newTickets = Lists.newArrayList();
		for(MusicaleTicket musicaleTicket : tickets){//过滤没有票的
			if(1<=musicaleTicket.getVirtualNum().intValue()){
				newTickets.add(musicaleTicket);
			}
		}
		if(CollectionUtils.isEmpty(newTickets))return null;
		return tickets.get(0);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#modifyVirtualNum(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public int modifyVirtualNum(Long id, Integer num) {
		
		return this.getEntityDao().modifyVirtualNum(id, num);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#modifyActualNum(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public int modifyActualNum(Long id, Integer num) {
		
		return this.getEntityDao().modifyActualNum(id, num);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#findNotTicket()
	 */
	@Override
	public List<MusicaleTicket> findNotTicket(Long musicaleId) {
		
		String ticketName = "%"+MusicaleConstant.NOT_TICKET_PREFIX+musicaleId+"%";
		return this.getEntityDao().findNotTicket(ticketName);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#findTicket()
	 */
	@Override
	public List<MusicaleTicket> findTicket() {
		
		return this.getEntityDao().findTicket();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#luckDrawTicket(java.lang.Long)
	 */
	@Override
	public MusicaleTicket luckDrawTicket(Long id) {
		
		List<MusicaleTicket> tickets = this.getEntityDao().findAllTicketsById(id);
		if(CollectionUtils.isEmpty(tickets))return null;
		List<MusicaleTicket> newTickets = Lists.newArrayList();
		int total = 0;
		for(MusicaleTicket musicaleTicket:tickets){
			int virtualNum = musicaleTicket.getVirtualNum();
			total += virtualNum;
			for(int i=0;i<virtualNum;i++){
				newTickets.add(musicaleTicket);
			}
		}
		int ticketIndex = RandomUtil.getRandom(total);
		return newTickets.get(ticketIndex);
	}
	
}
