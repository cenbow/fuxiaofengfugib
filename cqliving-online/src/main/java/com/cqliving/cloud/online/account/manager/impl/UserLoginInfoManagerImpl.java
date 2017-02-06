package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserLoginInfoDao;
import com.cqliving.cloud.online.account.domain.UserLoginInfo;
import com.cqliving.cloud.online.account.manager.UserLoginInfoManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userLoginInfoManager")
public class UserLoginInfoManagerImpl extends EntityServiceImpl<UserLoginInfo, UserLoginInfoDao> implements UserLoginInfoManager {

}
