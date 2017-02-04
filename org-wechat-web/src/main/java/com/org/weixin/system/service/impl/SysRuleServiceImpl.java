package com.org.weixin.system.service.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysRuleDao;
import com.org.weixin.system.domain.SysRule;
import com.org.weixin.system.service.SysRuleService;

@Service("sysRuleService")
public class SysRuleServiceImpl extends EntityServiceImpl<SysRule,SysRuleDao> implements SysRuleService {

}
