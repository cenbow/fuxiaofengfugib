package com.org.weixin.system.service.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysRuleKeywordDao;
import com.org.weixin.system.domain.SysRuleKeyword;
import com.org.weixin.system.service.SysRuleKeywordService;

@Service("sysRuleKeywordService")
public class SysRuleKeywordServiceImpl extends EntityServiceImpl<SysRuleKeyword,SysRuleKeywordDao> implements SysRuleKeywordService {

}
