package com.cqliving.cloud.online.account.manager.impl;


import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.common.JokeInfoUtil;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.common.constant.ClientControlType;
import com.cqliving.cloud.online.account.dao.UserFavoriteDao;
import com.cqliving.cloud.online.account.domain.UserFavorite;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserFavoriteManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.config.manager.CommentAppConfigManager;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.FavoritesData;
import com.cqliving.cloud.online.interfacc.dto.FavoritesShoppingData;
import com.cqliving.cloud.online.shop.dao.ShopInfoDao;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.tourism.dao.TourismInfoDao;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.CurrencyUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("userFavoriteManager")
public class UserFavoriteManagerImpl extends EntityServiceImpl<UserFavorite, UserFavoriteDao> implements UserFavoriteManager {

	@Autowired
	private AppConfigManager appConfigManager;
	@Autowired
	private CommentAppConfigManager commentAppConfigManager;
	@Autowired
	private InformationManager informationManager;
	@Autowired
	private ShopInfoDao shopInfoDao;
	@Autowired
	private ShoppingGoodsDao shoppingGoodsDao;
	@Autowired
	private TourismInfoDao tourismInfoDao;
//	@Autowired
//	private UserAccountManager userAccountManager;
	@Autowired
	private UserFavoriteDao userFavoriteDao;
	@Autowired
	private UserSessionManager userSessionManager;
	
	@Override
	public CommonListResult<FavoritesData> getMyFavoritesPage(Long appId, String sessionId, String token, Long lastId) {
		//获取用户信息
//		UserSession userSession = userSessionManager.get(sessionId, token);
//		if (userSession == null) {	//用户不存在
//			throw new BusinessException(
//					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
//					ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
//		}
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		
		//查询数据
		ScrollPage<InfoClassifyDto> page = new ScrollPage<InfoClassifyDto>();
		page = getEntityDao().getMyFavoritesInfoPage(lastId, appId, userSession.getUserId());
		List<InfoClassifyDto> list = page.getPageResults();
		
		//返回数据
		CommonListResult<FavoritesData> result = new CommonListResult<FavoritesData>();
		List<FavoritesData> favorites = Lists.newArrayList();
		FavoritesData data;
		if (CollectionUtils.isNotEmpty(list)) {
			AppConfig appConfig = appConfigManager.findByAppId(list.get(0).getAppId());
			for (InfoClassifyDto obj : list) {
				data = new FavoritesData();
				//详情展示类型
				Byte detailViewType;
				if (Information.CONTEXTTYPE1.equals(obj.getContextType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_1;
				} else if (Information.TYPE0.equals(obj.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_2;
				} else if (Information.TYPE2.equals(obj.getType())) {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_3;
				} else {
					detailViewType = ClientControlType.DETAIL_VIEW_TYPE_5;
				}
				//详情和分享Url、分享标题
				if (BusinessType.SOURCE_TYPE_1.equals(obj.getSourceType())) {
					//计算浏览量
					InformationDto informationDto = new InformationDto();
					informationDto.setId(obj.getInformationId());
					informationDto.setInfoClassifyId(obj.getId());
					informationDto.setViewCount(obj.getDetailViewCount());
					informationDto.setReplyCount(obj.getTotalReplyCount());
					informationDto.setInitCount(obj.getInitCount());
					informationDto.setTopTime(obj.getTopTime());
					informationDto.setOnlineTime(obj.getOnlineTime());
					informationDto.setAddType(obj.getAddType());
					informationManager.setViewAndReplyCount(informationDto);
					data.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
					data.setReplyCount(informationDto.getReplyCount());
					data.setShareTitle(InformationUtil.getShareTitle(obj.getAppId(), obj.getTitle()));
					data.setShareUrl(InformationUtil.getShareUrl(obj.getContextType(), obj.getType(), obj.getContentUrl(), obj.getId(), appConfig.getDownLoadUrl()));
					data.setUrl(InformationUtil.getRedirectUrl(obj.getContextType(), obj.getType(), obj.getContentUrl(), obj.getId()));
				} else if (BusinessType.SOURCE_TYPE_5.equals(obj.getSourceType())) {	//段子
					data.setReplyCount(obj.getTotalReplyCount() == null ? 0 : obj.getTotalReplyCount());
					data.setShareTitle(obj.getTitle());
					data.setShareUrl(JokeInfoUtil.getShareUrl(obj.getId()));
					data.setUrl("");
				} 
				data.setCommentType(obj.getCommentType());
				data.setContentUrl(obj.getContentUrl());
				data.setContextType(obj.getContextType());
				data.setDetailViewType(detailViewType);
				data.setFavoriteId(obj.getUserFavoriteId());
				data.setImages(obj.getImgUrls());
				data.setId(obj.getId());
				data.setInfoLabel(obj.getInfoLabel());
				data.setInformationId(obj.getInformationId());
				data.setInfoSource(obj.getInfoSource());
				data.setMultipleImgCount(obj.getMultipleImgCount());
				data.setListViewType(obj.getListViewType());
				data.setOnlineDate(DateUtil.convertTimeToFormatHore1(obj.getOnlineTime().getTime(), "MM-dd HH:mm"));
				data.setOnlineTime(DateUtil.toString(obj.getOnlineTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				data.setSortNo(obj.getSortNo());
				data.setSourceType(obj.getSourceType());
				data.setSynopsis(obj.getSynopsis());
				data.setTitle(StringUtils.isNotBlank(obj.getListTitle()) ? obj.getListTitle() : obj.getTitle());
				data.setDetailTitle(obj.getTitle());	
				data.setType(obj.getType());
				favorites.add(data);
			}
		}
		result.setDataList(favorites);
		return result;
	}

	@Override
	public CommonListResult<FavoritesShoppingData> getMyFavoritesShoppingPage(Long appId, String sessionId, String token, Long lastFavoriteId) {
		//获取用户信息
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		
		//查询数据
		ScrollPage<ShoppingGoodsDto> page = new ScrollPage<ShoppingGoodsDto>();
		page = getEntityDao().getMyFavoritesShoppingPage(lastFavoriteId, appId, userSession.getUserId());
		List<ShoppingGoodsDto> list = page.getPageResults();
		
		//返回数据
		CommonListResult<FavoritesShoppingData> result = new CommonListResult<FavoritesShoppingData>();
		List<FavoritesShoppingData> dataList = Lists.newArrayList();
		FavoritesShoppingData data;
		if (CollectionUtils.isNotEmpty(list)) {
			Byte canComment = commentAppConfigManager.getConfigValueByName(appId, CommentConfig.CAN_COMMENT, BusinessType.SOURCE_TYPE_13);
			NumberFormat format = NumberFormat.getInstance();
			format.setMaximumFractionDigits(2);	//保留两位小数
			for (ShoppingGoodsDto obj : list) {
				data = new FavoritesShoppingData();
				//详情展示类型
				Byte detailViewType = -1;		//FIXME Tangtao 确定客户端展示类型
				data.setCollectCount(obj.getCollectCount());
				data.setCommentType(NumberUtils.toByte(CommentConfig.VALUE0.equals(canComment) ? "1" : "0"));
				data.setDetailViewType(detailViewType);
				data.setFavoriteId(obj.getFavoriteId());
				data.setLabels(obj.getLabels());
				data.setListImageUrl(obj.getListImageUrl());
				data.setName(obj.getName());
				data.setOriginalPrice(CurrencyUtil.format(obj.getOriginalPrice(), 2, 2, true));
				data.setPrice(CurrencyUtil.format(obj.getPrice(), 2, 2, true));
				data.setShareUrl("");	//FIXME Tangtao 确定详情及分享链接
				data.setSourceId(obj.getSourceId());
				data.setSourceType(BusinessType.SOURCE_TYPE_13);
				data.setSynopsis(obj.getSynopsis());
				data.setUrl("");	//FIXME Tangtao 确定详情及分享链接
				dataList.add(data);
			}
		}
		result.setDataList(dataList);
		return result;
	}

	@Override
	public boolean isCollected(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		UserSession userSession;
		//获取用户信息
		if (StringUtils.isNotBlank(token)) {	//注册用户
			userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
		} else {
			//登录用户才能收藏 By Tangtao 2016-07-11
//			userSession = userSessionManager.getTourist(sessionId);
//			if (userSession == null) {	//游客不存在
//				return false;
//			}
			return false;
		}
		
		//查询
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_userId", userSession.getUserId());
		map.put("EQ_sourceId", sourceId);
		map.put("EQ_sourceType", sourceType);
		map.put("EQ_status", UserFavorite.STATUS3);
		List<UserFavorite> list = query(map, null);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public boolean collect(Long appId, String sessionId, String token, Byte type, String title, Long sourceId, Byte sourceType) {
		UserSession userSession;
		Long userId;
		//获取用户信息
		if (StringUtils.isNotBlank(token)) {	//注册用户
			userSession = userSessionManager.getByToken(token);
			if (userSession == null) {	//用户未登录
				throw new BusinessException(
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
						ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
			}
			userId = userSession.getUserId();
		} else {
			//游客不允许收藏 By Tangtao 2016-07-11
//			userSession = userSessionManager.getTourist(sessionId);
//			if (userSession == null) {	//游客不存在
//				//创建游客账户
//				UserAccount userAccount = userAccountManager.createTourist(appId, sessionId);
//				userId = userAccount.getId();
//			} else {
//				userId = userSession.getUserId();
//			}
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		
		if (type.byteValue() == 1) {	//收藏
			//查询是否已收藏
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_appId", appId);
			map.put("EQ_userId", userId);
			map.put("EQ_sourceId", sourceId);
			map.put("EQ_sourceType", sourceType);
			map.put("EQ_status", UserFavorite.STATUS3);
			List<UserFavorite> list = query(map, null);
			if (CollectionUtils.isNotEmpty(list)) {
				throw new BusinessException(ErrorCodes.FAILURE, "已收藏");
			}
			
			//保存收藏记录
			UserFavorite obj = new UserFavorite();
			obj.setAppId(appId);
//			obj.setColumnsName(appColumns.getName());
			obj.setCreateTime(DateUtil.now());
//			obj.setSourceColumns(columnsId);
			obj.setSourceId(sourceId);
			obj.setSourceType(sourceType);
			obj.setStatus(UserFavorite.STATUS3);
			obj.setTitle(title.length() > 75 ? title.substring(0, 75) : title);	//只保留75个字符
			obj.setUserId(userId);
			save(obj);
			
			//递增收藏数
			if (BusinessType.SOURCE_TYPE_3.equals(sourceType)) {	//收藏商情
				shopInfoDao.increaseCollectedCount(sourceId);
			} else if (BusinessType.SOURCE_TYPE_10.equals(sourceType)) {	//收藏旅游
				tourismInfoDao.increaseCollectedCount(sourceId);
			} else if (BusinessType.SOURCE_TYPE_13.equals(sourceType)) {	//收藏商城商品
				shoppingGoodsDao.increaseCollectedCount(sourceId);
			}
		} else if (type.byteValue() == 0) {	//取消收藏
			int count = userFavoriteDao.changeStatus(userId, sourceType, sourceId, UserFavorite.STATUS99);
			if (count <= 0) {
				return false;
			}
			if (BusinessType.SOURCE_TYPE_3.equals(sourceType)) {	//取消收藏商情
				shopInfoDao.decreaseCollectedCount(sourceId);
			} else if (BusinessType.SOURCE_TYPE_10.equals(sourceType)) {	//取消收藏旅游
				tourismInfoDao.decreaseCollectedCount(sourceId);
			} else if (BusinessType.SOURCE_TYPE_13.equals(sourceType)) {	//取消收藏商城商品
				shoppingGoodsDao.decreaseCollectedCount(sourceId);
			}
		} else {
			return false;
		}
		return true;
	}

}