package com.cqliving.cloud.online.info.manager.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.info.dao.InfoSourceDao;
import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.cloud.online.info.manager.InfoSourceManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Maps;

@Service("infoSourceManager")
public class InfoSourceManagerImpl extends EntityServiceImpl<InfoSource, InfoSourceDao> implements InfoSourceManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoSourceManager#findBySource(java.lang.String)
	 */
	@Override
	public List<InfoSource> findByConditions(Map<String,Object> conditions) {
		
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo",true);	
        sortMap.put("updateTime",false);
        sortMap.put("id", false);
		return this.query(conditions,sortMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoSourceManager#saveInfoSource(java.lang.String)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public InfoSource saveInfoSource(Information information,String sourceName) {
		
		if(null == information || StringUtil.isEmpty(sourceName))return null;
		List<InfoSource> list = this.getEntityDao().findByNameAndAppId(information.getAppId(),sourceName);
		if(CollectionUtils.isNotEmpty(list))return list.get(0);
		
		InfoSource infoSource = new InfoSource();
		infoSource.setAppId(information.getAppId());
		infoSource.setCreateTime(information.getUpdateTime());
		infoSource.setCreator(information.getUpdator());
		infoSource.setCreatorId(information.getUpdatorId());
		infoSource.setUpdateTime(information.getUpdateTime());
		infoSource.setUpdator(information.getUpdator());
		infoSource.setUpdatorId(information.getUpdatorId());
		infoSource.setStatus(InfoSource.status3);
		infoSource.setName(sourceName);
		infoSource.setSortNo(Integer.MAX_VALUE);
		return this.getEntityDao().saveAndFlush(infoSource);
		
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoSourceManager#updateStatus(java.lang.Byte, java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void updateStatus(Byte status, Long... id) {
		
		if(null == id || null == status)return;
		this.getEntityDao().updateStatus(status,Arrays.asList(id));
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoSourceManager#updateSortNo(java.lang.Long, java.lang.Integer)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void updateSortNo(Long id, Integer sortNo) {
		
		if(null == id || null == sortNo)return;
		
		this.getEntityDao().updateSortNo(id, sortNo);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoSourceManager#queryForPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	public PageInfo<InfoSourceDto> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map) {
		
		return this.getEntityDao().queryForPage(pageInfo, map);
	}
}
