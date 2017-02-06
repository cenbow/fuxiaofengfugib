package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户_活动投票表 Service
 * Date: 2016-06-07 09:29:49
 * @author Code Generator
 */
public interface UserActVoteService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActVote>> queryForPage(PageInfo<UserActVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActVote> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActVote domain);
	/** @author Code Generator *****end*****/
	public Response<Void> saveUserVote(UserActVote userActVote,UserActList userActList,Long[] itemIds,String sessionCode,String token);
	
	//查询投票的总参加人数
	public Response<Long> findJoinTotalByVoteId(Long voteId);
	//查询剩余的投票数
	public Response<Integer> surplusVote(String sessionCode,String token,Long voteClassifyId);
}
