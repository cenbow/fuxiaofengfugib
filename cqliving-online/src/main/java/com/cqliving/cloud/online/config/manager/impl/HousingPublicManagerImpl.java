package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.HousingPublicDao;
import com.cqliving.cloud.online.config.domain.HousingPublic;
import com.cqliving.cloud.online.config.manager.HousingPublicManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("housingPublicManager")
public class HousingPublicManagerImpl extends EntityServiceImpl<HousingPublic, HousingPublicDao> implements HousingPublicManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(HousingPublic.STATUS99, idList);
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
