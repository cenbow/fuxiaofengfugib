package com.org.weixin.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysConfigDao;
import com.org.weixin.system.domain.SysConfig;
import com.org.weixin.system.service.SysConfigService;

@Service("sysConfigService")
public class SysConfigServiceImpl extends EntityServiceImpl<SysConfig,SysConfigDao> implements SysConfigService {

	@Override
	public List<SysConfig> queryConfigList() {
		// TODO Auto-generated method stub
		return this.getEntityDao().queryConfigList();
	}

}
