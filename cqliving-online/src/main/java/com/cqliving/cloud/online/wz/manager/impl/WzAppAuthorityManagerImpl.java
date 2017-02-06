package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.wz.dao.WzAppAuthorityDao;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.manager.WzAppAuthorityManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzAppAuthorityManager")
public class WzAppAuthorityManagerImpl extends EntityServiceImpl<WzAppAuthority, WzAppAuthorityDao> implements WzAppAuthorityManager {

    @Override
    public WzAppAuthority getByAuthorityId(Long appId, Long id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_appId", appId);
        map.put("EQ_authorityId", id);
        List<WzAppAuthority> list = getEntityDao().query(map, null);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
