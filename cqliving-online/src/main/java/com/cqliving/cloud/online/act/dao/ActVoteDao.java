package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动投票表 JPA Dao
 * Date: 2016-06-07 09:23:04
 * @author Code Generator
 */
public interface ActVoteDao extends EntityJpaDao<ActVote, Long>,ActVoteDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActVote set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Query(value="from ActVote where actInfoListId=?1 and status=3 ")
	public ActVote findByActInfoListId(Long actInfoListId);
	
}
