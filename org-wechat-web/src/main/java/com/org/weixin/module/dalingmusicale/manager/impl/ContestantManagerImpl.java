package com.org.weixin.module.dalingmusicale.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.ChineseToPinyin;
import com.org.weixin.module.dalingmusicale.dao.ContestantDao;
import com.org.weixin.module.dalingmusicale.domain.Contestant;
import com.org.weixin.module.dalingmusicale.manager.ContestantManager;

@Service("contestantManager")
public class ContestantManagerImpl extends EntityServiceImpl<Contestant, ContestantDao> implements ContestantManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#findByVoteStep(java.lang.Byte)
	 */
	@Override
	public List<Contestant> findByVoteStep(Byte voteStep) {
		return this.getEntityDao().findByVoteStep(voteStep);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#modifyVoteNum(java.lang.String, java.lang.Integer)
	 */
	@Override
	@Transactional
	public int modifyVoteNum(String contestantCode, Integer voteNum) {
		
		return this.getEntityDao().modifyVoteNum(contestantCode, voteNum);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#findByCode(java.lang.String)
	 */
	@Override
	public Contestant findByCodeAndStep(String code,Byte voteStep) {
		List<Contestant> contestant = this.getEntityDao().findByCodeAndStep(code,voteStep);
		if(CollectionUtils.isEmpty(contestant))return null;
		return contestant.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.MusicaleTicketManager#nameToSpell()
	 */
	@Override
	@Transactional
	public int nameToSpell() {
		List<Contestant> data = this.getAll();
		for(Contestant contestant : data){
			contestant.setNameSpell(ChineseToPinyin.chineseToPinyin(contestant.getName()));
			this.getEntityDao().saveAndFlush(contestant);
		}
		return data.size();
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#beginVote()
	 */
	@Override
	@Transactional
	public void beginVote(Byte voteStep) {
		if(null == voteStep)return;
		
		Date beginTime = Dates.now();
		Date endTime = DateUtil.addMonths(beginTime, 1);
		this.getEntityDao().updateTimeByVoteStep(beginTime, endTime, voteStep);
		
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#endVote()
	 */
	@Override
	@Transactional
	public void endVote(Byte voteStep) {
		if(null == voteStep)return;
		Date endTime = Dates.now();
		this.getEntityDao().endContest(endTime, voteStep);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.module.dalingmusicale.manager.ContestantManager#findCampion()
	 */
	@Override
	public List<Contestant> findCampion() {
		
		return this.getEntityDao().findCampion();
	}
}
