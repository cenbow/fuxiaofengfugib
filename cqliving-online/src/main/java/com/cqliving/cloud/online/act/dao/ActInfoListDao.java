package com.cqliving.cloud.online.act.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 活动集合表，一个活动包含 JPA Dao
 * Date: 2016-06-07 09:21:44
 * @author Code Generator
 */
public interface ActInfoListDao extends EntityJpaDao<ActInfoList, Long>, ActInfoListDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActInfoList set status = ?1,updator=?2,updatorId=?3 where id in ?4")
    public int updateStatus(Byte status,String updator,Long updateUserId,List<Long> ids);
	
	@Modifying
	@Query(value="update ActInfoList set linkUrl = ?1,status=?2,updator=?3,updatorId=?4,showType=?5 where id = ?6")
	public int updateLinkUrlAndStatus(String url,Byte status,String updator,Long updateUserId,Byte showType,Long id);
	
	/**
	 * 通过活动id改变状态
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年6月8日下午2:50:30
	 */
	@Modifying
	@Query(value="update ActInfoList set status = ?1,updator=?2,updatorId=?3,updateTime=?4 where actInfoId in ?5")
	public void updateStatusByActInfoId(Byte status,String updator,Long updateUserId,Date updateTime,List<Long> ids);
}
