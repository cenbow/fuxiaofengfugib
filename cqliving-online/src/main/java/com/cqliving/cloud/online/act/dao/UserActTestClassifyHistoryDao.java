package com.cqliving.cloud.online.act.dao;


import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.act.domain.UserActTestClassifyHistory;

/**
 * 用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。 JPA Dao
 * Date: 2016-06-22 18:02:08
 * @author Code Generator
 */
public interface UserActTestClassifyHistoryDao extends EntityJpaDao<UserActTestClassifyHistory, Long> {
}
