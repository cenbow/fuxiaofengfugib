package com.cqliving.cloud.online.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商铺图片表 JPA Dao
 * Date: 2016-05-16 20:41:26
 * @author Code Generator
 */
public interface ShopImageDao extends EntityJpaDao<ShopImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShopImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月21日
	 * @param shopId
	 */
	@Modifying
	@Query("update ShopImage set status = 99 where shopId = ?1")
	void deleteByShop(Long shopId);
}
