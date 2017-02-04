package com.org.weixin.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.system.dao.SysAccountWechatsDao;
import com.org.weixin.system.domain.SysAccountWechats;
import com.org.weixin.system.service.SysAccountWechatsService;

@Service("sysAccountWechatsService")
public class SysAccountWechatsServiceImpl extends EntityServiceImpl<SysAccountWechats,SysAccountWechatsDao> implements SysAccountWechatsService {

	@Override
	public List<SysAccountWechats> queryAccountList() {
		return this.getEntityDao().queryAccountList();
	}

	@Override
	public String queryTokenById(Long id) {
		// TODO Auto-generated method stub
		return this.getEntityDao().queryTokenById(id);
	}

}
