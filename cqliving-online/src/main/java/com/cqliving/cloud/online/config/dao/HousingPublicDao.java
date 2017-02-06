package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.config.domain.HousingPublic;

/**
 * 公租房表 JPA Dao
 * Date: 2016-11-07 16:34:55
 * @author Code Generator
 */
public interface HousingPublicDao extends EntityJpaDao<HousingPublic, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update HousingPublic set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
