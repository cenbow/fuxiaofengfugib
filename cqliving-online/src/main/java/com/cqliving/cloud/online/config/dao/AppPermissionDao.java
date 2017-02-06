package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.AppPermission;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端资源权限表 JPA Dao
 * Date: 2016-12-14 16:50:46
 * @author Code Generator
 */
public interface AppPermissionDao extends EntityJpaDao<AppPermission, Long>, AppPermissionDaoCustom {
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update AppPermission set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * 删除
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("delete from AppPermission where appId = ?1")
	public void delByAppId(Long appId);

}