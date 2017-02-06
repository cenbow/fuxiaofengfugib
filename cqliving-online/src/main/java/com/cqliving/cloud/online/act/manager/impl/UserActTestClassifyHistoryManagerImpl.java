package com.cqliving.cloud.online.act.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.UserActTestClassifyHistoryDao;
import com.cqliving.cloud.online.act.domain.UserActTestClassifyHistory;
import com.cqliving.cloud.online.act.manager.UserActTestClassifyHistoryManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userActTestClassifyHistoryManager")
public class UserActTestClassifyHistoryManagerImpl extends EntityServiceImpl<UserActTestClassifyHistory, UserActTestClassifyHistoryDao> implements UserActTestClassifyHistoryManager {
}
