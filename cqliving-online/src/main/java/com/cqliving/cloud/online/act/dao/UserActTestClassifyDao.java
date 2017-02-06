package com.cqliving.cloud.online.act.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;

/**
 * 用户答题分类表，一个用户对应一个分类测试题只有一条记录。 JPA Dao
 * Date: 2016-06-22 18:02:02
 * @author Code Generator
 */
public interface UserActTestClassifyDao extends EntityJpaDao<UserActTestClassify, Long>, UserActTestClassifyDaoCustom {
}
