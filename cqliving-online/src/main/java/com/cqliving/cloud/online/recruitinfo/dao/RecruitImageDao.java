package com.cqliving.cloud.online.recruitinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 人才招聘图片表 JPA Dao
 * Date: 2016-10-12 10:09:13
 * @author Code Generator
 */
public interface RecruitImageDao extends EntityJpaDao<RecruitImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update RecruitImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
     * 修改状态
     * @author Code Generator
     * @param ids
     * @return
     */
    @Modifying
    @Query("update RecruitImage set status = ?1 where recruitInfoId = ?2")
    public void updateStatusByRecruitInfoId(Byte status,Long recruitInfoId);
	
	@Query("from RecruitImage where status = ?1 and recruitInfoId = ?2 order by sortNo asc")
	public List<RecruitImage> findByRecruitInfoId(Byte status,Long recruitInfoId);
}
