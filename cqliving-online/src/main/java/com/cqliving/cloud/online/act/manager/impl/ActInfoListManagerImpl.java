package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ActivityUtil;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.dto.ActInfoListDto;
import com.cqliving.cloud.online.act.manager.ActInfoListManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("actInfoListManager")
public class ActInfoListManagerImpl extends EntityServiceImpl<ActInfoList, ActInfoListDao> implements ActInfoListManager {
	
	@Autowired
	private AppConfigManager appConfigManager;
	@Autowired
	private AppInfoDao appInfoDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids,String updator,Long updateUserId){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActInfoList.STATUS99,updator,updateUserId, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,String updator,Long updateUserId,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActInfoList.STATUS99,updator,updateUserId, idList);
	}

	@Override
	public CommonListResult<ActInfoData> getScrollPage(Long appId, String sessionId, String token, Byte rangeType, Byte showType, Long lastId, Integer lastSortNo, String lastStartTime, Byte isRecommend) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//查询数据
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_status", ActInfoList.STATUS3);
		conditions.put("EQ_appId", appId);
		conditions.put("EQ_showType", showType);
		conditions.put("EQ_isRecommend", isRecommend);
		if (rangeType != null) {
			if (rangeType.byteValue() == 0) {	//已过期
				conditions.put("LTE_actInfoEndTime", DateUtil.now());
			} else if (rangeType.byteValue() == 1) {	//未过期
				conditions.put("GT_actInfoEndTime", DateUtil.now());
			}
		}
		ScrollPage<ActInfoListDto> scrollPage = new ScrollPage<ActInfoListDto>();
		scrollPage.setPageSize(10);
		scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("start_time", ScrollPage.DESC, lastStartTime));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage = getEntityDao().queryForScrollPage(scrollPage, conditions);
		List<ActInfoListDto> dtos = scrollPage.getPageResults();
		
		Long current = System.currentTimeMillis();
		//返回数据
		CommonListResult<ActInfoData> result = new CommonListResult<ActInfoData>();
		List<ActInfoData> dataList = Lists.newArrayList();
		ActInfoData data;
		if (CollectionUtils.isNotEmpty(dtos)) {
			AppConfig appConfig = appConfigManager.findByAppId(dtos.get(0).getAppId());
			for (ActInfoListDto dto : dtos) {
				data = new ActInfoData();
				data.setDetailViewType(ClientControlType.DETAIL_VIEW_TYPE_3);
				data.setEndTime(DateUtil.format(dto.getEndTime(), "yyyy.MM.dd"));
				data.setId(dto.getId());
				data.setImageUrl(dto.getListImageUrl());
				data.setLinkUrl(dto.getLinkUrl());
				data.setOverdue(dto.getActInfoEndTime().getTime() < current);
				data.setRecommendImageUrl(dto.getRecommendImageUrl());
				data.setSortNo(dto.getSortNo());
				data.setSourceType(BusinessType.SOURCE_TYPE_6);
				data.setStartTime(DateUtil.format(dto.getStartTime(), "yyyy.MM.dd"));
				data.setStartTimestamp(DateUtil.format(dto.getStartTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				data.setSynopsis(dto.getDigest());
				data.setTitle(dto.getTitle());
				data.setShowType(dto.getShowType());
				data.setShowTypeStr(ActInfoList.allShowTypes.containsKey(dto.getShowType()) ? ActInfoList.allShowTypes.get(dto.getShowType()) : "未知");
				
				String urlParma = "?appId=" + appId + "&sessionId=" + sessionId + "&token=" + token;
				String url = ActivityUtil.getRedirectUtil(dto.getType(), dto.getLinkUrl(), dto.getId(), urlParma);
				data.setUrl(url);
				data.setShareUrl(ActivityUtil.getShareUrl(dto.getType(), dto.getLinkUrl(), dto.getId(), appConfig.getDownLoadUrl(), urlParma));
				dataList.add(data);
			}
			result.setDataList(dataList);
		}
		return result;
	}
	
}