package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.config.dao.RecommendAppDao;
import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.cloud.online.config.manager.RecommendAppManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("recommendAppManager")
public class RecommendAppManagerImpl extends EntityServiceImpl<RecommendApp, RecommendAppDao> implements RecommendAppManager {
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(RecommendApp.STATUS99, idList);
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
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void modifySortNo(Long id, Integer sortNo) {
		if (sortNo == null) {
			sortNo = Integer.MAX_VALUE;
		}
		getEntityDao().modifySortNo(id, sortNo);
	}

	@Override
	public PageInfo<RecommendAppDto> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	public ScrollPage<RecommendAppDto> queryDtoForPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions) {
		return getEntityDao().queryDtoForPage(scrollPage, conditions);
	}
	
}