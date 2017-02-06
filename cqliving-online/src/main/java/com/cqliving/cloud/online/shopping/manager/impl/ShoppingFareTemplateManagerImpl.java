package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingFareTemplateDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateDetailManager;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateManager;
import com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateRegionDetailManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingFareTemplateManager")
public class ShoppingFareTemplateManagerImpl extends EntityServiceImpl<ShoppingFareTemplate, ShoppingFareTemplateDao> implements ShoppingFareTemplateManager {
	
	@Autowired
	ShoppingFareTemplateDetailManager shoppingFareTemplateDetailManager ; 
	@Autowired
	ShoppingFareTemplateRegionDetailManager shoppingFareTemplateRegionDetailManager;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingFareTemplate.STATUS99, idList);
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
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateManager#saveOrUpdate(com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void saveOrUpdate(ShoppingFareTemplate fareTemplate) {
		
		boolean isNew = false;
		if(null == fareTemplate.getId()){
			isNew = true;
		}
		this.update(fareTemplate);
		List<ShoppingFareTemplateDetail> tmpDetails = fareTemplate.getTempDetails();
		//比较删除
		List<ShoppingFareTemplateDetail> sqlList = shoppingFareTemplateDetailManager.findFareTemplateId(fareTemplate.getId());
		shoppingFareTemplateDetailManager.compare(tmpDetails, sqlList);
		
		for(ShoppingFareTemplateDetail tmpDetail : tmpDetails){
			if(isNew){
				tmpDetail.setId(null);
			}
			if(null == tmpDetail.getId()){
				tmpDetail.setStatus(ShoppingFareTemplateDetail.STATUS3);
				tmpDetail.setSortNo(10000);
				tmpDetail.setCreateTime(fareTemplate.getUpdateTime());
				tmpDetail.setCreator(fareTemplate.getUpdator());
				tmpDetail.setCreatorId(fareTemplate.getUpdatorId());
			}
			tmpDetail.setUpdateTime(fareTemplate.getUpdateTime());
			tmpDetail.setUpdator(fareTemplate.getUpdator());
			tmpDetail.setUpdatorId(fareTemplate.getUpdatorId());
			tmpDetail.setFareTemplateId(fareTemplate.getId());
			
			shoppingFareTemplateDetailManager.save(tmpDetail);
			//比较删除
			List<ShoppingFareTemplateRegionDetail> sqlRegionList = shoppingFareTemplateRegionDetailManager.findByTmpDetailId(tmpDetail.getId());
			shoppingFareTemplateRegionDetailManager.compareSql(tmpDetail.getRegions(), sqlRegionList);
			if(CollectionUtils.isEmpty(tmpDetail.getRegions()))continue;
			for(ShoppingFareTemplateRegionDetail region : tmpDetail.getRegions()){
				if(isNew){
					region.setId(null);
				}
				if(null == region.getId()){
					region.setCreateTime(fareTemplate.getUpdateTime());
					region.setCreator(fareTemplate.getUpdator());
					region.setCreatorId(fareTemplate.getUpdatorId());
				}
				region.setFareTemplateId(fareTemplate.getId());
				region.setFareTemplateDetailId(tmpDetail.getId());
				shoppingFareTemplateRegionDetailManager.save(region);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingFareTemplateManager#findConditions(java.util.Map)
	 */
	@Override
	public List<ShoppingFareTemplateDto> findConditions(Map<String, Object> conditions) {
		
		return this.getEntityDao().findConditions(conditions);
	}
}
