package com.cqliving.cloud.online.security.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.security.dao.SysResTypeDao;
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.manager.SysResTypeManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("sysResTypeManager")
public class SysResTypeManagerImpl extends EntityServiceImpl<SysResType, SysResTypeDao> implements SysResTypeManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(SysResType.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(SysResType.STATUS99, idList);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysResTypeManager#findExistsRes()
	 */
	@Override
	public List<SysResType> findExistsRes() {
		
		return this.getEntityDao().findExistsRes();
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysResTypeManager#findExistsResByUserId(java.lang.Long)
	 */
	@Override
	public List<SysResType> findExistsResByUserId(Long userId) {
		
		return this.getEntityDao().findExistsResByUserId(userId);
	}
}
