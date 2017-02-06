package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
/**
 * 中国建行银行悦生活服务接口 JPA Dao
 * Date: 2016-06-16 17:48:17
 * @author Code Generator
 */
public interface ConfigLifeDao extends EntityJpaDao<ConfigLife, Long> {
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月20日
	 * @param regionCode
	 * @return
	 */
	@Query("from ConfigLife where type = ?1")
	List<ConfigLife> getByType(Integer type);
}
