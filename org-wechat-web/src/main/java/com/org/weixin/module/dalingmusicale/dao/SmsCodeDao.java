package com.org.weixin.module.dalingmusicale.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.dalingmusicale.domain.SmsCode;

/**
 * 用户手机验证码表 JPA Dao
 * Date: 2016-09-16 09:09:44
 * @author Code Generator
 */
public interface SmsCodeDao extends EntityJpaDao<SmsCode, Long> {
	
	@Query(value="select * from sms_code where date_add(send_time,interval 1 minute) >= ?1 and phone=?2 ",nativeQuery=true)
	public List<SmsCode> findInOneMinute(Date time,String phone);
	
	@Query(value="from SmsCode where phone=?1 and smsCode=?2 and invalidTime>=?3 and used=1")
	public List<SmsCode> findByPhoneCode(String phone,String code,Date time);
	
	@Modifying
	@Query(value="update SmsCode set used=2,name=?1 where phone=?2 and smsCode=?3")
	public int updateSmsCode(String name,String phone,String smsCode);
}
