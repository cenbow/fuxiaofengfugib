package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.wz.dao.WzCollectInfoDao;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.manager.WzCollectInfoManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzCollectInfoManager")
public class WzCollectInfoManagerImpl extends EntityServiceImpl<WzCollectInfo, WzCollectInfoDao> implements WzCollectInfoManager {

    @Override
    public List<WzCollectInfo> getByAppAuthority(Long appId, Long appAuthorityId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_appAuthorityId", appAuthorityId);
        map.put("EQ_appId", appId);
        
        return getEntityDao().query(map, null);
    }

	@Override
	public List<WzCollectInfoData> getUserCollectInfo(Long appId, Long questionId) {
		return this.getEntityDao().getUserCollectInfo(appId, questionId);
	}
}
