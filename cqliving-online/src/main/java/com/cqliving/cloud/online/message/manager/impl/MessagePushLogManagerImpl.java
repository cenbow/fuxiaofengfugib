package com.cqliving.cloud.online.message.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.message.dao.MessagePushLogDao;
import com.cqliving.cloud.online.message.domain.MessagePushLog;
import com.cqliving.cloud.online.message.manager.MessagePushLogManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("messagePushLogManager")
public class MessagePushLogManagerImpl extends EntityServiceImpl<MessagePushLog, MessagePushLogDao> implements MessagePushLogManager {
}
