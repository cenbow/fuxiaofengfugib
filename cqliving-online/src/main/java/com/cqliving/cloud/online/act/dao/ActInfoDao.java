package com.cqliving.cloud.online.act.dao;

import java.util.Date;
import java.util.List;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.act.domain.ActInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 活动表 JPA Dao
 * Date: 2016-06-07 09:21:22
 * @author Code Generator
 */
public interface ActInfoDao extends EntityJpaDao<ActInfo, Long>,ActInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * 修改状态
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update ActInfo set status=?1, updator=?2, updatorId=?3, updateTime=?4 where id in ?5")
	public int updateStatus(Byte status,String updator,Long updateUserId,Date updateTime,List<Long> ids);
	
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
	@Query("update ActInfo set sortNo=?1, updator=?2, updatorId=?3, updateTime=?4 where id in ?5")
	public int updateSortNo(Integer sortNo,String updator,Long updateUserId,Date updateTime,List<Long> ids);
	
	/**
	 * 取消推荐到首页
	 * @author huxiaoping
	 * @param isRecommend0 设置是否推荐
	 * @param updator 修改人
	 * @param updatorId 修改人id
	 * @param updateTime 修改时间
	 * @param appId 所属客户端
	 * @param isRecommend1 原来的推荐状态
	 * @return
	 */
	@Modifying
	@Query("update ActInfo set isRecommend=?1, updator=?2, updatorId=?3, updateTime=?4 where appId = ?5 and isRecommend = ?6")
	public void cancelRecommend(Byte isRecommend0,String updator,Long updateUserId,Date updateTime,Long appId,Byte isRecommend1);
	
	/**
	 * 推荐到首页
	 * @author huxiaoping
	 * @param isRecommend0 设置是否推荐
	 * @param updator 修改人
	 * @param updatorId 修改人id
	 * @param updateTime 修改时间
	 * @param recommendImageUrl 推荐图片
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ActInfo set isRecommend=?1, updator=?2, updatorId=?3, updateTime=?4, recommendImageUrl=?5 where id = ?6")
	public void recommend(Byte isRecommend0,String updator,Long updateUserId,Date updateTime,String recommendImageUrl,Long id);
	
	/**
     * Title:取消推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param updator
     * @param updateUserId
     * @param id
     * @return
     */
    @Modifying
    @Query("update ActInfo set isRecommend=?1, updator=?2, updatorId=?3, updateTime=?4 where id = ?5")
    public void cancelRecommend(Byte isRecommend,String updator,Long updateUserId,Date updateTime,Long id);
    
    /**
     * <p>Description: 增加回复数</p>
     * @author Tangtao on 2016年6月29日
     * @param id
     */
    @Modifying
    @Query("update ActInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
    int increaseReplyCount(Long id);
    
    /**
     * <p>Description: 减少回复数</p>
     * @author Tangtao on 2016年6月29日
     * @param id
     */
    @Modifying
    @Query("update ActInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
    int decreaseReplyCount(Long id);

	/**
	 * <p>Description: 递增点赞数</p>
	 * @author Tangtao on 2016年7月21日
	 * @param id
	 * @return
	 */
    @Modifying
    @Query("update ActInfo set priseCount = ifnull(priseCount, 0) + 1 where id = ?1")
	int increasePraiseCount(Long id);
	
}
