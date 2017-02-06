package com.cqliving.cloud.online.account.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserActCollecInfoDao;
import com.cqliving.cloud.online.account.domain.UserActCollecInfo;
import com.cqliving.cloud.online.account.manager.UserActCollecInfoManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userActCollecInfoManager")
public class UserActCollecInfoManagerImpl extends EntityServiceImpl<UserActCollecInfo, UserActCollecInfoDao> implements UserActCollecInfoManager {
}
