package com.cqliving.cloud.online.topic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 话题表 JPA Dao
 * Date: 2016-07-14 09:45:12
 * @author Code Generator
 */
public interface TopicInfoDao extends EntityJpaDao<TopicInfo, Long>, TopicInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TopicInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:推荐新数据之前把旧的推荐取消
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月18日
	 * @param isRecommend0
	 * @param appId
	 * @param isRecommend1
	 * @return
	 */
	@Modifying
	@Query("update TopicInfo set isRecommend = ?1 where appId = ?2 and isRecommend = ?3")
	public int resetIsRecommendStatus(Byte isRecommend0, Long appId, Byte isRecommend1);
	
	/**
     * <p>Description: 增加回复数</p>
     * @author Tangtao on 2016年6月29日
     * @param id
     */
    @Modifying
    @Query("update TopicInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
    int increaseReplyCount(Long id);
    
    /**
     * <p>Description: 减少回复数</p>
     * @author Tangtao on 2016年6月29日
     * @param id
     */
    @Modifying
    @Query("update TopicInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
    int decreaseReplyCount(Long id);
    
    /**
     * Title:修改是否推荐状态 
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月17日
     * @param isTop
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TopicInfo set isTop = ?1 where id in ?2")
    public int updateTopStatus(Byte isTop,List<Long> ids);
}