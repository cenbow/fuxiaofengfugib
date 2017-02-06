package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.manager.ShoppingDiscountManager;
import com.cqliving.cloud.online.shopping.dao.ShoppingDiscountDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingDiscount;
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("shoppingDiscountManager")
public class ShoppingDiscountManagerImpl extends EntityServiceImpl<ShoppingDiscount, ShoppingDiscountDao> implements ShoppingDiscountManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingDiscount.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
}
