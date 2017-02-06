package com.cqliving.cloud.online.act.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.act.dao.ActCollectOptionDao;
import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.cloud.online.act.manager.ActCollectOptionManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("actCollectOptionManager")
public class ActCollectOptionManagerImpl extends EntityServiceImpl<ActCollectOption, ActCollectOptionDao> implements ActCollectOptionManager {
}
