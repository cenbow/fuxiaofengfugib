package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.app.domain.AllMedia;

/**
 * 全媒体表 JPA Dao
 * Date: 2016-11-02 14:35:32
 * @author Code Generator
 */
public interface AllMediaDao extends EntityJpaDao<AllMedia, Long>,AllMediaDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update AllMedia set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Modifying
    @Query("update AllMedia set sortNo = ?2 where id=?1")
	public void updateSortNo(Long id, Integer sortNo);
}
