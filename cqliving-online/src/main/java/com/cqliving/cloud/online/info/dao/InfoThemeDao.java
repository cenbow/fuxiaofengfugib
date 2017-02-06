package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯专题分类表 JPA Dao
 * Date: 2016-04-15 09:45:02
 * @author Code Generator
 */
public interface InfoThemeDao extends EntityJpaDao<InfoTheme, Long> {

	@Query(value="from InfoTheme where infoClassifyId = ?1 order by sortNo asc")
	public List<InfoTheme> findByInfoClassifyId(Long infoClassifyId);
}
