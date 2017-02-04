package com.org.weixin.system.service.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysWechatLoginLogDao;
import com.org.weixin.system.domain.SysWechatLoginLog;
import com.org.weixin.system.service.SysWechatLoginLogService;

@Service("sysWechatLoginLogService")
public class SysWechatLoginLogServiceImpl extends EntityServiceImpl<SysWechatLoginLog,SysWechatLoginLogDao> implements SysWechatLoginLogService {

}
