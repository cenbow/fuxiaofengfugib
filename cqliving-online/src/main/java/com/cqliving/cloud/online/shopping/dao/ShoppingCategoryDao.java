package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商品分类表 JPA Dao
 * Date: 2016-11-17 15:11:46
 * @author Code Generator
 */
public interface ShoppingCategoryDao extends EntityJpaDao<ShoppingCategory, Long> ,ShoppingCategoryDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingCategory set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Modifying
    @Query(value = "update ShoppingCategory set sortNo=?2,parentId=?3 where id=?1")
    public int sort(Long id, Integer sortNo, Long parentId);
	
	/**
     * 查询App下最大的排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月21日下午1:10:51
     */
    @Query(nativeQuery=true,value="SELECT MAX(sort_no) FROM shopping_category WHERE app_id = ?1")
    public Integer getMaxSortNo(Long appId);
}
