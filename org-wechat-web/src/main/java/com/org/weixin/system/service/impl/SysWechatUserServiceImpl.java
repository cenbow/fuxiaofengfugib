package com.org.weixin.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.tool.common.util.date.DateUtil;
import com.org.weixin.system.dao.SysWechatUserDao;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.service.SysWechatUserService;

@Service("sysWechatUserService")
public class SysWechatUserServiceImpl extends EntityServiceImpl<SysWechatUser,SysWechatUserDao> 
		implements SysWechatUserService {

	@Override
	public Integer getValidCellPhoneNum(Long moduleId) {
		Integer num = getEntityDao().getValidCellPhoneNum(moduleId);
		return num == null ? 0 : num;
	}

	@Override
	public SysWechatUser getUserByOpenIdAndMId(String openId, Long mId) {
		// TODO Auto-generated method stub
		return this.getEntityDao().queryUserByOpenIdAndMid(openId, mId);
	}

	@Override
	public List<SysWechatUser> getUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		return this.getEntityDao().queryUserByOpenId(openId);
	}

	@Override
	public Integer getYesterdayNewUserNum(Long moduleId) {
		Date beginTime = DateUtil.getTodayFirstTime();
		beginTime = DateUtils.addDays(beginTime, -1);	//昨天开始时间
		Date endTime = DateUtil.getTodaylastTime();
		endTime = DateUtils.addDays(endTime, -1);	//昨天结束时间
		Integer num = getEntityDao().getUserNum(moduleId, beginTime, endTime);
		return num == null ? 0 : num;
	}

	@Override
	public Integer getNewUserNum(Long moduleId, Date date) {
		Date beginTime = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(date, 0), 0), 0);
		Date endTime = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(date, 23), 59), 59);
		Integer num = getEntityDao().getUserNum(moduleId, beginTime, endTime);
		return num == null ? 0 : num;
	}

	@Override
	public List<SysWechatUser> getYesterdayNewUsers(Long moduleId) {
		Date beginTime = DateUtil.getTodayFirstTime();
		beginTime = DateUtils.addDays(beginTime, -1);	//昨天开始时间
		Date endTime = DateUtil.getTodaylastTime();
		endTime = DateUtils.addDays(endTime, -1);	//昨天结束时间
		List<SysWechatUser> coll = getEntityDao().getUsers(moduleId, beginTime, endTime);
		return coll;
	}

	@Override
	public List<SysWechatUser> getYesterdayNewPageUsers(Long moduleId) {
		Date beginTime = DateUtil.getTodayFirstTime();
		beginTime = DateUtils.addDays(beginTime, -1);	//昨天开始时间
		Date endTime = DateUtil.getTodaylastTime();
		endTime = DateUtils.addDays(endTime, -1);	//昨天结束时间
		List<SysWechatUser> coll = null;//getEntityDao().getPageUsers(moduleId, beginTime, endTime);
		return coll;
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.system.service.SysWechatUserService#updatePhone(java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional
	public void updatePhone(String phone, Long userId) {
		this.getEntityDao().updatePhone(phone, userId);
	}

	/* (non-Javadoc)
	 * @see com.org.weixin.system.service.SysWechatUserService#findByAccIdAndModuleId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<SysWechatUser> findByAccIdAndModuleId(Long accId, Long moduleId) {
		
		return this.getEntityDao().findByAccIdAndModuleId(accId, moduleId);
	}

}