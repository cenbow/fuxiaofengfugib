package com.org.weixin.module.szc.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.szc.domain.SzcUserAward;

/**
 * 奖品领取历史表 JPA Dao
 * Date: 2016-09-02 11:24:23
 * @author Code Generator
 */
public interface SzcUserAwardDao extends EntityJpaDao<SzcUserAward, Long>,SzcUserAwardDaoCustom {
	
	/**
	 * Title:
	 * <p>Description:根据当前抽奖的时间段查找用户抽奖记录,用于判断是否可以继续抽奖(用户在一个抽奖时间段只能抽奖一次)</p>
	 * @author fuxiaofeng on 2016年9月4日
	 * @param awardBeginTime
	 * @param awardEndTime
	 * @param phone
	 * @return
	 */
	@Query(value="from SzcUserAward where awardTime<=?1 and phone = ?2 and district=?3")
	public List<SzcUserAward> findByAwardTime(Date awardEndTime,String phone,Integer district);
	
	@Query(value="select count(s) from SzcUserAward s where awardCode=?1 and verifyTime>=?2 and verifyTime<=?3")
	public long findVerifyAll(String awardCode, Date beginTime, Date endTime);
	
	@Modifying
	@Query(value="update SzcUserAward set takeStatus = 2 where convertCode=?1")
	public int updateTakeStatus(String convertCode);
	
	public List<SzcUserAward> findByConvertCode(String convertCode);
	
	//查找指定时间的中奖数量
	@Query(value="select count(s) from SzcUserAward s where awardTime>=?1 and awardTime<=?2 and takeStatus in(1,2) and district=?3")
	public long findAwardNumDaily(Date date,Date date2,Integer district);
	//查找指定时间的核销数量
	@Query(value="select count(s) from SzcUserAward s where verifyTime>=?1 and verifyTime<=?2 and takeStatus = 2 and district=?3")
	public long findVerifyNumDaily(Date date,Date date2,Integer district);
	//查找该区域所有中奖数量
	@Query(value="select count(s) from SzcUserAward s where takeStatus in(1,2) and district=?1")
	public long findAawardNum(Integer district);
	//查找该区域所有核销数量
	@Query(value="select count(s) from SzcUserAward s where takeStatus = 2 and district=?1")
	public long findVerifyNum(Integer district);

}
