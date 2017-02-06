package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateRegionDetailDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateRegionDetailManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingFareTemplateRegionDetailManager")
public class ShoppingFareTemplateRegionDetailManagerImpl extends EntityServiceImpl<ShoppingFareTemplateRegionDetail, ShoppingFareTemplateRegionDetailDao> implements ShoppingFareTemplateRegionDetailManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateRegionDetailManager#compareSql(java.util.List, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void compareSql(List<ShoppingFareTemplateRegionDetail> list, List<ShoppingFareTemplateRegionDetail> sqlList) {
		if(CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(sqlList))return;
		
		Iterator<ShoppingFareTemplateRegionDetail> regions = sqlList.iterator();
		while(regions.hasNext()){
			ShoppingFareTemplateRegionDetail sqlRegion = regions.next();
			boolean delete = true;
			
			for(ShoppingFareTemplateRegionDetail d : list){
				if(null != d.getId() && d.getId().longValue() == sqlRegion.getId().longValue()){
					delete = false;
				}
			}
			if(delete){
				this.remove(sqlRegion);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateRegionDetailManager#findByTmpDetailId(java.lang.Long)
	 */
	@Override
	public List<ShoppingFareTemplateRegionDetail> findByTmpDetailId(Long tmpDetailId) {
		
		if(null == tmpDetailId)return null;
		
		return this.getEntityDao().findByTmpDetailId(tmpDetailId);
	}
}
