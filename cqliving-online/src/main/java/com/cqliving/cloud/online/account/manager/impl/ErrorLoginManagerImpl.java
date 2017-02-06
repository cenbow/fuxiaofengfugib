package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.ErrorLoginDao;
import com.cqliving.cloud.online.account.domain.ErrorLogin;
import com.cqliving.cloud.online.account.manager.ErrorLoginManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("errorLoginManager")
public class ErrorLoginManagerImpl extends EntityServiceImpl<ErrorLogin, ErrorLoginDao> implements ErrorLoginManager {

}
