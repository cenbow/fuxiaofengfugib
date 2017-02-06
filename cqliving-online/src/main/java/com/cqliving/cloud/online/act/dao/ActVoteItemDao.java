package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动投票分类选项表 JPA Dao
 * Date: 2016-06-07 09:23:54
 * @author Code Generator
 */
public interface ActVoteItemDao extends EntityJpaDao<ActVoteItem, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActVoteItem set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Modifying
    @Query("update ActVoteItem set actValue = ifnull(actValue,0) + ?2  where id = ?1")
	public int addActValue(Long id,Integer num);
	
	@Query(value="from ActVoteItem where actVoteClassifyId = ?1")
	public List<ActVoteItem> findByClassifyId(Long classifyId);
}
