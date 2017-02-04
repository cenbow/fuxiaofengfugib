package com.org.weixin.module.zjchj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.zjchj.domain.ZjchjBackUser;

/**
 * 用户访问日志表 JPA Dao
 * Date: 2016-09-27 19:30:29
 * @author Code Generator
 */
public interface ZjchjBackUserDao extends EntityJpaDao<ZjchjBackUser, Long> {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年9月27日
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@Query("from ZjchjBackUser where userName = ?1 and pwd = ?2")
	List<ZjchjBackUser> login(String userName, String pwd);
	
}