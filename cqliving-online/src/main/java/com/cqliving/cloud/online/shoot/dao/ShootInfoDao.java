package com.cqliving.cloud.online.shoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
/**
 * 随手拍表 JPA Dao
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
public interface ShootInfoDao extends EntityJpaDao<ShootInfo, Long>, ShootInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShootInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * <p>Description: 递增评论量</p>
	 * @author Tangtao on 2016年6月15日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ShootInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int increaseReplyCount(Long id);
	
	/**
	 * <p>Description: 递增点赞量</p>
	 * @author Tangtao on 2016年6月15日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ShootInfo set priseCount = ifnull(priseCount, 0) + 1 where id = ?1")
	int increasePraiseCount(Long id);
	
	/**
	 * <p>Description: 递减评论量</p>
	 * @author Tangtao on 2016年6月15日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ShootInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
	int decreaseReplyCount(Long id);
	
	/**
	 * <p>Description: 递减点赞量</p>
	 * @author Tangtao on 2016年6月15日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ShootInfo set priseCount = ifnull(priseCount, 1) - 1 where id = ?1")
	int decreasePraiseCount(Long id);
	
}
