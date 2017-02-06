package com.cqliving.cloud.online.wz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 问题转交流程表 JPA Dao
 * Date: 2016-05-10 09:49:54
 * @author Code Generator
 */
public interface WzTransferDao extends EntityJpaDao<WzTransfer, Long>, WzTransferDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update WzTransfer set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
}
