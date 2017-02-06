package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯来源表 JPA Dao
 * Date: 2016-04-15 09:44:51
 * @author Code Generator
 */
public interface InfoSourceDao extends EntityJpaDao<InfoSource, Long>,InfoSourceDaoCustom {

	@Query(value="from InfoSource where appId=?1 and name=?2")
	public List<InfoSource> findByNameAndAppId(Long appId,String source);
	@Modifying
	@Query(value="update InfoSource set status = ?1 where id in ?2")
	public void updateStatus(Byte status, List<Long> ids);
	@Modifying
	@Query(value="update InfoSource set sortNo = ?2 where id = ?1")
	public void updateSortNo(Long id, Integer sortNo);
}
