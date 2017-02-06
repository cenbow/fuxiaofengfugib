package com.cqliving.cloud.online.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 订单 JPA Dao
 * Date: 2016-11-21 21:35:10
 * @author Code Generator
 */
public interface OrderInfoDao extends EntityJpaDao<OrderInfo, Long>, OrderInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update OrderInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * Title:修改订单支付状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param payforStatus
	 * @param idList
	 * @return
	 */
	@Modifying
    @Query("update OrderInfo set payforStatus = ?1 where id in ?2")
	public int updatePayForStatus(Byte payforStatus, List<Long> idList);
}
