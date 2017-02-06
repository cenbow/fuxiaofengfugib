package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.config.domain.ConfigConvenience;

/**
 * config_便民表 JPA Dao
 * Date: 2016-07-12 09:33:56
 * @author Code Generator
 */
public interface ConfigConvenienceDao extends EntityJpaDao<ConfigConvenience, Long>, ConfigConvenienceDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ConfigConvenience set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * Title:修改排序号
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	@Modifying
    @Query("update ConfigConvenience set sortNo = ?2 where id = ?1")
	public int updateSort(Long id, Integer sortNo);
}
