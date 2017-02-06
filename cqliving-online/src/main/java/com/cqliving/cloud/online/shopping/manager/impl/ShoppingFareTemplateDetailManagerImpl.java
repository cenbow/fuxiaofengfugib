package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDetailDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateDetailManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingFareTemplateDetailManager")
public class ShoppingFareTemplateDetailManagerImpl extends EntityServiceImpl<ShoppingFareTemplateDetail, ShoppingFareTemplateDetailDao> implements ShoppingFareTemplateDetailManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingFareTemplateDetail.STATUS99, idList);
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

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateDetailManager#compare(java.util.List, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void compare(List<ShoppingFareTemplateDetail> detailList, List<ShoppingFareTemplateDetail> sqlList) {
		if(CollectionUtils.isEmpty(detailList) || CollectionUtils.isEmpty(sqlList)){
			return;
		}
		Iterator<ShoppingFareTemplateDetail> sqlIt = sqlList.iterator();
		while(sqlIt.hasNext()){
			ShoppingFareTemplateDetail sql = sqlIt.next();
			boolean delete = true;
			for(ShoppingFareTemplateDetail detail : detailList){
				if(null != detail.getId() && detail.getId().longValue() == sql.getId().longValue())
				{
					delete = false;
					break;
				}
			}
			if(delete){
				sql.setStatus(ShoppingFareTemplateDetail.STATUS99);
				this.getEntityDao().saveAndFlush(sql);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateDetailManager#findFareTemplateId(java.lang.Long)
	 */
	@Override
	public List<ShoppingFareTemplateDetail> findFareTemplateId(Long templateId) {
		
		if(null == templateId)return null;
		return this.getEntityDao().findFareTemplateId(templateId);
	}
}
