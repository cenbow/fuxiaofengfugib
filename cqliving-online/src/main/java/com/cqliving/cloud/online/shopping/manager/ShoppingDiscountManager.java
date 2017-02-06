package com.cqliving.cloud.online.shopping.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.shopping.domain.ShoppingDiscount;

/**
 * 商品折扣表 Manager
 * Date: 2016-11-17 15:41:14
 * @author Code Generator
 */
public interface ShoppingDiscountManager extends EntityService<ShoppingDiscount> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
}
