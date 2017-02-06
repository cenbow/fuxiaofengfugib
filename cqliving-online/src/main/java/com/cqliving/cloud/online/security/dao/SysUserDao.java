package com.cqliving.cloud.online.security.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统用户表 JPA Dao
 *
 * Date: 2015-05-11 16:11:20
 *
 * @author Code Generator
 *
 */
public interface SysUserDao extends EntityJpaDao<SysUser, Long> {

	public SysUser findByUsername(String username);
	
    @Modifying
    @Query(value = "update SysUser t set t.status=?2 where id=?1")
    public int updateStatusById(Long id, Integer status);

    @Modifying
    @Query(value = "update SysUser t set t.password=?2, t.salt=?3 where id=?1")
    public int updateUserpassById(Long id, String userpass, String salt);
    
    @Modifying
    @Query(value = "update SysUser t set t.lastLoginDate=?2, t.lastLoginIp=?3 where id=?1")
    public int updateUserLoginById(Long id, Date lastLoginDate, String lastLoginIp);

	/**
	 * <p>Description: 获取客户端后台用户</p>
	 * @author Tangtao on 2016年5月10日
	 * @param appIdList
	 * @return
	 */
    @Query(value = "from SysUser where appId in ?1 order by id desc")
	List<SysUser> getByAppIds(List<Long> appIdList);
    
    
}
