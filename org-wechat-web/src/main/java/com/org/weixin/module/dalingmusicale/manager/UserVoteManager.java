package com.org.weixin.module.dalingmusicale.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.UserVote;

/**
 * 用户投票信息 Manager
 * Date: 2016-09-16 09:10:09
 * @author Code Generator
 */
public interface UserVoteManager extends EntityService<UserVote> {
	
	public UserVote userVote(Long userId,String contestantCode,Byte voteStep);
	//是否有资格投票
	public boolean canVote(Long userId,String contestantCode,Byte voteStep);
	
	public long findJoinedUserNum(Byte voteStep);
}
