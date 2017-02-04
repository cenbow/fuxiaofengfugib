package com.org.weixin.module.dalingmusicale.manager;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;

/**
 * 音乐会门票 Manager
 * Date: 2016-09-16 09:09:40
 * @author Code Generator
 */
public interface MusicaleTicketManager extends EntityService<MusicaleTicket> {
	
	//获取正在进行的音乐会
	public MusicaleTicket findById(Long id);
	//修改虚拟量
	public int modifyVirtualNum(Long id,Integer num);
	//修改实际量
	public int modifyActualNum(Long id,Integer num);
	//查找不是音乐会票的数量
	public List<MusicaleTicket> findNotTicket(Long musicaleId);
	//查找音乐会门票
	public List<MusicaleTicket> findTicket();
	//抽取门票
	public MusicaleTicket luckDrawTicket(Long id);
}
