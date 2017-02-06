package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.wz.dao.WzUserDao;
import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.cloud.online.wz.manager.WzUserManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("wzUserManager")
public class WzUserManagerImpl extends EntityServiceImpl<WzUser, WzUserDao> implements WzUserManager {

    @Override
    public List<WzUser> getByAppId(Long appId) {
        return this.getEntityDao().getByAppId(appId);
    }
}
