package com.cqliving.cloud.online.manuscript.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;

/**
 * 抓稿栏目配置表 JPA Dao
 * Date: 2016-11-08 16:06:24
 * @author Code Generator
 */
public interface ManuscriptColumnsDao extends EntityJpaDao<ManuscriptColumns, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ManuscriptColumns set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
