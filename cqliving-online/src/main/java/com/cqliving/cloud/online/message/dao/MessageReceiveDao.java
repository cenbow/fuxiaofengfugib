package com.cqliving.cloud.online.message.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 消息通知接收表 JPA Dao
 * Date: 2016-05-11 09:35:50
 * @author Code Generator
 */
public interface MessageReceiveDao extends EntityJpaDao<MessageReceive, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update MessageReceive set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
