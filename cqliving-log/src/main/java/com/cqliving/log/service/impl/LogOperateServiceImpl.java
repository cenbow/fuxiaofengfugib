package com.cqliving.log.service.impl;

import org.springframework.stereotype.Service;

import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.log.dao.LogOperateDao;
import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.service.LogOperateService;

@Service("logOperateService")
public class LogOperateServiceImpl extends EntityServiceImpl<LogOperate,LogOperateDao> implements LogOperateService {


}
