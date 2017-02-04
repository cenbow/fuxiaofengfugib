package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.org.weixin.module.dalingmusicale.dao.UserVoteDao;
import com.org.weixin.module.dalingmusicale.domain.Contestant;
import com.org.weixin.module.dalingmusicale.domain.UserShareHis;
import com.org.weixin.module.dalingmusicale.domain.UserVote;
import com.org.weixin.module.dalingmusicale.enums.MusicaleEnum;
import com.org.weixin.module.dalingmusicale.manager.ContestantManager;
import com.org.weixin.module.dalingmusicale.manager.UserShareHisManager;
import com.org.weixin.module.dalingmusicale.manager.UserVoteManager;

@Service("userVoteManager")
public class UserVoteManagerImpl extends EntityServiceImpl<UserVote, UserVoteDao> implements UserVoteManager {

	@Autowired
	ContestantManager contestantManager;
	@Autowired
	UserShareHisManager userShareHisManager;
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserVoteManager#userVote(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public synchronized boolean canVote(Long userId,String contestantCode,Byte voteStep) {
		
		Contestant contestant = contestantManager.findByCodeAndStep(contestantCode,voteStep);
		if(null == contestant){
			throw new BusinessException(MusicaleEnum.VOTE_NOT_EXIST.code,MusicaleEnum.VOTE_NOT_EXIST.msg);
		}
		Date now = Dates.now();
		if(now.before(contestant.getBeginTime()) || now.after(contestant.getEndTime())){
			throw new BusinessException(MusicaleEnum.VOTE_NOT_START.code,MusicaleEnum.VOTE_NOT_START.msg);
		}
		List<UserVote> userVotes = this.getEntityDao().findDaily(Dates.todayStart(), Dates.todayEnd(), userId,voteStep);
		if(Contestant.VOTESTEP1 == voteStep){//人气王
			if(CollectionUtils.isEmpty(userVotes)){
				return true;
				//return this.saveUserVote(userId, contestantCode, now);
			}
			if(userVotes.size()>=2){//当日最多投票2次，不分享一次，分享增加一次
				throw new BusinessException(MusicaleEnum.HAD_VOTE.code,MusicaleEnum.HAD_VOTE.msg);
			}
			if(userVotes.size() == 1){//已经投了一次，查看是否分享，分享则再可以投一次
				List<UserShareHis> dailyShare = userShareHisManager.findDaily(userId, UserShareHis.SHARETYPE1);
				if(CollectionUtils.isEmpty(dailyShare)){
					throw new BusinessException(MusicaleEnum.SHARE_CAN_VOTE.code,MusicaleEnum.SHARE_CAN_VOTE.msg);
				}
				//return this.saveUserVote(userId, contestantCode, now);
				return true;
			}
		}
		if(Contestant.VOTESTEP2 == voteStep){//决赛
			if(CollectionUtils.isEmpty(userVotes)){
				//return this.saveUserVote(userId, contestantCode, now);
				return true;
			}
			throw new BusinessException(MusicaleEnum.HAD_VOTE.code,MusicaleEnum.HAD_VOTE.msg);//每人只能投一次
		}
		return true;
	}
	
	@Transactional
	@Override
	public synchronized UserVote userVote(Long userId,String contestantCode,Byte voteStep){
		this.canVote(userId, contestantCode,voteStep);
		Contestant contestant = contestantManager.findByCodeAndStep(contestantCode,voteStep);
		UserVote userVote = new UserVote();
		userVote.setContestantCode(contestantCode);
		userVote.setUserId(userId);
		userVote.setVoteTime(Dates.now());
		userVote.setVoteStep(contestant.getVoteStep());
		//增加获得的票数
		contestantManager.modifyVoteNum(contestantCode, 1);
		return this.getEntityDao().saveAndFlush(userVote);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.UserVoteManager#findJoinedUserNum()
	 */
	@Override
	public long findJoinedUserNum(Byte voteStep) {
		List<UserVote> list = this.getEntityDao().findJoinedUserNum(voteStep);
		if(CollectionUtils.isEmpty(list)){
			return 0;
		}
		return list.size();
	}
	
}
