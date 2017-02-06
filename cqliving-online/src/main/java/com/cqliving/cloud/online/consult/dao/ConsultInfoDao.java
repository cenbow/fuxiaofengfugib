package com.cqliving.cloud.online.consult.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.consult.domain.ConsultInfo;

/**
 * 工商联咨询表 JPA Dao
 * Date: 2016-11-29 14:58:28
 * @author Code Generator
 */
public interface ConsultInfoDao extends EntityJpaDao<ConsultInfo, Long> ,ConsultInfoDaoCustom{
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ConsultInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * 修改状态
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update ConsultInfo set replyTime = ?2,replyUserName = ?3,replyUserId = ?4,replyContent = ?5 where id = ?1")
	public void reply(Long id,Date replyTime,String replyUserName,Long replyUserId,String replyContent);
}
