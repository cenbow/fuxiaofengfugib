package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingImages;

/**
 * 商品图片表 JPA Dao
 * Date: 2016-11-17 15:41:36
 * @author Code Generator
 */
public interface ShoppingImagesDao extends EntityJpaDao<ShoppingImages, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingImages set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Query(value="from ShoppingImages where shoppingGoodsId=?1 and status=3")
	public List<ShoppingImages> findGoodsId(Long goodsId);
}
