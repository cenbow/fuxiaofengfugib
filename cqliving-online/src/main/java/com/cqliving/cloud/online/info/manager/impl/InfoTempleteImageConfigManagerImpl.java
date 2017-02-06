package com.cqliving.cloud.online.info.manager.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoTempleteImageConfigDao;
import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.cloud.online.info.manager.InfoTempleteImageConfigManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("infoTempleteImageConfigManager")
public class InfoTempleteImageConfigManagerImpl extends EntityServiceImpl<InfoTempleteImageConfig, InfoTempleteImageConfigDao> implements InfoTempleteImageConfigManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoTempleteImageConfigManager#getByAppColumnsId(java.lang.Long)
	 */
	@Override
	public List<InfoTempleteImageConfig> getByAppColumnsId(Long appColumnsId,Long appId){
	
		if(null == appColumnsId || 0 == appColumnsId || null == appId) return null;
		
		return this.getEntityDao().getByAppColumnsId(appColumnsId,appId);
	}

    @Override
    public InfoTempleteImageConfigDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }

}
