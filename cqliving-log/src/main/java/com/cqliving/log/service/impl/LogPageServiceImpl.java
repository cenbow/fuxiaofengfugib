package com.cqliving.log.service.impl;

import org.springframework.stereotype.Service;

import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.log.dao.LogPageDao;
import com.cqliving.log.domain.LogPage;
import com.cqliving.log.service.LogPageService;

@Service("logPageService")
public class LogPageServiceImpl extends EntityServiceImpl<LogPage,LogPageDao> implements LogPageService {


}
