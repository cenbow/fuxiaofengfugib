package com.org.weixin.module.dalingmusicale.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.UserVote;

/**
 * 用户投票信息 JPA Dao
 * Date: 2016-09-16 09:10:09
 * @author Code Generator
 */
public interface UserVoteDao extends EntityJpaDao<UserVote, Long> {
	
	//查找当日的投票数
	@Query(value="from UserVote where voteTime>= ?1 and voteTime<=?2 and userId=?3 and voteStep=?4")
	public List<UserVote> findDaily(Date start,Date end,Long userId,Byte voteStep);
	
	@Query(value="from UserVote where voteStep=?1 group by userId")
	public List<UserVote> findJoinedUserNum(Byte voteStep);
}
