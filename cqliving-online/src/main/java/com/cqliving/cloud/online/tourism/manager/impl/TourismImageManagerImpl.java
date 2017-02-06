package com.cqliving.cloud.online.tourism.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.tourism.dao.TourismImageDao;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.manager.TourismImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("tourismImageManager")
public class TourismImageManagerImpl extends EntityServiceImpl<TourismImage, TourismImageDao> implements TourismImageManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(TourismImage.STATUS99, idList);
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
	 * @see com.cqliving.cloud.online.tourism.manager.TourismImageManager#findByTourismId(java.lang.Long)
	 */
	@Override
	public List<TourismImage> findByTourismId(Long tourismId) {
		
		return this.getEntityDao().findByTourismId(tourismId);
	}
}
