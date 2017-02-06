package com.cqliving.cloud.online.account.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * user_用户点赞表 JPA Dao
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserPraiseDao extends EntityJpaDao<UserPraise, Long>, UserPraiseDaoCustom {

	@Query(value="from UserPraise where sourceUserId=?1 and sourceType=?2 and sourceId=?3")
	public List<UserPraise> findByUserId(Long userId,Byte sourceType,Long sourceId);
	
	@Query(value="from UserPraise where userId=?1 and sourceType=?2 and sourceId=?3 and sourceUserId=?4")
	public List<UserPraise> findByUserId(Long userId,Byte sourceType,Long sourceId,Long sourceUserId);
}