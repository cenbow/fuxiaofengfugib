package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.AppPermissionDao;
import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.cloud.online.config.dto.AppPermissionDto;
import com.cqliving.cloud.online.config.manager.AppPermissionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("appPermissionManager")
public class AppPermissionManagerImpl extends EntityServiceImpl<AppPermission, AppPermissionDao> implements AppPermissionManager {
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(AppPermission.STATUS99, idList);
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

	@Override
	public List<AppPermissionDto> getDtoOfAll() {
		return getEntityDao().getDtoOfAll();
	}
	
}