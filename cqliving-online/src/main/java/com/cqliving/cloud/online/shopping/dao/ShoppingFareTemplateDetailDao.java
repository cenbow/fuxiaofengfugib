package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;

/**
 * 运费模板明细表 JPA Dao
 * Date: 2016-11-17 15:41:25
 * @author Code Generator
 */
public interface ShoppingFareTemplateDetailDao extends EntityJpaDao<ShoppingFareTemplateDetail, Long> , ShoppingFareTemplateDetailDaoCustom{
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingFareTemplateDetail set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	@Query("from ShoppingFareTemplateDetail where fareTemplateId = ?1 and status=3 ")
	public List<ShoppingFareTemplateDetail> findFareTemplateId(Long templateId);
}
