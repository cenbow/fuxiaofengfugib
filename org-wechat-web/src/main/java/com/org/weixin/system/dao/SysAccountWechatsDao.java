package com.org.weixin.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.system.domain.SysAccountWechats;

/**
 * sys_account_wechats JPA Dao
 *
 * Date: 2015-07-23 20:46:52
 *
 * @author Acooly Code Generator
 *
 */
public interface SysAccountWechatsDao extends EntityJpaDao<SysAccountWechats, Long> {
	
	@Query("FROM SysAccountWechats WHERE status = 1")
	public List<SysAccountWechats> queryAccountList();
	
	@Query("SELECT token FROM SysAccountWechats WHERE id = ?1 AND status = 1")
	public String queryTokenById(Long id);
	
}
