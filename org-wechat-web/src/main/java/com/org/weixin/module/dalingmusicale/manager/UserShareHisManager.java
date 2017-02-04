package com.org.weixin.module.dalingmusicale.manager;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.dalingmusicale.domain.UserShareHis;

/**
 * 用户分享历史表 Manager
 * Date: 2016-09-16 09:09:57
 * @author Code Generator
 */
public interface UserShareHisManager extends EntityService<UserShareHis> {
	
	//查找当天的分享次数
	public List<UserShareHis> findDaily(Long userId,Byte shareType);
}
