package com.cqliving.cloud.online.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统资源表 JPA Dao
 *
 * Date: 2015-05-12 15:39:57
 *
 * @author Code Generator
 *
 */
public interface SysResourceDao extends EntityJpaDao<SysResource, Long>,SysResourceDaoCustom {

    @Modifying
    @Query(value = "update SysResource t set t.status=?2 where id=?1")
    public int updateStatusById(Long id, Byte status);

    @Modifying
    @Query(value = "update SysResource set sortNum=?2,parentId=?3,sysResTypeId=?4 where id=?1")
    int sort(Long id, Integer sortNum, Long parentId,Long sysResTypeId);

    @Modifying
    @Query(value = "update SysResource set sysResTypeId=?2 where id=?1")
    public int updateSysType(Long recId,Long sysTypeId);
}
