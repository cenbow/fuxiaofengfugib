package com.cqliving.cloud.online.account.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.account.domain.UserInfoReply;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 用户资讯回复表 JPA Dao
 * Date: 2016-05-11 21:29:30
 * @author Code Generator
 */
public interface UserInfoReplyDao extends EntityJpaDao<UserInfoReply, Long>, UserInfoReplyDaoCustom {
    
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update UserInfoReply set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * 审核
	 * @author Code Generator
	 * @param ids
	 * @return
	 */
	@Modifying
	@Query("update UserInfoReply set status = ?1,auditingContent=?2,auditingId=?3,auditingtor=?4,auditingTime=?5 where id in ?6")
	public int auditing(Byte status,String auditingContent,Long auditingId,String auditingtor,Date date,List<Long> ids);

	/**
	 * Title:获得评论总条数
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月26日
	 * @param appId
	 * @param id
	 * @param sourceType
	 * @param status
	 * @return
	 */
	@Query("select count(id) as cnt from UserInfoReply where appId=?1 and sourceId=?2 and sourceType=?3 and status=?4")
    public Long getTotalCount(Long appId, Long id, Byte sourceType, Byte status);
	
	@Modifying
	@Query(value="update UserInfoReply set praise = ifnull(praise,0) + 1 where id = ?1")
	public int addPraise(Long id);

	/**
	 * <p>Description: 递增评论数</p>
	 * @author Tangtao on 2016年6月7日
	 * @param replyId
	 */
	@Modifying
	@Query("update UserInfoReply set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	int increaseReplyCount(Long replyId);
	
	/**
	 * <p>Description: 递减评论数</p>
	 * @author Tangtao on 2016年6月7日
	 * @param replyId
	 * @return 
	 */
	@Modifying
	@Query("update UserInfoReply set replyCount = ifnull(replyCount, 1) - 1 where id = ?1")
	int decreaseReplyCount(Long replyId);
	
}