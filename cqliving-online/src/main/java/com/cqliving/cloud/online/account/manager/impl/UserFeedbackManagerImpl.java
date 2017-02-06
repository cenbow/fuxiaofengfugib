package com.cqliving.cloud.online.account.manager.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserFeedbackDao;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserFeedback;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.dto.UserFeedbackDto;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserFeedbackManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.manager.ConfigTextManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FeedbackData;
import com.cqliving.cloud.online.message.domain.MessageUnreadCount;
import com.cqliving.cloud.online.message.manager.MessageUnreadCountManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;

@Service("userFeedbackManager")
public class UserFeedbackManagerImpl extends EntityServiceImpl<UserFeedback, UserFeedbackDao> implements UserFeedbackManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private ConfigTextManager configTextManager;
	@Autowired
	private UserAccountManager accountManager;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private MessageUnreadCountManager messageUnreadCountManager;

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void add(Long appId, String sessionId, String token, String title, String content, String images) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取用户 id
		UserSession userSession;
		if (StringUtils.isNotBlank(token)) {
			//获取登录用户
			userSession = userSessionManager.getByToken(token);
		} else {
			//获取游客用户
			userSession = userSessionManager.getTourist(sessionId);
		}
		Long userId;
		if (userSession == null) {	//创建游客用户
			UserAccount account = accountManager.createTourist(appId, sessionId);
			userId = account.getId();
		} else {
			userId = userSession.getUserId();
		}
		
		//获取用户信息
		UserInfo userInfo = userInfoDao.get(userId);
		//获取自动反馈内容
		ConfigText configText = configTextManager.getByAppId(appId, ConfigText.TYPE3);
		
		//保存
		boolean isAutoReply = configText != null && StringUtils.isNotBlank(configText.getContent());
		UserFeedback obj = new UserFeedback();
		obj.setAppId(appId);
		obj.setContent(content);
		obj.setCreateTime(DateUtil.now());
		obj.setImageUrl(images);
		obj.setName(userInfo.getName());
		obj.setReplyContent(isAutoReply ? configText.getContent() : "");
		obj.setSessionCode(sessionId);
		obj.setStatus(isAutoReply ? UserFeedback.STATUS3 : UserFeedback.STATUS2);
		obj.setTitle(title);
		obj.setUserId(userId);
		save(obj);
	}

	@Override
	public CommonListResult<FeedbackData> queryForScrollPage(Long appId, String sessionId, String token, Long lastId) {
		CommonListResult<FeedbackData> result = new CommonListResult<FeedbackData>();
		//获取用户信息
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession == null) {
			return result;
		}
		
		//查询数据
		ScrollPage<UserFeedback> page = new ScrollPage<UserFeedback>();
		page.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		page.setPageSize(10);
		page = getEntityDao().queryForScrollPage(page, appId, userSession.getUserId());
		List<UserFeedback> list = page.getPageResults();
		
		//返回数据
		List<FeedbackData> columns = Lists.newArrayList();
		FeedbackData data;
		if (CollectionUtils.isNotEmpty(list)) {
			for (UserFeedback obj : list) {
				data = new FeedbackData();
				data.setContent(obj.getContent());
				data.setId(obj.getId());
				data.setImageUrl(obj.getImageUrl());
				data.setReplyContent(obj.getReplyContent());
				data.setCreateTime(DateUtil.toString(obj.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD));
				data.setStatus(obj.getStatus());
				data.setTitle(obj.getTitle());
				columns.add(data);
			}
		}
		result.setDataList(columns);
		return result;
	}
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(UserFeedback.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	
	/**
     * <p>Description:分页查询反馈信息</p>
     * @author huxiaoping on 2016年5月28日
     * @param pageInfo
     * @param conditions
     * @param orders
     * @return
     */
    @Override
    public PageInfo<UserFeedbackDto> queryByPage(PageInfo<UserFeedbackDto> pageInfo, Map<String, Object> conditions,
            Map<String, Boolean> orders) {
        return this.getEntityDao().queryByPage(pageInfo, conditions, orders);
    }

    /**
     * <p>Description:通过id查询</p>
     * @author huxiaoping on 2016年5月28日
     * @param id
     * @return
     */
    @Override
    public UserFeedbackDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }
    
    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月28日下午5:13:25
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void reply(UserFeedback userFeedback){
        UserFeedback userFeedbackDb = this.get(userFeedback.getId());
        if(null!=userFeedbackDb){
            this.getEntityDao().reply(userFeedback.getId(), userFeedback.getAuditingTime(), userFeedback.getAuditingtor(), userFeedback.getAuditingId(), userFeedback.getStatus(), userFeedback.getReplyContent());
            messageUnreadCountManager.increaceUnreadCount(userFeedbackDb.getAppId(), userFeedbackDb.getUserId(), MessageUnreadCount.TYPE1);
        }
    }
}