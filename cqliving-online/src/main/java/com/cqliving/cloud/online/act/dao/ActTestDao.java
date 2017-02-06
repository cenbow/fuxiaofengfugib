package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动答题表 JPA Dao
 * Date: 2016-06-07 09:22:07
 * @author Code Generator
 */
public interface ActTestDao extends EntityJpaDao<ActTest, Long>, ActTestDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActTest set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
}
