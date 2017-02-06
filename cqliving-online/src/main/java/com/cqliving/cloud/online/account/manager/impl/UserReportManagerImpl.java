package com.cqliving.cloud.online.account.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserReportDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserReport;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.dto.UserReportDto;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserReportManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("userReportManager")
public class UserReportManagerImpl extends EntityServiceImpl<UserReport, UserReportDao> implements UserReportManager {
    
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private UserAccountManager userAccountManager;
    @Autowired
    private UserSessionManager userSessionManager;
    @Autowired
    private UserInfoDao userInfoDao;
    
	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void saveUserReport(Long appId, String sessionId, String token, String content, Long sourceId, Byte type, Byte sourceType, String reportCodes) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询用户
		Long userId = null;
		if (StringUtils.isNotBlank(token)) {
			//查询注册用户
			UserSession userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId = userSession.getUserId();
		} else {
			//查询游客用户
			UserSession userSession = userSessionManager.getTourist(sessionId);
			if (userSession == null) {
				//创建游客用户
				UserAccount userAccount = userAccountManager.createTourist(appId, sessionId);
				userId = userAccount.getId();
			} else {
				userId = userSession.getUserId();
			}
		}
		
		//判断是否已经举报过
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_sourceType", sourceType);
		map.put("EQ_sourceId", sourceId);
		map.put("EQ_sessionCode", sessionId);
		map.put("EQ_operateType", type);
		List<UserReport> coll = query(map, null);
		if (CollectionUtils.isNotEmpty(coll)) {
			throw new BusinessException(ErrorCodes.FAILURE, "你已经举报过了");
		}
		
		//保存举报数据
		UserInfo userInfo = userInfoDao.get(userId);
		UserReport obj = new UserReport();
		obj.setAppId(appId);
		obj.setAuditingType(UserReport.AUDITINGTYPE0);
		obj.setContent(content);
		obj.setCreateTime(DateUtil.now());
		obj.setName(userInfo.getName());
		obj.setReportCode(reportCodes.trim());
		obj.setSessionCode(sessionId);
		obj.setSourceId(sourceId);
		obj.setSourceType(sourceType);
		obj.setStatus(UserReport.STATUS2);
		obj.setOperateType(type);
		obj.setUserId(userId);
		getEntityDao().save(obj);
	}
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="onlineTransactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(UserReport.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="onlineTransactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	
	/**
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
    @Override
    public UserReportDto getByIdAndSourceType(Long id, Byte sourceType,Byte type) {
        return this.getEntityDao().getByIdAndSourceType(id, sourceType,type);
    }
    
    /**
     * 审核
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日下午2:54:40
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void auditing(UserReport report,Long... ids){
        List<Long> idList = Arrays.asList(ids);
        this.getEntityDao().auditing(report.getStatus(), report.getAuditingContent(), report.getAuditingId(), report.getAuditingtor(), new Date(), UserReport.AUDITINGTYPE1, idList);
    }

	@Override
	public List<UserReport> getByUserAndSourceId(Long appId, Long userId, Long sourceId, Byte sourceType) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_userId", userId);
		map.put("EQ_sourceId", sourceId);
		map.put("EQ_sourceType", sourceType);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return query(map, sortMap);
	}
}