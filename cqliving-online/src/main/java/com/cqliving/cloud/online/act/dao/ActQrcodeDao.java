package com.cqliving.cloud.online.act.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.act.domain.ActQrcode;

/**
 * 二维码扫描活动 JPA Dao
 * Date: 2016-12-16 15:15:52
 * @author Code Generator
 */
public interface ActQrcodeDao extends EntityJpaDao<ActQrcode, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActQrcode set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Query("from ActQrcode where status = 3 and code=?1 ")
	public List<ActQrcode> findByCode(String code);
}
