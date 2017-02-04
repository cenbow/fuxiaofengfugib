package com.org.weixin.system.service.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysLogDao;
import com.org.weixin.system.domain.SysLog;
import com.org.weixin.system.service.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl extends EntityServiceImpl<SysLog,SysLogDao> implements SysLogService {

}
