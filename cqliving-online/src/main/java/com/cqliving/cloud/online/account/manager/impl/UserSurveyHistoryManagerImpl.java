package com.cqliving.cloud.online.account.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserSurveyHistoryDao;
import com.cqliving.cloud.online.account.domain.UserSurveyHistory;
import com.cqliving.cloud.online.account.manager.UserSurveyHistoryManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("userSurveyHistoryManager")
public class UserSurveyHistoryManagerImpl extends EntityServiceImpl<UserSurveyHistory, UserSurveyHistoryDao> implements UserSurveyHistoryManager {

}
