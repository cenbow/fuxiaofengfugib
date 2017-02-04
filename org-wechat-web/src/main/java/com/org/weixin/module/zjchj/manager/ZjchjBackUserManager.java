package com.org.weixin.module.zjchj.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.ZjchjBackUser;

/**
 * 用户访问日志表 Manager
 * Date: 2016-09-27 19:30:29
 * @author Code Generator
 */
public interface ZjchjBackUserManager extends EntityService<ZjchjBackUser> {

	/**
	 * <p>Description:登录</p>
	 * @author Tangtao on 2016年9月27日
	 * @param userName
	 * @param pwd
	 * @return
	 */
	ZjchjBackUser login(String userName, String pwd);
	
}