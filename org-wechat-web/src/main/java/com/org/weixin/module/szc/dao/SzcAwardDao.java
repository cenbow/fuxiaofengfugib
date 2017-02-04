package com.org.weixin.module.szc.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.szc.domain.SzcAward;

/**
 * szc_砂之船奖品表 JPA Dao
 * Date: 2016-09-02 11:24:15
 * @author Code Generator
 */
public interface SzcAwardDao extends EntityJpaDao<SzcAward, Long> {
	
	@Query(value="from SzcAward where beginTime <=?1 and endTime>= ?2 and virtualNum>=1 and district=?3")
	public List<SzcAward> getSzcAward(Date now,Date nowd,Integer district);
	
	public SzcAward findByCode(String awardCode);
	
	@Query(value="select sum(virtualNum) from SzcAward where beginTime <=?1 and endTime>= ?2 and district=?3")
	public long getAllSzcAward(Date now,Date nowd,Integer district);
	
	@Modifying
	@Query(value="update SzcAward set virtualNum = ifnull(virtualNum,0) - ?2 where code = ?1")
	public int minusVirtualNum(String awardCode,int num);
}
