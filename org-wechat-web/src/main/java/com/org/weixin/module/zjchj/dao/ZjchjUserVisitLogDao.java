package com.org.weixin.module.zjchj.dao;


import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.zjchj.domain.ZjchjUserVisitLog;

/**
 * 用户访问日志表 JPA Dao
 * Date: 2016-09-27 17:37:31
 * @author Code Generator
 */
public interface ZjchjUserVisitLogDao extends EntityJpaDao<ZjchjUserVisitLog, Long> {

	/**
	 * <p>Description: 访问总量</p>
	 * @author Tangtao on 2016年9月27日
	 * @return
	 */
	@Query("select count(id) from ZjchjUserVisitLog")
	Long getTotalCount();

	/**
	 * <p>Description: 访问用户量</p>
	 * @author Tangtao on 2016年9月27日
	 * @return
	 */
	@Query("select count(distinct userId) from ZjchjUserVisitLog")
	Long getTotalPeople();
	
}