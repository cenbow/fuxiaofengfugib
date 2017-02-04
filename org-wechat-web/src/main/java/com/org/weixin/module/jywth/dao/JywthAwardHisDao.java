package com.org.weixin.module.jywth.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.jywth.domain.JywthAwardHis;

/**
 * 红包领取历史表 JPA Dao
 *
 * Date: 2016-04-02 13:11:46
 *
 * @author Acooly Code Generator
 *
 */
public interface JywthAwardHisDao extends EntityJpaDao<JywthAwardHis, Long> {

	@Query(value="from JywthAwardHis where phone=?1 order by awardTime desc")
	public List<JywthAwardHis> findByPhone(String phone);
	
	@Modifying
	@Query(value="update JywthAwardHis set takeStatus = ?2 where exchangeCode=?1 ")
	public int updateStatus(String exchangeCode,byte status);
	@Modifying
	@Query(value="update JywthAwardHis set takeStatus = 2,verifyTime=?2 where exchangeCode=?1 and takeStatus=1")
	public int verifyAward(String exchangeCode,Date verifyTime);
	
	@Modifying
	@Query(value="update JywthAwardHis set takeStatus = 1,takeTime=?2 where id=?1 ")
	public int takeAward(Long id,Date takeTime);
	
	//查询当天中奖
	@Query(value="from JywthAwardHis where date(awardTime) = date(?1) order by awardTime desc")
	public List<JywthAwardHis> queryAwardByDate(Date date);
	//查询当天核销数量
	@Query(value="from JywthAwardHis where date(verifyTime) = date(?1) and takeStatus=2 order by verifyTime desc")
	public List<JywthAwardHis> queryVerifyAwardByDate(Date date);
	
	@Query(value="from JywthAwardHis where  exchangeCode=?1 order by awardTime desc")
	public List<JywthAwardHis> queryAwardByExCode(String exCode);
}
