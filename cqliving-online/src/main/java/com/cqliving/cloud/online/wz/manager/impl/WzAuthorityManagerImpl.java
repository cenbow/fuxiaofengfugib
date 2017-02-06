package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.wz.dao.WzAuthorityDao;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.manager.WzAuthorityManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzAuthorityManager")
public class WzAuthorityManagerImpl extends EntityServiceImpl<WzAuthority, WzAuthorityDao> implements WzAuthorityManager {

    @Override
    public WzAuthority getByNameOne(String name) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_name", name);
        List<WzAuthority> list = getEntityDao().query(map, null);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
