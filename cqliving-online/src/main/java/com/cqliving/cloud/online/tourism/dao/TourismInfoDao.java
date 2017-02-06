package com.cqliving.cloud.online.tourism.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;

/**
 * 旅游表 JPA Dao
 * Date: 2016-08-23 13:55:25
 * @author Code Generator
 */
public interface TourismInfoDao extends EntityJpaDao<TourismInfo, Long>, TourismInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TourismInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年8月24日
	 * @param id
	 * @param sortNo
	 */
	@Modifying
	@Query("update TourismInfo set sortNo = ?2 where id in ?1")
	void modifySortNo(Long id, Integer sortNo);
	
	/**
     * <p>Description: 增加回复数</p>
     * @author huxiaoping on 2016年8月26日
     * @param id
     */
    @Modifying
    @Query("update TourismInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
    int increaseReplyCount(Long id);

	/**
	 * <p>Description: 递增收藏数</p>
	 * @author Tangtao on 2016年8月26日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update TourismInfo set collectCount = ifnull(collectCount, 0) + 1 where id = ?1")
	int increaseCollectedCount(Long id);

	/**
	 * <p>Description: 递减收藏数</p>
	 * @author Tangtao on 2016年8月26日
	 * @param sourceId
	 * @return
	 */
	@Modifying
	@Query("update TourismInfo set collectCount = ifnull(collectCount, 1) - 1 where id = ?1")
	int decreaseCollectedCount(Long sourceId);

	/**
	 * <p>Description: 递增点赞数</p>
	 * @author Tangtao on 2016年8月26日
	 * @param sourceId
	 * @return
	 */
	@Modifying
    @Query("update TourismInfo set priseCount = ifnull(priseCount, 0) + 1 where id = ?1")
	int increasePraiseCount(Long sourceId);
	
	/**
     * <p>Description: 减少回复数</p>
     * @author huxiaoping on 2016年6月29日
     * @param id
     */
    @Modifying
    @Query("update TourismInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
    int decreaseReplyCount(Long id);
	
    /**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param updateTime
     * @param ids
     * @return
     */
    @Modifying
    @Query("update TourismInfo set sortNo=?1, updator=?2, updatorId=?3, updateTime=?4 where id in ?5")
    public int updateSortNo(Integer sortNo,String updator,Long updateUserId,Date updateTime,List<Long> ids);
}