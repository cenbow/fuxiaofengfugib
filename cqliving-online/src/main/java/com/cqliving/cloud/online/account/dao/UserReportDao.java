package com.cqliving.cloud.online.account.dao;

import java.util.Date;
import java.util.List;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.account.domain.UserReport;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户举报表 JPA Dao
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserReportDao extends EntityJpaDao<UserReport, Long>,UserReportDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update UserReport set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 审核
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update UserReport set status = ?1,auditingContent=?2,auditingId=?3,auditingtor=?4,auditingTime=?5,auditingType=?6 where id in ?7")
    public int auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Date date,Byte auditingType,List<Long> ids);
}