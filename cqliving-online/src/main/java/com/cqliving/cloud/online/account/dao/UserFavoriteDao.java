package com.cqliving.cloud.online.account.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户收藏表 JPA Dao
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserFavoriteDao extends EntityJpaDao<UserFavorite, Long>, UserFavoriteDaoCustom {

	/**
	 * <p>Description: 批量修改状态</p>
	 * @author Tangtao on 2016年5月16日
	 * @param idList
	 * @param status 
	 */
	@Modifying
	@Query("update UserFavorite set status = ?2 where id in ?1")
	void changeStatus(List<Long> idList, Byte status);

	/**
	 * <p>Description: 修改状态</p>
	 * @author Tangtao on 2016年6月1日
	 * @param userId
	 * @param sourceType
	 * @param sourceId
	 * @param status
	 * @return 
	 */
	@Modifying
	@Query("update UserFavorite set status = ?4 where userId = ?1 and sourceType = ?2 and sourceId = ?3")
	int changeStatus(Long userId, Byte sourceType, Long sourceId, Byte status);

}