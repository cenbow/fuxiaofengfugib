package com.cqliving.cloud.online.recruitinfo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 人才招聘表 JPA Dao
 * Date: 2016-10-11 14:08:19
 * @author Code Generator
 */
public interface RecruitInfoDao extends EntityJpaDao<RecruitInfo, Long>, RecruitInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update RecruitInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
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
    @Query("update RecruitInfo set sortNo=?1, updator=?2, updatorId=?3, updateTime=?4 where id in ?5")
    public int updateSortNo(Integer sortNo,String updator,Long updateUserId,Date updateTime,List<Long> ids);

}
