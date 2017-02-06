package com.cqliving.cloud.online.config.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * config_热线表 JPA Dao
 * Date: 2016-07-07 11:54:13
 * @author Code Generator
 */
public interface ConfigHotlineDao extends EntityJpaDao<ConfigHotline, Long>,ConfigHotlineDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
//	@Modifying
//    @Query("update ConfigHotline set status = ?1 where id in ?2")
//    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 修改状态
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ConfigHotline set updator=?1,updatorId=?2,updateTime=?3,status = ?4 where id in ?5")
    public int updateStatus(String updator,Long updatorId,Date now,Byte status,List<Long> ids);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param updateTime
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ConfigHotline set sortNo=?1, updator=?2, updatorId=?3, updateTime=?4 where id in ?5")
    public int updateSortNo(Integer sortNo,String updator,Long updateUserId,Date updateTime,List<Long> ids);
}
