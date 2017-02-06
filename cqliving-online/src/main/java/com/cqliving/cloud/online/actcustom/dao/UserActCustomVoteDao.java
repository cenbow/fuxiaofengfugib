package com.cqliving.cloud.online.actcustom.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;

/**
 * 用户自定义投票活动表 JPA Dao
 * Date: 2017-01-03 10:31:16
 * @author Code Generator
 */
public interface UserActCustomVoteDao extends EntityJpaDao<UserActCustomVote, Long> {

	@Modifying
    @Query("from UserActCustomVote where actCustomVoteItemId=?1 and (userId=?2 or sessionId=?3) and actQrcodeCode=?4 and createTime>=?5 and createTime<=?6")
    public List<UserActCustomVote> getVoteListById(Long actCustomVoteItemId,Long userId,String userSetionCode,String actQrcodeCode,Date stateTime,Date endTime);
	
}
