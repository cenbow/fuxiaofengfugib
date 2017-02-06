package com.cqliving.cloud.online.account.dao;

import java.util.Date;
import java.util.List;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.account.domain.UserFeedback;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 意见反馈表 JPA Dao
 * Date: 2016-05-28 12:26:25
 * @author Code Generator
 */
public interface UserFeedbackDao extends EntityJpaDao<UserFeedback, Long>, UserFeedbackDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update UserFeedback set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Modifying
    @Query("update UserFeedback set auditingTime = ?2, auditingtor = ?3, auditingId = ?4, status = ?5, replyContent = ?6, replyTime = ?2 where id = ?1")
	public void reply(Long id,Date auditingTime,String auditingtor,Long auditingId,Byte status,String replyContent);
}
