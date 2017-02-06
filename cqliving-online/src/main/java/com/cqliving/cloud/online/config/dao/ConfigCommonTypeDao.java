package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;

/**
 * config_common_type JPA Dao
 * Date: 2016-07-25 13:54:08
 * @author Code Generator
 */
public interface ConfigCommonTypeDao extends EntityJpaDao<ConfigCommonType, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ConfigCommonType set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	

	/**
	 * Title:修改排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	@Modifying
	@Query("update ConfigCommonType set sortNo = ?2 where id in ?1")
	public int updateSort(Long id, Integer sortNo);
}
