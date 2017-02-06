package com.cqliving.cloud.online.building.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 楼房信息表 JPA Dao
 * Date: 2016-10-10 17:07:08
 * @author Code Generator
 */
public interface BuildingInfoDao extends EntityJpaDao<BuildingInfo, Long>,BuildingInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update BuildingInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * Title:修改排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	@Modifying
    @Query("update BuildingInfo set sortNo = ?2 where id in ?1")
	public int modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description: 递增评论数</p>
	 * @author Tangtao on 2016年11月10日
	 * @param id
	 */
	@Modifying
    @Query("update BuildingInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int increaseReplyCount(Long id);

	/**
	 * <p>Description: 递增点赞数</p>
	 * @author Tangtao on 2016年11月10日
	 * @param sourceId
	 * @return
	 */
	@Modifying
    @Query("update BuildingInfo set praiseCount = ifnull(praiseCount, 0) + 1 where id = ?1")
	int increasePraiseCount(Long sourceId);
	
}