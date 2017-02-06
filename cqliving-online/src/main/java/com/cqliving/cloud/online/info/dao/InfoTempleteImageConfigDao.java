package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 模板图片配置表 JPA Dao
 * Date: 2016-04-15 09:44:58
 * @author Code Generator
 */
public interface InfoTempleteImageConfigDao extends EntityJpaDao<InfoTempleteImageConfig, Long>,InfoTempleteImageConfigDaoCustom {

	@Query(value="from InfoTempleteImageConfig where templetCode = ?1")
	public List<InfoTempleteImageConfig> findByTempleteCode(String code);
}
