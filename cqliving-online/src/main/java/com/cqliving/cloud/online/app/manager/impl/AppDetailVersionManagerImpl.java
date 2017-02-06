package com.cqliving.cloud.online.app.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.dao.AppDetailVersionDao;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.manager.AppColumnsManager;
import com.cqliving.cloud.online.app.manager.AppDetailVersionManager;
import com.cqliving.cloud.online.app.manager.AppImageVersionManager;
import com.cqliving.cloud.online.app.manager.AppMarketplaceResourceManager;
import com.cqliving.cloud.online.app.manager.AppVersionManager;
import com.cqliving.cloud.online.config.domain.AppConfig;
import com.cqliving.cloud.online.config.manager.AppConfigManager;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.cloud.online.interfacc.dto.InitStartApp;
import com.cqliving.cloud.online.interfacc.dto.InitStartLoadingImg;
import com.cqliving.cloud.online.interfacc.dto.InitStartResult;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("appDetailVersionManager")
public class AppDetailVersionManagerImpl extends EntityServiceImpl<AppDetailVersion, AppDetailVersionDao> implements AppDetailVersionManager {
	
	@Autowired
	private AbstractRedisClient abstractRedisClient;
	@Autowired
	private AppColumnsManager appColumnsManager;
	@Autowired
	private AppConfigManager appConfigManager;
	@Autowired
	private AppImageVersionManager appImageVersionManager;
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private AppMarketplaceResourceManager appMarketplaceResourceManager;
	@Autowired
	private AppVersionManager appVersionManager;
	
	@Override
	public InitStartResult getInitStartInfo(Long appId, String sessionId, String token, Integer appVersion,
			Integer loadingImgVersion, Integer columnsVersion, Integer type) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		
		InitStartResult result = new InitStartResult();
		//获取当前客户端最新版本
		AppVersion version = appVersionManager.getLatest(appId, type);
		if (version != null) {
			InitStartApp app = new InitStartApp();
			app.setMinVersion(version.getMinVersion());
			app.setName(version.getName());
			app.setPublishTime(DateUtil.format(version.getPublishTime(), DateUtil.FORMAT_YYYY_MM_DD));
			app.setUpdateContext(version.getUpdateContext());
			app.setUrl(version.getUrl());
			app.setVesionNo(version.getVesionNo());
			app.setViewVersion(version.getViewVersion());
			result.setApp(app);
		}
		
		//获取最新广告图
		AppImageVersion imageVersion = appImageVersionManager.getLatest(appId, type);
		//广告图是否过期
		//update by huxiaoping 查询到了数据就表示需要替换图片，返回false（未过期）
		if (imageVersion != null) {
            result.setIsExpired(false);
        } else {
            result.setIsExpired(true);
        }
		if (imageVersion != null) {	
			//获取对应版本广告图
			AppMarketplaceResource marketplaceResource = appMarketplaceResourceManager.getByVersion(appId, imageVersion.getId());
			InitStartLoadingImg loadingImg = new InitStartLoadingImg();
			if (marketplaceResource != null) {
				loadingImg.setImageUrl(marketplaceResource.getImageUrl());
				loadingImg.setLinkUrl(marketplaceResource.getLinkUrl());
				loadingImg.setShowTime(marketplaceResource.getShowTime());	//By Tangtao 2016-12-07
			}
			result.setLoadingImg(loadingImg);
			result.setImgVersion(imageVersion.getVersionNo());
		} else {
			result.setImgVersion(loadingImgVersion);
		}
		
		//获取栏目最新版本
		AppDetailVersion colVersion = getLatest(appId, AppDetailVersion.TYPE2);
		if (colVersion != null && colVersion.getVersionNo() > columnsVersion) {	//有新的栏目版本
			//对比APP版本号，判断是否更新栏目数据 By Tangtao 2016-12-28
			//获取app_config配置数据
			AppConfig appConfig = getAppConfigFromCache(appId);
			//默认版本为0，就是默认允许更新
			Integer configVersion = 0;
			if (appConfig != null) {
				if (type.toString().equals(AppVersion.TYPE1)) {	//安卓
					String av = appConfig.getColumnVersionAndroid();
					if(StringUtils.isNotBlank(av) && NumberUtils.isDigits(av)){
						configVersion = Integer.valueOf(av);
					}
				} else if (type.toString().equals(AppVersion.TYPE2)) {	//iOS
					String iv = appConfig.getColumnVersionIos();
					if(StringUtils.isNotBlank(iv) && NumberUtils.isDigits(iv)){
						configVersion = Integer.valueOf(iv);
					}
				}
			}
			
			//当客户端版本大于等于配置的版本号时，获取栏目数据
			if (appVersion >= configVersion) {
				List<GetColumnsData> columns = appColumnsManager.getCache(appId);
				result.setColumns(columns);
			}
			result.setColVersionNo(colVersion.getVersionNo());
			
		} else {
			result.setColVersionNo(columnsVersion);
		}
		return result;
	}

	@Override
	public AppDetailVersion getLatest(Long appId, Byte type) {
		if (appId == null || !AppDetailVersion.allTypes.containsKey(type)) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", type);
		map.put("LTE_publishTime", DateUtil.now());
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("id", false);
		List<AppDetailVersion> list = query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * <p>Description: 从缓存中获取app_config数据</p>
	 * @author Tangtao on 2016年12月28日
	 * @param appId
	 * @return
	 */
	private AppConfig getAppConfigFromCache(Long appId) {
		AppConfig obj = abstractRedisClient.get(CacheConstant.APP_CONFIG_APPID_PREFIX + appId, AppConfig.class);
        if (obj == null) {
        	obj = appConfigManager.findByAppId(appId);
            abstractRedisClient.set(CacheConstant.APP_CONFIG_APPID_PREFIX + appId, obj);
        }
        return obj;
    }
	
}