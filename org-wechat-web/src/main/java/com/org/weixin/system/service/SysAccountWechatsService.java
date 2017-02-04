package com.org.weixin.system.service;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.system.domain.SysAccountWechats;

/**
 * sys_account_wechats Service
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysAccountWechatsService extends EntityService<SysAccountWechats> {

	public List<SysAccountWechats> queryAccountList();
	
	public String queryTokenById(Long id);

}
