package com.cqliving.cloud.online.info.manager.impl;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoClassifyListDao;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.manager.InfoClassifyListManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("infoClassifyListManager")
public class InfoClassifyListManagerImpl extends EntityServiceImpl<InfoClassifyList, InfoClassifyListDao> implements InfoClassifyListManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoClassifyListManager#findByInfoId(java.lang.Long)
	 */
	@Override
	public InfoClassifyList findByInfoId(Long infoId) {
		
		List<InfoClassifyList> list = this.getEntityDao().getByInfoClassify(infoId);
		
		if(CollectionUtils.isEmpty(list))return null;
		
		return list.get(0);
	}

}
