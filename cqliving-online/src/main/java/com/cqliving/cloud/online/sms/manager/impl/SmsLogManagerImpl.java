package com.cqliving.cloud.online.sms.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.sms.dao.SmsLogDao;
import com.cqliving.cloud.online.sms.domain.SmsLog;
import com.cqliving.cloud.online.sms.manager.SmsLogManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("smsLogManager")
public class SmsLogManagerImpl extends EntityServiceImpl<SmsLog, SmsLogDao> implements SmsLogManager {

}
