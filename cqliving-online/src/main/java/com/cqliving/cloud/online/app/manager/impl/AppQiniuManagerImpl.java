package com.cqliving.cloud.online.app.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppQiniuDao;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.manager.AppQiniuManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("appQiniuManager")
public class AppQiniuManagerImpl extends EntityServiceImpl<AppQiniu, AppQiniuDao> implements AppQiniuManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(AppQiniu.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(AppQiniu.STATUS99, idList);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppQiniuManager#findByAppId(java.lang.Long)
	 */
	@Override
	public List<AppQiniu> findByAppId(Long appId) {
		
		return this.getEntityDao().findByAppId(appId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.manager.AppQiniuManager#findByDefault(java.lang.Byte)
	 */
	@Override
	public List<AppQiniu> findByDefault(Byte isDefault) {
		
		return this.getEntityDao().findByDefault(isDefault);
	}
}
