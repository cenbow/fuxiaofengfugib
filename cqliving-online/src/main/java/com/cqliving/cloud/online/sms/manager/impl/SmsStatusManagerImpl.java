package com.cqliving.cloud.online.sms.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.sms.dao.SmsStatusDao;
import com.cqliving.cloud.online.sms.domain.SmsStatus;
import com.cqliving.cloud.online.sms.manager.SmsStatusManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("smsStatusManager")
public class SmsStatusManagerImpl extends EntityServiceImpl<SmsStatus, SmsStatusDao> implements SmsStatusManager {

}
