package com.cqliving.cloud.online.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商铺分类表 JPA Dao
 * Date: 2016-05-16 20:41:22
 * @author Code Generator
 */
public interface ShopCategoryDao extends EntityJpaDao<ShopCategory, Long>, ShopCategoryDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShopCategory set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param id
	 * @param sortNo
	 */
	@Modifying
	@Query("update ShopCategory set sortNo = ?2 where id = ?1")
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年6月17日
	 * @param ids
	 */
	@Modifying
	@Query("update ShopCategory set sortNo = " + Integer.MAX_VALUE + " where id in ?1")
	void clearSortNo(List<Long> ids);
	
}
