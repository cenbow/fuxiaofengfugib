package com.cqliving.cloud.online.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商铺表 JPA Dao
 * Date: 2016-05-16 20:41:01
 * @author Code Generator
 */
public interface ShopInfoDao extends EntityJpaDao<ShopInfo, Long>, ShopInfoDaoCustom {
	/**
	 * <p>Description: 递减收藏数</p>
	 * @author Tangtao on 2016年6月1日
	 * @param shopId
	 * @return 
	 */
	@Modifying
	@Query("update ShopInfo set collectCount = ifnull(collectCount, 1) - 1 where id = ?1")
	int decreaseCollectedCount(Long shopId);

	/**
	 * <p>Description: 递减回复数</p>
	 * @author Tangtao on 2016年6月2日
	 * @param shopId
	 * @return
	 */
	@Modifying
	@Query("update ShopInfo set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
	int decreaseReplyCount(Long shopId);

	/**
	 * <p>Description: 递增收藏数</p>
	 * @author Tangtao on 2016年6月1日
	 * @param shopId
	 * @return 
	 */
	@Modifying
	@Query("update ShopInfo set collectCount = ifnull(collectCount, 0) + 1 where id = ?1")
	int increaseCollectedCount(Long shopId);

	/**
	 * <p>Description: 递增评论量</p>
	 * @author Tangtao on 2016年6月1日
	 * @param shopId
	 * @return
	 */
	@Modifying
	@Query("update ShopInfo set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int increaseReplyCount(Long shopId);

	/**
	 * 修改状态
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update ShopInfo set status = ?1 where id in ?2")
	public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月6日
	 * @param typeId
	 * @param excludeId
	 */
	@Modifying
	@Query("update ShopInfo set topType = 0 where id <> ?2 and typeId = ?1")
	void cancelTop(Long typeId, Long excludeId);

	/**
	 * Title:审核
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月24日
	 * @param status
	 * @param content
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update ShopInfo set status = ?1, auditDesc=?2 where id in ?3")
	public int audit(Byte status, String content, List<Long> ids);
}
