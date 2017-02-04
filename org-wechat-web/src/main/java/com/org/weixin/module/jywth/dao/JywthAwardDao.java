package com.org.weixin.module.jywth.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.jywth.domain.JywthAward;

/**
 * jywth_揭阳万泰汇奖品表 JPA Dao
 *
 * Date: 2016-04-02 13:11:45
 *
 * @author Acooly Code Generator
 *
 */
public interface JywthAwardDao extends EntityJpaDao<JywthAward, Long> {

	@Query(value="select sum(num) from jywth_award where date(send_begin_time) >= date(?1) and date(send_end_time) <= date(?2) and num >=1 ",nativeQuery = true)
	public Integer getTotalDuration(String begin,String end);
	
	@Query(value="select * from jywth_award where date(send_begin_time) >= date(?1) and date(send_end_time) <= date(?2) and num >=1 ",nativeQuery=true)
	public List<JywthAward> getListDuration(String begin,String end);

	@Modifying
	@Query(value="update JywthAward set num = num-?2 where code=?1 and num>=1")
	public int subtractNum(String code,int num);
	
	@Modifying
	@Query(value="update JywthAward set num = ?2,sendBeginTime=?3,sendEndTime=?4 where code=?1")
	public int createNewNum(String code,int num,Date beginTime,Date endTime);
}
