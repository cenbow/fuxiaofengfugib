package com.cqliving.cloud.online.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统角色表 JPA Dao
 *
 * Date: 2015-05-12 15:40:19
 *
 * @author Code Generator
 *
 */
public interface SysRoleDao extends EntityJpaDao<SysRole, Long>,SysRoleDaoCustom {

	@Query(value="from SysRole where type=2 and appId is null order by id asc")
	public List<SysRole> findManagerRole();

	public List<SysRole> findByAppIdOrderByIdAsc(Long appId);
	
	@Query(value="from SysRole where type=1 order by id asc")
	public List<SysRole> findCommonRole();
	
	/**
     * Title:
     * <p>Description:通过appId和公共角色CODE查找角色</p>
     * @author huxiaoping on 2016年5月19日
     * @return
     */
	@Query(value="from SysRole where appId=?1 and commonRoleCode=?2 order by id asc")
    public List<SysRole> getByAppIdAndCode(Long appId, String commonRoleCode);
}
