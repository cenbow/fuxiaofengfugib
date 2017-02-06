package com.cqliving.cloud.online.account.dao;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserLoginInfo;

/**
 * 用户APP登录信息表 JPA Dao
 * Date: 2016-04-15 09:46:12
 * @author Code Generator
 */
public interface UserLoginInfoDao extends EntityJpaDao<UserLoginInfo, Long> {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年8月24日
	 * @param appId
	 * @param userId
	 * @return
	 */
	@Query("from UserLoginInfo where appId = ?1 and userId = ?2 order by id desc")
	List<UserLoginInfo> getByAppAndUser(Long appId, Long userId);

}