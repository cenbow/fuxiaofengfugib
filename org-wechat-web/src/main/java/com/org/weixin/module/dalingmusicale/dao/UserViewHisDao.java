package com.org.weixin.module.dalingmusicale.dao;


import java.util.List;

import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.UserViewHis;

/**
 * 用户浏览历史表 JPA Dao
 * Date: 2016-09-16 09:10:05
 * @author Code Generator
 */
public interface UserViewHisDao extends EntityJpaDao<UserViewHis, Long> {
	
	public List<UserViewHis> findByViewType(Byte viewType);
}
