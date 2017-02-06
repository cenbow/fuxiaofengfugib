package com.cqliving.cloud.online.tourism.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.tourism.dao.TourismSpecialDao;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.manager.TourismSpecialManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("tourismSpecialManager")
public class TourismSpecialManagerImpl extends EntityServiceImpl<TourismSpecial, TourismSpecialDao> implements TourismSpecialManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus((byte) 99, idList);
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
	 * @see com.cqliving.cloud.online.tourism.manager.TourismSpecialManager#queryForSpecialSub(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<TourismInfoDto> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().queryForSpecialSub(pageInfo, conditions, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.manager.TourismSpecialManager#updateSortNo(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateSortNo(Long tourismSpecialId,Integer sortNo) {
		if(null == tourismSpecialId || null == sortNo)return;
		this.getEntityDao().updateSortNo(tourismSpecialId, sortNo);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.manager.TourismSpecialManager#queryForNoJoinSpecial(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<TourismInfoDto> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().queryForNoJoinSpecial(pageInfo, conditions, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.manager.TourismSpecialManager#joinSecial(java.lang.Long, java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager")
	public void joinSecial(TourismSpecial tourismSpecial, Long[] refTourismId) {

		if(null == tourismSpecial  || null == refTourismId || refTourismId.length<=0)
			return;
        
		for(Long refId : refTourismId){
			List<TourismSpecial> hasSpecial = this.getEntityDao().findBySpecialIdRefId(tourismSpecial.getTourismId(), refId);
			if(CollectionUtils.isEmpty(hasSpecial)){
				tourismSpecial.setRefTourismId(refId);
				TourismSpecial newTourismSpecial = new TourismSpecial();
				BeanUtils.copyProperties(tourismSpecial, newTourismSpecial);
				this.getEntityDao().saveAndFlush(newTourismSpecial);
			}
		}
	}

	@Override
	public List<TourismInfoDto> queryForSubList(Long appId, Long tourismId, double lat, double lng) {
		return getEntityDao().queryForSubList(appId, tourismId, lat, lng);
	}
	
}