package com.org.weixin.module.dalingmusicale.manager;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.UserViewHis;

/**
 * 用户浏览历史表 Manager
 * Date: 2016-09-16 09:10:05
 * @author Code Generator
 */
public interface UserViewHisManager extends EntityService<UserViewHis> {
	
	public UserViewHis saveUserViewHis(String url,String name,Long userId,Byte viewType);
	
	public List<UserViewHis> findByViewType(Byte viewType);
}
