package com.org.weixin.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.system.domain.SysModules;

/**
 * sys_modules JPA Dao
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysModulesDao extends EntityJpaDao<SysModules, Long> {

	@Query("FROM SysModules")
	public List<SysModules> queryModules();
	
}
