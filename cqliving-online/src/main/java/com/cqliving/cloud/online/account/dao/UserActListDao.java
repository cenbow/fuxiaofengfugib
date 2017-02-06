package com.cqliving.cloud.online.account.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户参与活动表 JPA Dao
 * Date: 2016-06-07 09:29:35
 * @author Code Generator
 */
public interface UserActListDao extends EntityJpaDao<UserActList, Long> {
	
	@Modifying
	@Query("update UserActList set joinCount = ifnull(joinCount,0) + 1,updateTime=?2 where id = ?1")
	public int addJoinCount(Long id,Date updateTime);
	
	@Query("from UserActList where actInfoListId = ?1 and userId=?2")
	public List<UserActList> findByActInfoListAndUserId(Long actInfoList,Long userId);
	
	@Query("select count(userId) from UserActList where actInfoListId = ?1 ")
	public long findByActInfoListId(Long actInfoListId);
}
