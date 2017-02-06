package com.cqliving.cloud.online.account.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户_活动投票表 JPA Dao
 * Date: 2016-06-07 09:29:49
 * @author Code Generator
 */
public interface UserActVoteDao extends EntityJpaDao<UserActVote, Long> {
	
	@Query(value="select count(u) from UserActVote u where userId=?1 and actVoteClassifyId=?2")
	public long countByUserIdVoteClassifyId(Long userId,Long voteClassifyId);
	@Query(value="select count(u) from UserActVote u where userId=?1 and actVoteClassifyId=?2 and createTime >= ?3 and createTime <= ?4")
	public long countTodayByUserIdVoteClassifyId(Long userId,Long voteClassifyId,Date dayBegin,Date dayEnd);
	@Query(value="select count(u) from UserActVote u where userId=?1 and actVoteItemId=?2")
	public long countByUserIdItemId(Long userId,Long itemId);
	@Query(value="select count(u) from UserActVote u where userId=?1 and actVoteItemId=?2 and createTime >= ?3 and createTime <= ?4")
	public long countTodayByUserIdItemId(Long userId,Long itemId,Date dayBegin,Date dayEnd);
	@Query(value="select count(u) from UserActVote u where voteId=?1")
	public long findJoinTotalByVoteId(Long voteId);
}
