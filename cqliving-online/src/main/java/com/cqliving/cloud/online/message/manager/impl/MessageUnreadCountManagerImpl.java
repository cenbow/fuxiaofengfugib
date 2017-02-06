package com.cqliving.cloud.online.message.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.message.dao.MessageUnreadCountDao;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.cloud.online.message.manager.MessageUnreadCountManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("messageUnreadCountManager")
public class MessageUnreadCountManagerImpl extends EntityServiceImpl<MessageUnreadCount, MessageUnreadCountDao> implements MessageUnreadCountManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private UserSessionManager userSessionManager;

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void clear(Long appId, String sessionId, String token, Byte type) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession != null) {
			getEntityDao().clear(appId, userSession.getUserId(), type, DateUtil.now());
		}
	}

	@Override
	public Integer getCount(Long appId, String sessionId, String token, Byte type) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			return 0;
		}
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_userId", userSession.getUserId());
		map.put("EQ_type", type);
		List<MessageUnreadCount> coll = query(map, null);
		return CollectionUtils.isNotEmpty(coll) ? coll.get(0).getNumber() : 0;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void increaceUnreadCount(Long appId, Long userId, Byte type) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		//查询用户此类型记录是否存在
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_userId", userId);
		map.put("EQ_type", type);
		List<MessageUnreadCount> coll = query(map, null);
		Date now = DateUtil.now();
		MessageUnreadCount obj;
		if (CollectionUtils.isEmpty(coll)) {	//不存在记录，新增
			obj = new MessageUnreadCount();
			obj.setAppId(appId);
			obj.setNumber(1);
			obj.setCreateTime(now);
			obj.setType(type);
			obj.setUserId(userId);
		} else {	//存在记录，递增count
			obj = coll.get(0);
			obj.setNumber(obj.getNumber() + 1);
		}
		obj.setUpdateTime(now);
		save(obj);
	}
	
}