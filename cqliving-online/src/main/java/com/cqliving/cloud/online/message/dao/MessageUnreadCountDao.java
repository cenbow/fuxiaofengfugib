package com.cqliving.cloud.online.message.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 消息未读数量表 JPA Dao
 * Date: 2016-06-16 13:37:29
 * @author Code Generator
 */
public interface MessageUnreadCountDao extends EntityJpaDao<MessageUnreadCount, Long> {

	/**
	 * <p>Description: 清空消息未读数</p>
	 * @author Tangtao on 2016年6月16日
	 * @param appId
	 * @param userId
	 * @param type
	 */
	@Modifying
	@Query("update MessageUnreadCount set number = 0, updateTime = ?4 where appId = ?1 and userId = ?2 and type = ?3")
	void clear(Long appId, Long userId, Byte type, Date updateTime);
	
}