package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商品推荐表 JPA Dao
 * Date: 2016-11-17 15:41:40
 * @author Code Generator
 */
public interface ShoppingRecommendDao extends EntityJpaDao<ShoppingRecommend, Long>, ShoppingRecommendDaoCustom {
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingRecommend set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ShoppingRecommend set sortNo=?1 where id = ?2")
    public void updateSortNo(Integer sortNo,Long id);

}