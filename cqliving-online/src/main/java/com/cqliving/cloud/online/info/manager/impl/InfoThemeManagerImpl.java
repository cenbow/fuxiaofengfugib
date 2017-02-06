package com.cqliving.cloud.online.info.manager.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoThemeDao;
import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.cloud.online.info.manager.InfoThemeManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("infoThemeManager")
public class InfoThemeManagerImpl extends EntityServiceImpl<InfoTheme, InfoThemeDao> implements InfoThemeManager {

	@Override
	public List<InfoTheme> getByApp(Long appId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		return query(map, sortMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoThemeManager#findByInfoClassifyId(java.lang.Long)
	 */
	@Override
	public List<InfoTheme> findByInfoClassifyId(Long infoClassifyId) {
		
		return this.getEntityDao().findByInfoClassifyId(infoClassifyId);
	}

}