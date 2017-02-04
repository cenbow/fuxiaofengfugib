package com.org.weixin.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysModulesDao;
import com.org.weixin.system.domain.SysModules;
import com.org.weixin.system.service.SysModulesService;

@Service("sysModulesService")
public class SysModulesServiceImpl extends EntityServiceImpl<SysModules,SysModulesDao> implements SysModulesService {

	@Override
	public List<SysModules> queryModules() {
		// TODO Auto-generated method stub
		return this.getEntityDao().queryModules();
	}

}
