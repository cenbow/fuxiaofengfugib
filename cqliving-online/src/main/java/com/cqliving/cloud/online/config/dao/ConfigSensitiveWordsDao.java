package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 敏感词表 JPA Dao
 * Date: 2016-05-07 10:02:24
 * @author Code Generator
 */
public interface ConfigSensitiveWordsDao extends EntityJpaDao<ConfigSensitiveWords, Long>,ConfigSensitiveWordsDaoCustom {
	
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ConfigSensitiveWords set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月6日
	 * @param appId
	 * @return
	 */
	@Query("from ConfigSensitiveWords where status = 3 and appId = ?1 order by id desc")
	List<ConfigSensitiveWords> getByAppId(Long appId);
	
}