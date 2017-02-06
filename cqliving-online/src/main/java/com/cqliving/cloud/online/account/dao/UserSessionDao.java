package com.cqliving.cloud.online.account.dao;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserSession;

/**
 * 用户APP当前登录信息表 JPA Dao
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserSessionDao extends EntityJpaDao<UserSession, Long> {

	/**
	 * <p>Description: 获取游客</p>
	 * @author Tangtao on 2016年5月19日
	 * @param sessionId
	 * @return
	 */
	//add by yuwu 20161224,当sessionCode为空字符串时，会查询出16万条记录
	//@Query("from UserSession where sessionCode = ?1 and token is null order by id desc")
	@Query(nativeQuery=true,value="SELECT ID, user_id, app_id, session_code, token, phone_code, create_time FROM user_session where session_code = ?1 and token is null order by id desc limit 0,1")
	List<UserSession> getTourist(String sessionId);

}
