package com.cqliving.cloud.online.joke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.joke.domain.JokeInfo;

/**
 * 段子表 JPA Dao
 * Date: 2016-06-28 11:18:14
 * @author Code Generator
 */
public interface JokeInfoDao extends EntityJpaDao<JokeInfo, Long>, JokeInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update JokeInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 批量修改状态</p>
	 * @author Tangtao on 2016年6月28日
	 * @param idList
	 * @param fromStatus
	 * @param toStatus
	 */
	@Modifying
	@Query("update JokeInfo set status = ?3 where id in ?1 and status = ?2")
	 void changeStatusBatch(List<Long> idList, Byte fromStatus, Byte toStatus);

	/**
	 * <p>Description: 增加回复数</p>
	 * @author Tangtao on 2016年6月29日
	 * @param id
	 */
	@Modifying
	@Query("update JokeInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int increaseReplyCount(Long id);
	
	/**
	 * <p>Description: 减少回复数</p>
	 * @author Tangtao on 2016年6月29日
	 * @param id
	 */
	@Modifying
	@Query("update JokeInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
	int decreaseReplyCount(Long id);

	/**
	 * <p>Description: 递增点赞数</p>
	 * @author Tangtao on 2016年6月29日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update JokeInfo set praiseCount = ifnull(praiseCount, 0) + 1 where id = ?1")
	int increasePraiseCount(Long id);

	/**
	 * <p>Description: 递增鄙视数</p>
	 * @author Tangtao on 2016年7月5日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update JokeInfo set despiseCount = ifnull(despiseCount, 0) + 1 where id = ?1")
	int increaseDespiseCount(Long id);
	 
}
