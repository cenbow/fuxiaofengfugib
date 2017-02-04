package com.org.weixin.module.dalingmusicale.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.UserShareHis;

/**
 * 用户分享历史表 JPA Dao
 * Date: 2016-09-16 09:09:57
 * @author Code Generator
 */
public interface UserShareHisDao extends EntityJpaDao<UserShareHis, Long> {
	
	@Query(value="from UserShareHis where shareTime>=?1 and shareTime<=?2 and userId=?3 and shareType=?4")
	List<UserShareHis> findDaily(Date date,Date date2,Long userId,Byte shareType);
}
