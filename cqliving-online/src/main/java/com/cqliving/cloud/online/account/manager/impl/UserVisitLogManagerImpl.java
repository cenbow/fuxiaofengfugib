package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserVisitLogDao;
import com.cqliving.cloud.online.account.domain.UserVisitLog;
import com.cqliving.cloud.online.account.manager.UserVisitLogManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userVisitLogManager")
public class UserVisitLogManagerImpl extends EntityServiceImpl<UserVisitLog, UserVisitLogDao> implements UserVisitLogManager {

}
