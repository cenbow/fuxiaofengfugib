package com.cqliving.cloud.online.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统用户数据权限表，目前数据权限的值为app_id对应的值 JPA Dao
 * Date: 2016-05-03 15:25:12
 * @author Code Generator
 */
public interface SysUserDataDao extends EntityJpaDao<SysUserData, Long>,SysUserDataDaoCustom {

	@Query(value="from SysUserData where userId=?1 and type=?2")
	public List<SysUserData> findByUserId(Long userId,byte type);
	
	@Modifying
	@Query(value="delete from SysUserData where userId=?1 and type=?2")
	public int deleteData(Long userId,byte type);
}
