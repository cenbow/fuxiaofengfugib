package com.cqliving.cloud.online.config.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.config.domain.Permission;

/**
 * 前端资源权限，该表数据需要初始化好 JPA Dao
 * Date: 2016-12-20 10:12:14
 * @author Code Generator
 */
public interface PermissionDao extends EntityJpaDao<Permission, Long> ,PermissionDaoCustom{
}
