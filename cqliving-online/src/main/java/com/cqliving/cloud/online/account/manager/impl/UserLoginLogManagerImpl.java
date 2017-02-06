package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserLoginLogDao;
import com.cqliving.cloud.online.account.domain.UserLoginLog;
import com.cqliving.cloud.online.account.manager.UserLoginLogManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userLoginLogManager")
public class UserLoginLogManagerImpl extends EntityServiceImpl<UserLoginLog, UserLoginLogDao> implements UserLoginLogManager {

}
