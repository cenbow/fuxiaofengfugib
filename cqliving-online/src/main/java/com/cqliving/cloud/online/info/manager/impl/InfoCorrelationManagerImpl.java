package com.cqliving.cloud.online.info.manager.impl;


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
import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InfoCorrelationDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InfoCorrelationManager;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("infoCorrelationManager")
public class InfoCorrelationManagerImpl extends EntityServiceImpl<InfoCorrelation, InfoCorrelationDao> implements InfoCorrelationManager {
	
	@Autowired
	private AppConfigManager appConfigManager;
	@Autowired
	private InfoClassifyDao infoClassifyDao;
	@Autowired
	private InformationManager informationManager;
	
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void clearSortNo(List<Long> idList) {
		getEntityDao().clearSortNo(idList);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void modifySortNo(Long id, Integer sortNo) {
		if (sortNo == null) {
			sortNo = Integer.MAX_VALUE;
		}
		getEntityDao().modifySortNo(id, sortNo);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Date updateTime, Long updatorId, String updator) {
		//设置专题的修改人及修改时间 By Tangtao 2016-12-21
		for (Long id : infoCorrelationIds) {
			InfoCorrelation correlation = get(id);
			infoClassifyDao.updateUpdate(correlation.getInfoClassifyId(), updator, updatorId, updateTime);
		}
		//移除资讯相关表关系
		getEntityDao().delete(Arrays.asList(infoCorrelationIds));
		//修改资讯专题状态	
		Map<String, Object> map;
		for (Long id : infoClasifyIds) {
			//查询新闻是否还加入了其他专题 By Tangtao 2016-11-30
			map = Maps.newHashMap();
			map.put("EQ_refInfoClassifyId", id);
			map.put("NOTIN_id", Arrays.asList(infoCorrelationIds));
			map.put("NOTNULL_themeId", "666");
			List<InfoCorrelation> list = query(map, null);
			if (CollectionUtils.isEmpty(list)) {
				infoClassifyDao.changeAddSpecialStatus(id, InfoClassify.ADDSPECIALSTATUS0);	//未加入其他专题时，修改加入专题状态
			}
		}
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Long appColumnId, Date updateTime, Long updatorId, String updator) {
		//设置专题的修改人及修改时间 By Tangtao 2016-12-21
		for (Long id : infoCorrelationIds) {
			InfoCorrelation correlation = get(id);
			infoClassifyDao.updateUpdate(correlation.getInfoClassifyId(), updator, updatorId, updateTime);
		}
		//移出资讯相关表关系
		getEntityDao().delete(Arrays.asList(infoCorrelationIds));
		//修改资讯专题状态和栏目
		List<InfoClassify> infoClassifyList = (List<InfoClassify>) infoClassifyDao.findAll(Arrays.asList(infoClasifyIds));
		Map<String, Object> map;
		for (InfoClassify infoClassify : infoClassifyList) {
			//查询新闻是否还加入了其他专题 By Tangtao 2016-11-30
			map = Maps.newHashMap();
			map.put("EQ_refInfoClassifyId", infoClassify.getId());
			map.put("NOTIN_id", Arrays.asList(infoCorrelationIds));
			map.put("NOTNULL_themeId", "666");
			List<InfoCorrelation> list = query(map, null);
			if (CollectionUtils.isEmpty(list)) {
				infoClassify.setAddSpecialStatus(InfoClassify.ADDSPECIALSTATUS0);	//未加入其他专题时，修改加入专题状态
			}
			infoClassify.setColumnsId(appColumnId);
		}
		infoClassifyDao.saves(infoClassifyList);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoCorrelationManager#joinSpecialInfo(java.lang.Long, java.lang.Long, java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void joinSpecialInfo(Long[] infoClassifyIds, Long refInfoClassId, Long[] infoThemeIds,Long[] appIds,String sessionUser,Long sessionUserId) {
        
		//同一个分类不能加入多次
		if(StringUtil.isEmpty(infoThemeIds) || StringUtil.isEmpty(infoClassifyIds) 
				|| null == refInfoClassId || StringUtil.isEmpty(appIds)){
			throw new BusinessException(ErrorCodes.FAILURE,"新闻Id及专题分类不能为空");
		}
		//专题新闻
		InfoClassify specialInfo = infoClassifyDao.get(refInfoClassId);
		if(null == specialInfo){
			return;
		}
		Date now = Dates.now();
		for(int i=0,m=infoClassifyIds.length;i<m;i++){
			Long infoClassifyId = infoClassifyIds[i];
			
			//判断新闻状态，如果状态为未发布，则不能加入专题
			//保存（待审核）,上线的才能加入专题
			InfoClassify infoClassify = infoClassifyDao.get(infoClassifyId);
			if(infoClassify.getStatus().byteValue() != InfoClassify.STATUS3.byteValue() && 
					infoClassify.getStatus().byteValue() != InfoClassify.STATUS1.byteValue()){
				continue; 
			}
			boolean isSucc = false;
			List<InfoCorrelation> list = this.getEntityDao().findByInfoClssifyId(refInfoClassId,infoClassifyId);
			for(int j=0,k=infoThemeIds.length;j<k;j++){
				if(CollectionUtils.isEmpty(list)){
					InfoCorrelation infoCorrelation = new InfoCorrelation();
					infoCorrelation.setAppId(appIds[i]);
					infoCorrelation.setInfoClassifyId(refInfoClassId);
					infoCorrelation.setRefInfoClassifyId(infoClassifyId);
					infoCorrelation.setSortNo(Integer.MAX_VALUE);
					infoCorrelation.setThemeId(infoThemeIds[j]);
					infoCorrelation.setCreateTime(now);
					infoCorrelation.setCreator(sessionUser);
					infoCorrelation.setCreatorId(sessionUserId);
					infoCorrelation.setUpdateTime(now);
					infoCorrelation.setUpdator(sessionUser);
					infoCorrelation.setUpdatorId(sessionUserId);
					infoCorrelation.setStatus(InfoCorrelation.STATUS3);
					this.getEntityDao().saveAndFlush(infoCorrelation);
					//修改该新闻为已加入专题
					infoClassifyDao.changeAddSpecialStatus(infoClassifyId, InfoClassify.ADDSPECIALSTATUS1);
					isSucc = true;
				}
			}
			if(!isSucc)return;
			if(InfoClassify.STATUS3.byteValue() == specialInfo.getStatus().byteValue()
					&& specialInfo.getOnlineTime().before(now)){
				specialInfo.setOnlineTime(now);
				specialInfo.setOnlineTimeDate(now);
				specialInfo.setUpdateTime(now);
				specialInfo.setUpdator(sessionUser);
				specialInfo.setUpdatorId(sessionUserId);
				infoClassifyDao.saveAndFlush(specialInfo);
			}else{
				//修改专题修改时间等
				infoClassifyDao.updateUpdate(refInfoClassId, sessionUser, sessionUserId, now);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoCorrelationManager#infoCorrelation(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void infoCorrelation(Long infoClassifyId, Long refInfoClassifyId,Long appId,String sessionUser,Long sessionUserId) {
		
		if(null == infoClassifyId || null == refInfoClassifyId || null == appId){
			throw new BusinessException(ErrorCodes.FAILURE,"新闻ID不能为空");
		}
		
		List<InfoCorrelation> list = this.getEntityDao().findByInfoClssifyId(infoClassifyId, refInfoClassifyId);
		Date now = Dates.now();
		if(CollectionUtils.isEmpty(list)){
			
			InfoCorrelation infoCorrelation = new InfoCorrelation();
			infoCorrelation.setAppId(appId);
			infoCorrelation.setInfoClassifyId(infoClassifyId);
			infoCorrelation.setRefInfoClassifyId(refInfoClassifyId);
			infoCorrelation.setSortNo(Integer.MAX_VALUE);
			infoCorrelation.setCreateTime(now);
			infoCorrelation.setCreator(sessionUser);
			infoCorrelation.setCreatorId(sessionUserId);
			infoCorrelation.setUpdateTime(now);
			infoCorrelation.setUpdator(sessionUser);
			infoCorrelation.setUpdatorId(sessionUserId);
			infoCorrelation.setStatus(InfoCorrelation.STATUS3);
			this.getEntityDao().saveAndFlush(infoCorrelation);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoCorrelationManager#cancelCorrelation(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void cancelCorrelation(Long infoClassifyId, Long refInfoClassifyId) {
		
		if(null == infoClassifyId || null == refInfoClassifyId){
			throw new BusinessException(ErrorCodes.FAILURE,"新闻ID不能为空");
		}
		this.getEntityDao().cancelCorrelation(infoClassifyId, refInfoClassifyId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoCorrelationManager#joinInfo(java.lang.Long, java.lang.Long[], java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void joinInfo(Long[] infoClassifyIds, Long refInfoClassifyId, Long appId,Long infoThemeId,String sessionUser,Long sessionUserId) {
			
		if(StringUtil.isEmpty(infoClassifyIds) || null == refInfoClassifyId || null == appId  || null == infoThemeId){
			throw new BusinessException(ErrorCodes.FAILURE,"加入新闻失败");
		}
		//专题新闻
		InfoClassify specialInfo = infoClassifyDao.get(refInfoClassifyId);
		if(null == specialInfo){
			return;
		}
		Date now = Dates.now();
		boolean isSucc = false;
		for(int j=0,k=infoClassifyIds.length;j<k;j++){
			Long infoClassifyId = infoClassifyIds[j];
			List<InfoCorrelation> list = this.getEntityDao().findByInfoClssifyId(refInfoClassifyId,infoClassifyId);
			if(CollectionUtils.isEmpty(list)){
				InfoCorrelation infoCorrelation = new InfoCorrelation();
				infoCorrelation.setAppId(appId);
				infoCorrelation.setInfoClassifyId(refInfoClassifyId);
				infoCorrelation.setRefInfoClassifyId(infoClassifyId);
				infoCorrelation.setSortNo(Integer.MAX_VALUE);
				infoCorrelation.setThemeId(infoThemeId);
				infoCorrelation.setCreateTime(now);
				infoCorrelation.setCreator(sessionUser);
				infoCorrelation.setCreatorId(sessionUserId);
				infoCorrelation.setUpdateTime(now);
				infoCorrelation.setUpdator(sessionUser);
				infoCorrelation.setUpdatorId(sessionUserId);
				infoCorrelation.setStatus(InfoCorrelation.STATUS3);
				this.getEntityDao().saveAndFlush(infoCorrelation);
				//修改该新闻为已加入专题
				infoClassifyDao.changeAddSpecialStatus(infoClassifyId, InfoClassify.ADDSPECIALSTATUS1);
				isSucc = true;
			}
		}
		if(!isSucc)return;
		//修改专题修改时间等
		if(InfoClassify.STATUS3.byteValue() == specialInfo.getStatus().byteValue()
				&& specialInfo.getOnlineTime().before(now)){
			specialInfo.setOnlineTime(now);
			specialInfo.setOnlineTimeDate(now);
			specialInfo.setUpdateTime(now);
			specialInfo.setUpdator(sessionUser);
			specialInfo.setUpdatorId(sessionUserId);
			infoClassifyDao.saveAndFlush(specialInfo);
		}else{
			//修改专题修改时间等
			infoClassifyDao.updateUpdate(refInfoClassifyId, sessionUser, sessionUserId, now);
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.manager.InfoCorrelationManager#findByInfoIdAndRefId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<InfoCorrelation> findByInfoIdAndRefId(Long infoClassifyId, Long refClassifyId) {
		
		return this.getEntityDao().findByInfoClssifyId(infoClassifyId,refClassifyId);
	}

	@Override
	public CommonListResult<NewsData> getCorrelation(Long appId, Long infoClassifyId) {
		//查询数据
		List<InfoClassifyDto> dtos = getEntityDao().getCorrelation(appId, infoClassifyId);
		//返回数据
		CommonListResult<NewsData> newsResult = new CommonListResult<NewsData>();
		List<NewsData> newList = Lists.newArrayList();
		NewsData news;
		InfoClassifyDto dto;
		if (CollectionUtils.isNotEmpty(dtos)) {
			AppConfig appConfig = appConfigManager.findByAppId(dtos.get(0).getAppId());
			for (int i = 0; i < dtos.size(); i++) {
				if (i >= 5) {
					break;
				}
				news = new NewsData();
				dto = dtos.get(i);
				//计算浏览量和评论量
				InformationDto informationDto = new InformationDto();
				informationDto.setId(dto.getInformationId());
				informationDto.setInfoClassifyId(dto.getId());
				informationDto.setViewCount(dto.getDetailViewCount());
				informationDto.setReplyCount(dto.getReplyCount());
				informationDto.setInitCount(dto.getInitCount());
				informationDto.setTopTime(dto.getTopTime());
				informationDto.setOnlineTime(dto.getOnlineTime());
				informationDto.setAddType(dto.getAddType());
				informationManager.setViewAndReplyCount(informationDto);
				news.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
				news.setReplyCount(informationDto.getReplyCount());
//				if (Information.ADDTYPE0.equals(dto.getAddType())) {	//一次添加
//					news.setViewCount(dto.getInitCount().intValue() + dto.getDetailViewCount());
//				} else if (Information.ADDTYPE1.equals(dto.getAddType())) {	//逐步添加
//					Long secondDiff = (DateUtil.now().getTime() - dto.getOnlineTime().getTime()) / 1000;
//					if (secondDiff.intValue() >= dto.getTopTime()) {
//						news.setViewCount(dto.getInitCount().intValue() + dto.getDetailViewCount());
//					} else {
//						Integer viewCount = dto.getInitCount().intValue() * secondDiff.intValue() / dto.getTopTime() + dto.getDetailViewCount();
//						news.setViewCount(viewCount);
//					}
//				} else {
//					news.setViewCount(0);
//				}
				//详情展示类型
				Byte detailViewType;
				if (Information.CONTEXTTYPE1.equals(dto.getContextType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_1;
				} else if (Information.TYPE0.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				} else if (Information.TYPE2.equals(dto.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_3;
				} else {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				}
				news.setCommentType(dto.getCommentType());
				news.setContentUrl(dto.getContentUrl());
				news.setContextType(dto.getContextType());
				news.setDetailViewType(detailViewType);
				news.setId(dto.getId());
				news.setImages(dto.getImgUrls());
				news.setInformationId(dto.getInformationId());
				news.setInfoSource(dto.getInfoSource());
				news.setListViewType(dto.getListViewType());
				news.setOnlineDate(DateUtil.convertTimeToFormatHore1(dto.getOnlineTime().getTime(), "MM-dd HH:mm"));
				news.setOnlineTime(DateUtil.toString(dto.getOnlineTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				news.setSortNo(dto.getSortNo());
				news.setSourceType(BusinessType.SOURCE_TYPE_1);
				news.setSynopsis(dto.getSynopsis());
				news.setInfoLabel(dto.getInfoLabel());
//				news.setReplyCount(dto.getReplyCount());
				news.setTitle(StringUtils.isNotBlank(dto.getListTitle()) ? dto.getListTitle() : dto.getTitle());
				news.setDetailTitle(dto.getTitle());	
				news.setType(dto.getType());
				news.setUrl(InformationUtil.getRedirectUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId()));
				news.setShareUrl(InformationUtil.getShareUrl(dto.getContextType(), dto.getType(), dto.getContentUrl(), dto.getId(), appConfig.getDownLoadUrl()));
				news.setShareTitle(InformationUtil.getShareTitle(dto.getAppId(), dto.getTitle()));
				newList.add(news);
			}
			newsResult.setDataList(newList);
		}
		return newsResult;
	}

}
