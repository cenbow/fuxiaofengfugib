package com.cqliving.cloud.online.account.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 分享历史纪录表 JPA Dao
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserShareHistoryDao extends EntityJpaDao<UserShareHistory, Long> {
	
	@Query(value="select count(u) from UserShareHistory u where userId = ?1 and appId=?2 and sourceId=?3")
	public long findTotalByUserIdSourceId(Long userId,Long appId,Long sourceId);
	
	@Query(value="select count(u) from UserShareHistory u where userId = ?1 and appId=?2 and sourceId=?3 and createTime >= ?4 and createTime <=?5")
	public long findTotalByUserIdToday(Long userId,Long appId,Long sourceId,Date dayBegin,Date dayEnd);
	
}
