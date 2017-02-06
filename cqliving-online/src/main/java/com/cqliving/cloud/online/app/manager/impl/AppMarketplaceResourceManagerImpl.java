package com.cqliving.cloud.online.app.manager.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppMarketplaceResourceDao;
import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.dto.AppMarketplaceResourceDto;
import com.cqliving.cloud.online.app.manager.AppImageVersionManager;
import com.cqliving.cloud.online.app.manager.AppMarketplaceResourceManager;
import com.cqliving.cloud.online.interfacc.dto.InitStartLoadingImg;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("appMarketplaceResourceManager")
public class AppMarketplaceResourceManagerImpl extends EntityServiceImpl<AppMarketplaceResource, AppMarketplaceResourceDao> implements AppMarketplaceResourceManager {
	
	@Autowired
	private AppImageVersionManager appImageVersionManager;

	@Override
	@Deprecated
	public List<InitStartLoadingImg> getUpdateImgs(Long appId, Integer type, Integer version) {
		List<AppMarketplaceResourceDto> list = getEntityDao().getUpdateImgs(appId, type, version);
		List<InitStartLoadingImg> imgs = Lists.newArrayList();
		InitStartLoadingImg img;
		Integer currAdVersion = null;
		Integer currLodingVersion = null;
		//过滤集合，找出最新的广告图和loading图
		if (CollectionUtils.isNotEmpty(list)) {
			for (AppMarketplaceResourceDto obj : list) {
				if (AppMarketplaceResource.IMAGETYPE0.equals(obj.getImageType())) {	//loading图
					if (currLodingVersion == null || currLodingVersion < obj.getVersionNo()) {	//获取最大版本号
						currLodingVersion = obj.getVersionNo();
					} else if (currLodingVersion > obj.getVersionNo()) {	//历史版本号，无需更新
						continue;
					}
				} else if (AppMarketplaceResource.IMAGETYPE1.equals(obj.getImageType())) {	//广告图
					if (currAdVersion == null || currAdVersion < obj.getVersionNo()) {	//获取最大版本号
						currAdVersion = obj.getVersionNo();
					} else if (currAdVersion > obj.getVersionNo()) {	//历史版本号，无需更新
						continue;
					}
				}
				img = new InitStartLoadingImg();
//				img.setImageType(obj.getImageType());
				img.setImageUrl(obj.getImageUrl());
//				img.setSortNo(obj.getSortNo());
				imgs.add(img);
			}
		}
		return imgs;
	}

	@Override
	public AppMarketplaceResource getLatestByApp(Long appId, Integer type) {
		//获取最新广告版本
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", type);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("versionNo", false);
		List<AppImageVersion> coll = appImageVersionManager.query(map, sortMap);
		if (CollectionUtils.isEmpty(coll)) {
			return null;
		}
		
		//获取广告图
		AppImageVersion imageVersion = coll.get(0);
		map.clear();
		map.put("EQ_appId", appId);
		map.put("EQ_versionId", imageVersion.getId());
		map.put("EQ_imageType", AppMarketplaceResource.IMAGETYPE1);
		sortMap.clear();
		sortMap.put("sortNo", true);
		List<AppMarketplaceResource> list = query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public AppMarketplaceResource getByVersion(Long appId, Long versionId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_versionId", versionId);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("versionId", false);
		List<AppMarketplaceResource> list = query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
     * 逻辑删除
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public int deleteLogic(Long[] ids){
        List<Long> idList = Arrays.asList(ids);
        return this.getEntityDao().updateStatus(AppMarketplaceResource.STATUS99, idList);
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
    
    /**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午5:18:43
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateSortNo(Integer sortNo, Long id) {
        this.getEntityDao().updateSortNo(sortNo, id);
    }
}