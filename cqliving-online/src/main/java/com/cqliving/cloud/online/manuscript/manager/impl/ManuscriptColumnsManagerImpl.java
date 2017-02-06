package com.cqliving.cloud.online.manuscript.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.manuscript.manager.ManuscriptColumnsManager;
import com.cqliving.cloud.online.manuscript.dao.ManuscriptColumnsDao;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("manuscriptColumnsManager")
public class ManuscriptColumnsManagerImpl extends EntityServiceImpl<ManuscriptColumns, ManuscriptColumnsDao> implements ManuscriptColumnsManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ManuscriptColumns.STATUS99, idList);
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
