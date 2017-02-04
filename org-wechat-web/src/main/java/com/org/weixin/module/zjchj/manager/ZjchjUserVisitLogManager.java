package com.org.weixin.module.zjchj.manager;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.ZjchjUserVisitLog;

/**
 * 用户访问日志表 Manager
 * Date: 2016-09-27 17:37:31
 * @author Code Generator
 */
public interface ZjchjUserVisitLogManager extends EntityService<ZjchjUserVisitLog> {

	/**
	 * <p>Description: 访问总量</p>
	 * @author Tangtao on 2016年9月27日
	 * @return
	 */
	Long getTotalCount();

	/**
	 * <p>Description: 访问总人数</p>
	 * @author Tangtao on 2016年9月27日
	 * @return
	 */
	Long getTotalPeople();
	
}