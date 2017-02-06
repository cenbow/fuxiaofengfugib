package com.cqliving.cloud.online.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.security.domain.SysResType;

/**
 * 系统资源分类表 JPA Dao
 * Date: 2016-06-29 17:07:53
 * @author Code Generator
 */
public interface SysResTypeDao extends EntityJpaDao<SysResType, Long>,SysResTypeDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update SysResType set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
