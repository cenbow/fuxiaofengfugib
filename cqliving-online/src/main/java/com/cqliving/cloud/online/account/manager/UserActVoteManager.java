package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户_活动投票表 Manager
 * Date: 2016-06-07 09:29:49
 * @author Code Generator
 */
public interface UserActVoteManager extends EntityService<UserActVote> {
	
	public void saveUserVote(UserActVote userActVote,UserActList userActList,Long[] itemIds, String sessionCode, String token);
	
	public Long findJoinTotalByVoteId(Long voteId);
	//查询可以投票的次数
	public Integer surplusVote(String sessionCode, String token,Long voteClassifyId);
}
