package com.cqliving.cloud.online.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 订单商品评价表 JPA Dao
 * Date: 2016-11-23 17:32:16
 * @author Code Generator
 */
public interface OrderEvaluateDao extends EntityJpaDao<OrderEvaluate, Long>,OrderEvaluateDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update OrderEvaluate set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * 修改状态
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update OrderEvaluate set status = ?1,auditingContent=?3 where id in ?2")
	public int auditing(Byte status,List<Long> ids,String auditingContent);
}
