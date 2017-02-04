package com.org.weixin.module.zjchj.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.ZjchjUserAward;

/**
 * 用户中奖表 Manager
 * Date: 2016-09-26 15:18:47
 * @author Code Generator
 */
public interface ZjchjUserAwardManager extends EntityService<ZjchjUserAward> {

	/**
	 * <p>Description: 核销</p>
	 * @author Tangtao on 2016年9月27日
	 * @param id
	 * @param userId 
	 * @param userName
	 */
	void reward(Long id, Long userId, String userName);
	
}