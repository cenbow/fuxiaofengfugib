package com.cqliving.cloud.online.app.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AllMediaDao;
import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.cloud.online.app.manager.AllMediaManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("allMediaManager")
public class AllMediaManagerImpl extends EntityServiceImpl<AllMedia, AllMediaDao> implements AllMediaManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(AllMedia.STATUS99, idList);
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
	 * @see com.cqliving.cloud.online.app.manager.AllMediaManager#queryPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<AllMediaDto> queryPage(PageInfo<AllMediaDto> pageInfo, Map<String, Object> map,
			Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().queryPage(pageInfo, map, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AllMediaManager#updateSortNo(java.lang.Long, java.lang.Integer)
	 */
	@Override
	@Transactional
	public void updateSortNo(Long id, Integer sortNo) {
		
		if(null == id || null == sortNo )return;
		
		this.getEntityDao().updateSortNo(id, sortNo);
	}
}
