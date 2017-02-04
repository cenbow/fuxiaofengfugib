package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.weixin.module.dalingmusicale.dao.UserTicketDao;
import com.org.weixin.module.dalingmusicale.domain.MusicaleTicket;
import com.org.weixin.module.dalingmusicale.domain.UserShareHis;
import com.org.weixin.module.dalingmusicale.domain.UserTicket;
import com.org.weixin.module.dalingmusicale.dto.UserTicketDto;
import com.org.weixin.module.dalingmusicale.enums.MusicaleEnum;
import com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager;
import com.org.weixin.module.dalingmusicale.manager.SmsCodeManager;
import com.org.weixin.module.dalingmusicale.manager.UserShareHisManager;
import com.org.weixin.module.dalingmusicale.manager.UserTicketManager;
import com.org.weixin.module.szc.util.RandomUtil;
import com.org.weixin.system.service.SysWechatUserService;

@Service("userTicketManager")
public class UserTicketManagerImpl extends EntityServiceImpl<UserTicket, UserTicketDao> implements UserTicketManager {

	@Autowired
	MusicaleTicketManager musicaleTicketManager;
	@Autowired
	UserShareHisManager userShareHisManager;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	SmsCodeManager smsCodeManager;
	@Autowired
	SysWechatUserService sysWechatUserService;
	
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#grabTicket(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public synchronized UserTicket grabTicket(String phone,Long userId, Long musicaleId) {

		MusicaleTicket musicaleTicket = musicaleTicketManager.findById(musicaleId);
		if(null == musicaleTicket){
			throw new BusinessException(MusicaleEnum.MUSICALE_NO_TICKET.code,MusicaleEnum.MUSICALE_NO_TICKET.msg);
		}
		Date now = Dates.now();
		if(now.before(musicaleTicket.getBeginTime())){
			throw new BusinessException(MusicaleEnum.MUSICALE_NOT_START.code,MusicaleEnum.MUSICALE_NOT_START.msg);
		}
		if(now.after(musicaleTicket.getEndTime())){
			throw new BusinessException(MusicaleEnum.MUSICALE_OVER.code,MusicaleEnum.MUSICALE_OVER.msg);
		}
		//当日用户总的抢票次数不能大于4
		List<UserTicket> data = this.getEntityDao().findByUserIdDaily(Dates.todayStart(), Dates.todayEnd(), userId);
		if(CollectionUtils.isNotEmpty(data) && data.size() >=4){
			throw new BusinessException(MusicaleEnum.MUSICALE_NO_TURN.code,MusicaleEnum.MUSICALE_NO_TURN.msg);
		}
		//用户获得的门票数不能大于改门票的实际能获取的数量
		Integer cangetNum = musicaleTicket.getGetNum();
		List<UserTicket> userTotalTickets = this.getEntityDao().findByUserIdAndMusicaleTicketId(userId, musicaleId);
		if(cangetNum.intValue() >=1 && CollectionUtils.isNotEmpty(userTotalTickets) && userTotalTickets.size() >= cangetNum.intValue()){
			//浪费机会
			return this.saveUserTicket(phone, userId, musicaleId, "00000000", UserTicket.TAKESTATUS0);
		}
		//用户当天获取到的门票数
		List<UserTicket> todayTickets = this.getEntityDao().findByUserIdDaily(userId, musicaleId);
		if(CollectionUtils.isEmpty(todayTickets)){//当天未抢直接抽奖
			return this.luckDrawTicket(phone, userId, musicaleId);
		}
		if(todayTickets.size()>=2){//同一张门票当天最多只有两次机会
			throw new BusinessException(MusicaleEnum.MUSICALE_ONE_NO_TURN.code,MusicaleEnum.MUSICALE_ONE_NO_TURN.msg);
			//return this.saveUserTicket(phone, userId, musicaleId, "00000000", UserTicket.TAKESTATUS0);
		}
		List<UserShareHis> userShare = userShareHisManager.findDaily(userId, UserShareHis.SHARETYPE2);
		if(todayTickets.size()==1){//刚好抢了一次，提示分享后再抢
			if(CollectionUtils.isEmpty(userShare)){
				UserTicket userTicket= todayTickets.get(0);
				if(0>= userTicket.getTakeStatus().intValue()){//未中奖
					throw new BusinessException(MusicaleEnum.MUSICALE_NO_GOT_NO_SHARE.code,MusicaleEnum.MUSICALE_NO_GOT_NO_SHARE.msg);
				}
				throw new BusinessException(MusicaleEnum.MUSICALE_SHARE.code,MusicaleEnum.MUSICALE_SHARE.msg);
			}
			return this.luckDrawTicket(phone, userId, musicaleId);
		}
		return null;
	}
	
	@Transactional
	private UserTicket luckDrawTicket(String phone,Long userId,Long musicaleId){
		MusicaleTicket awardTicket = musicaleTicketManager.luckDrawTicket(musicaleId);
		String verifyCode = RandomUtil.randomUUid(8);
		musicaleTicketManager.modifyVirtualNum(awardTicket.getId(), -1);
		Byte takeStatus = 1;
		if(MusicaleTicket.TICKETTYPE1.byteValue() == awardTicket.getTicketType().byteValue()){
			verifyCode = "00000000";
			takeStatus = 0;
		}
		return this.saveUserTicket(phone, userId,musicaleId,verifyCode,takeStatus);
	}
	
	@Transactional
	private UserTicket saveUserTicket(String phone,Long userId,Long musicaleId,String verifyCode,Byte takeStatus){
		UserTicket userTicket = new UserTicket();
		userTicket.setGrabTime(Dates.now());
		userTicket.setMusicaleTicketId(musicaleId);
		userTicket.setPhone(phone);
		userTicket.setTakeStatus(takeStatus);
		userTicket.setUserId(userId);
		userTicket.setVerifyCode(verifyCode);
		return this.getEntityDao().saveAndFlush(userTicket);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#verifyTicket(java.lang.String)
	 */
	@Override
	@Transactional
	public UserTicket verifyTicket(String verifyCode) {
		List<UserTicket> userTickets = this.getEntityDao().findFromVerifyCode(verifyCode);
		if(CollectionUtils.isEmpty(userTickets)){
			throw new BusinessException(MusicaleEnum.VERIFY_TICKET_NOT_EXIST.code,MusicaleEnum.VERIFY_TICKET_NOT_EXIST.msg);
		}
		UserTicket ut = userTickets.get(0);
		if(UserTicket.TAKESTATUS2 == ut.getTakeStatus()){
			throw new BusinessException(MusicaleEnum.VERIFY_TICKET_HAD_HANDLE.code,MusicaleEnum.VERIFY_TICKET_HAD_HANDLE.msg);
		}
		if(UserTicket.TAKESTATUS0 == ut.getTakeStatus()){
			throw new BusinessException(MusicaleEnum.VERIFY_TICKET_NO_TICKET.code,MusicaleEnum.VERIFY_TICKET_NO_TICKET.msg);
		}
		MusicaleTicket musicaleTicket = musicaleTicketManager.get(ut.getMusicaleTicketId());
		if(0>= musicaleTicket.getActualNum()){
			throw new BusinessException(MusicaleEnum.VERIFY_TICKET_NOT_SURPLUS.code,MusicaleEnum.VERIFY_TICKET_NOT_SURPLUS.msg);
		}
		ut.setVerifyTime(Dates.now());
		ut.setTakeStatus(UserTicket.TAKESTATUS2);
		musicaleTicketManager.modifyActualNum(ut.getMusicaleTicketId(), -1);
		return this.update(ut);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#findByVerifyCode(java.lang.String)
	 */
	@Override
	public UserTicketDto findByVerifyCode(String verifyCode) {
		return this.getEntityDao().findByVerifyCode(verifyCode);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#findMyTicket(java.lang.String)
	 */
	@Override
	public List<UserTicketDto> findMyTicket(Long userId) {
		return this.getEntityDao().findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#findUserJoinNum()
	 */
	@Override
	public long findUserJoinNum() {
		
		return this.getEntityDao().findUserJoinNum();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#findJoinedUserTotal()
	 */
	@Override
	public long findJoinedUserTotal() {
		
		List<UserTicket> userTickets = this.getEntityDao().findJoinedUserTotal();
		if(CollectionUtils.isEmpty(userTickets))return 0l;
		return userTickets.size();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserTicketManager#statistics(java.util.Map)
	 */
	@Override
	public List<UserTicketDto> statistics(Map<String, Object> conditions) {
		
		return this.getEntityDao().statistics(conditions);
	}
}
