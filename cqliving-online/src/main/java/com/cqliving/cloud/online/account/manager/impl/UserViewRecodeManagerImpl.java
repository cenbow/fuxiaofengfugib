package com.cqliving.cloud.online.account.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserViewRecodeDao;
import com.cqliving.cloud.online.account.domain.UserViewRecode;
import com.cqliving.cloud.online.account.manager.UserViewRecodeManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userViewRecodeManager")
public class UserViewRecodeManagerImpl extends EntityServiceImpl<UserViewRecode, UserViewRecodeDao> implements UserViewRecodeManager {
}
