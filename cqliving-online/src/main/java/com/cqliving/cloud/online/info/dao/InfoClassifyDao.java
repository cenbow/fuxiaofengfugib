package com.cqliving.cloud.online.info.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯栏目表 JPA Dao
 * Date: 2016-04-15 09:44:28
 * @author Code Generator
 */
public interface InfoClassifyDao extends EntityJpaDao<InfoClassify, Long>, InfoClassifyDaoCustom {

	/**
	 * <p>Description: 修改状态</p>
	 * @author Tangtao on 2016年4月25日
	 * @param id
	 * @param status
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	@Modifying
	@Query("update InfoClassify set status = ?2, updateTime = ?3, updatorId = ?4, updator = ?5 where id = ?1")
	void changeStatus(Long id, Byte status, Date updateTime, Long updatorId, String updator);

	/**
	 * <p>Description: 批量修改状态</p>
	 * @author Tangtao on 2016年5月4日
	 * @param idList
	 * @param status
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	@Modifying
	@Query("update InfoClassify set status = ?2, updateTime = ?3, updatorId = ?4, updator = ?5 where id in ?1")
	void changeStatusBatch(List<Long> idList, Byte status, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 批量修改状态</p>
	 * @author Tangtao on 2016年5月10日
	 * @param idList
	 * @param fromStatus
	 * @param toStatus
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	@Modifying
	@Query("update InfoClassify set status = ?3, updateTime = ?4, updatorId = ?5, updator = ?6  where id in ?1 and status = ?2")
	void changeStatusBatch(List<Long> idList, Byte fromStatus, Byte toStatus, Date updateTime, Long updatorId, String updator);

	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年5月10日
	 * @param ids
	 */
	@Modifying
	@Query("update InfoClassify set sortNo = " + Integer.MAX_VALUE + " where id in ?1")
	void clearSortNo(List<Long> ids);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param id
	 * @param sortNo
	 */
	@Modifying
	@Query("update InfoClassify set sortNo = ?2 where id in ?1")
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description: 修改加入专题状态</p>
	 * @author Tangtao on 2016年5月13日
	 * @param id
	 * @param addSpecialStatus
	 */
	@Modifying
	@Query("update InfoClassify set addSpecialStatus = ?2 where id = ?1")
	void changeAddSpecialStatus(Long id, Byte addSpecialStatus);

	/**
	 * <p>Description: 批量修改加入专题状态</p>
	 * @author Tangtao on 2016年5月25日
	 * @param idList
	 * @param addspecialstatus
	 */
	@Modifying
	@Query("update InfoClassify set addSpecialStatus = ?2 where id in ?1")
	void changeAddSpecialStatus(List<Long> idList, Byte addspecialstatus);

	@Modifying
	@Query("update InfoClassify set replyCount = ifnull(replyCount, 0) + ?2 where id = ?1")
	int addReplyCount(Long infoClassifyId,int num);	

	@Modifying
	@Query("update InfoClassify set viewCount = ifnull(viewCount, 0) + ?2 where id = ?1")
	int addViewCount(Long infoClassifyId,int num);	
    /**
     * Title:修改是否推荐状态 
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月17日
     * @param isRecommend
     * @param ids
     * @return
     */
	@Modifying
    @Query("update InfoClassify set isRecommend = ?1 where id in ?2")
    public int updateRecommendStatus(Byte isRecommend,List<Long> ids);
	
	@Modifying
	@Query("update InfoClassify set updator = ?2,updatorId=?3,updateTime=?4 where id=?1")
	public void updateUpdate(Long id,String updator,Long updateId,Date updateTime);
	
	@Modifying
	@Query("update InfoClassify set isViewWechat = ?2 where informationId=?1")
	public void updateIsViewWechatByInfoId(Long informationId,Byte isViewWechat);
	
	@Modifying
	@Query("update InfoClassify set columnsId = ?2,updator = ?3,updatorId=?4,updateTime=?5 where id in ?1")
	public void updateAppColumn(List<Long> classifyIds,Long columnId,String updator,Long updateId,Date updateTime);
}