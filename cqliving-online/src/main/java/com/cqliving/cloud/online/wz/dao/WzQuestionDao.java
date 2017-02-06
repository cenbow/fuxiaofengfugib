package com.cqliving.cloud.online.wz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 问政问题表 JPA Dao
 * Date: 2016-05-09 16:59:11
 * @author Code Generator
 */
public interface WzQuestionDao extends EntityJpaDao<WzQuestion, Long>, WzQuestionDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update WzQuestion set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 递减评论数</p>
	 * @author Tangtao on 2016年6月2日
	 * @param sourceId
	 * @return
	 */
	@Modifying
	@Query("update WzQuestion set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
	int decreaseReplyCount(Long sourceId);
	
	/**
	 * <p>Description: 加评论数</p>
	 * @author huxiaoping on 2016年6月2日
	 * @param sourceId
	 * @return
	 */
	@Modifying
	@Query("update WzQuestion set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int plusReplyCount(Long id);
	
}
