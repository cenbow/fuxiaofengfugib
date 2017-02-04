package com.org.weixin.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.system.domain.SysConfig;

/**
 * sys_config JPA Dao
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysConfigDao extends EntityJpaDao<SysConfig, Long> {

	@Query("FROM SysConfig WHERE status = 1")
	public List<SysConfig> queryConfigList();
	
}