package com.cqliving.cloud.online.joke.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.JokeInfoUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.JokeInfoData;
import com.cqliving.cloud.online.joke.dao.JokeInfoDao;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.joke.dto.JokeInfoDto;
import com.cqliving.cloud.online.joke.manager.JokeInfoManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("jokeInfoManager")
public class JokeInfoManagerImpl extends EntityServiceImpl<JokeInfo, JokeInfoDao> implements JokeInfoManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private UserSessionManager userSessionManager;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(JokeInfo.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void publishBatch(List<Long> idList) {
		getEntityDao().changeStatusBatch(idList, JokeInfo.STATUS1, JokeInfo.STATUS3);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void offlineBatch(List<Long> idList) {
		getEntityDao().changeStatusBatch(idList, JokeInfo.STATUS1, JokeInfo.STATUS88);
		getEntityDao().changeStatusBatch(idList, JokeInfo.STATUS3, JokeInfo.STATUS88);
	}

	@Override
	public CommonListResult<JokeInfoData> getJokeInfo(Long appId, String sessionId, String token, Long id, Long lastId, String lastOnlineTime) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取用户信息
		Long userId = -1L;
		UserSession userSession = userSessionManager.get(sessionId, token);
		if (userSession != null) {
			userId = userSession.getUserId();
		}
		//查询数据
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("LTE_onlineTime", DateUtil.now());
		conditions.put("EQ_status", JokeInfo.STATUS3);
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_id", id);
		ScrollPage<JokeInfoDto> scrollPage = new ScrollPage<JokeInfoDto>();
		scrollPage.setPageSize(10);
		scrollPage.addScrollPageOrder(new ScrollPageOrder("online_time", ScrollPage.DESC, lastOnlineTime));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage = getEntityDao().queryForScrollPage(scrollPage, conditions, userId);
		List<JokeInfoDto> dtos = scrollPage.getPageResults();
		
		//返回数据
		CommonListResult<JokeInfoData> result = new CommonListResult<JokeInfoData>();
		List<JokeInfoData> dataList = Lists.newArrayList();
		JokeInfoData data;
		//获取是否允许评论配置
		Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_5);
		if (CollectionUtils.isNotEmpty(dtos)) {
			for (JokeInfoDto dto : dtos) {
				data = new JokeInfoData();
				data.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
				data.setContent(dto.getContent());
				data.setDespiseCount(dto.getDespiseCount());
				data.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_5);
				data.setId(dto.getId());
				data.setIsPraised(dto.getIsPraised());
				data.setOnlineTime(DateUtil.format(dto.getOnlineTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
				data.setPraiseCount(dto.getPraiseCount());
				data.setReplyCount(dto.getReplyCount());
				data.setShareUrl(JokeInfoUtil.getShareUrl(dto.getId()));	
				data.setSourceType(BusinessType.SOURCE_TYPE_5);
				data.setSynopsis(dto.getContent());
				data.setTitle(appInfo.getName() + "-段子");
				data.setUrl("");
				dataList.add(data);
			}
			result.setDataList(dataList);
		}
		return result;
	}
	
}