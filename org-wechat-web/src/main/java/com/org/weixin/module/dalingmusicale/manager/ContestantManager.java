package com.org.weixin.module.dalingmusicale.manager;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.Contestant;

/**
 * 选手 Manager
 * Date: 2016-09-16 09:09:34
 * @author Code Generator
 */
public interface ContestantManager extends EntityService<Contestant> {
	
	public List<Contestant> findByVoteStep(Byte voteStep);
	
	public Contestant findByCodeAndStep(String code,Byte voteStep);
	
	public int modifyVoteNum(String contestantCode,Integer voteNum);
    //汉子转拼音
	public int nameToSpell();
	//开始投票
	public void beginVote(Byte voteStep);
	//结束投票
	public void endVote(Byte voteStep);
	
	public List<Contestant> findCampion();
}
