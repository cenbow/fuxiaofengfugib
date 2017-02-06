package com.cqliving.cloud.online.app.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppDetailVersionDao;
import com.cqliving.cloud.online.app.dao.AppImageVersionDao;
import com.cqliving.cloud.online.app.dao.AppMarketplaceResourceDao;
import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.domain.AppMarketplaceResource;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.cloud.online.app.manager.AppImageVersionManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("appImageVersionManager")
public class AppImageVersionManagerImpl extends EntityServiceImpl<AppImageVersion, AppImageVersionDao> implements AppImageVersionManager {
    
    @Autowired
    private AppMarketplaceResourceDao appMarketplaceResourceDao;
    
    @Autowired
    private AppDetailVersionDao appDetailVersionDao;
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    @Override
    public PageInfo<AppImageVersionDto> queryPage(PageInfo<AppImageVersionDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryPage(pageInfo, map, orderMap);
    }

    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月5日上午10:19:32
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void saveImageVersion(AppImageVersion appImageVersion, String loadingUrl, String linkUrl,Integer showTime) {
        //设置版本号
        Integer versionNo = this.getEntityDao().findVersionNoByAppId(appImageVersion.getAppId());
        versionNo = versionNo == null ? 1 : (versionNo+1);
        appImageVersion.setVersionNo(versionNo);
        appImageVersion.setStartTime(appImageVersion.getStartTime()==null?new Date():appImageVersion.getStartTime());
        appImageVersion.setEndTime(appImageVersion.getEndTime()==null?new Date():appImageVersion.getEndTime());
        //appImageVersion.setUseStatus(AppImageVersion.USESTATUS0);
        appImageVersion.setStatus(appImageVersion.getStatus()==null?AppImageVersion.STATUS3:appImageVersion.getStatus());
        
        //保存客户端发布广告表
        save(appImageVersion);
        //保存客户端发布市场资源表
        List<AppMarketplaceResource> appList = appMarketplaceResourceDao.getByVersionId(appImageVersion.getId());
        AppMarketplaceResource appMarketplaceResource =  null;
        if(null!=appList&&appList.size()>0){
            appMarketplaceResource =  appList.get(0);
        }else{
            appMarketplaceResource =  new AppMarketplaceResource();
        }
        //保存loding图
        appMarketplaceResource.setAppId(appImageVersion.getAppId());
        appMarketplaceResource.setVersionId(appImageVersion.getId());
        appMarketplaceResource.setImageType(AppMarketplaceResource.IMAGETYPE1);
        appMarketplaceResource.setCreateTime(appImageVersion.getCreateTime());
        appMarketplaceResource.setCreator(appImageVersion.getCreator());
        appMarketplaceResource.setCreatorId(appImageVersion.getCreatorId());
        appMarketplaceResource.setUpdateTime(appImageVersion.getUpdateTime());
        appMarketplaceResource.setUpdator(appImageVersion.getUpdator());
        appMarketplaceResource.setUpdatorId(appImageVersion.getUpdatorId());
        appMarketplaceResource.setSortNo(1);
        appMarketplaceResource.setImageUrl(loadingUrl);
        appMarketplaceResource.setLinkUrl(linkUrl);
        //广告显示时间
        appMarketplaceResource.setShowTime(showTime==null?2:showTime);
        appMarketplaceResourceDao.save(appMarketplaceResource);
    }
    
    /**
     * 查询单个记录（包括所有图片）
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    @Override
    public AppImageVersionDto queryById(Long id) {
        return getEntityDao().queryById(id);
    }

	@Override
	public AppImageVersion getLatest(Long appId, Integer type) {
		if (appId == null) {
			return null;
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("LTE_startTime", DateUtil.now());
		//update by huxiaoping 最后一条数据已过期bug的修改,查询开始时间小于等于当前时间，并且结束时间大于等于当期时间的数据
		map.put("GTE_endTime", DateUtil.now());
		map.put("LIKE_type", type);
		map.put("EQ_status", AppImageVersion.STATUS3);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		//update by huxiaoping 开始时间在前的有优先展示权
		sortMap.put("startTime", true);
		sortMap.put("id", false);
		List<AppImageVersion> list = query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	/**
     * 启用
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月2日下午4:10:1
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void startUsing(Long id) {
        AppImageVersion appImageVersion = get(id);
        if(null!=appImageVersion){
            //停用和设置总版本
            onoUse(appImageVersion);
            //设置启用，结束时间设置到20年后
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 20);
            Date endTime=calendar.getTime();
            this.getEntityDao().updateUseStatusById(AppImageVersion.USESTATUS1, id, endTime);
        }
    }
    
    /**
     * 停用和总版本变更
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年9月26日下午2:02:12
     */
    private void onoUse(AppImageVersion appImageVersion){
      //停用之前使用的数据
        this.getEntityDao().updateUseStatusByAppId(AppImageVersion.USESTATUS0, appImageVersion.getAppId(),new Date());
        
        //设置总版本
        List<AppDetailVersion> detailList = appDetailVersionDao.getByAppId(appImageVersion.getAppId(), AppDetailVersion.TYPE1);
        if(null!=detailList&&detailList.size()>0){
            AppDetailVersion detail = detailList.get(0);
            detail.setVersionNo(detail.getVersionNo()+1);
            detail.setPublishTime(new Date());
            appDetailVersionDao.update(detail);
        }else{
            AppDetailVersion detail = new AppDetailVersion();
            detail.setAppId(appImageVersion.getAppId());
            detail.setVersionNo(2);
            detail.setPublishTime(new Date());
            detail.setType(AppDetailVersion.TYPE1);
            appDetailVersionDao.save(detail);
        }
    }
    
    /**
     * 设置停用
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月2日下午4:10:1
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void nonUse(Long id) {
        AppImageVersion appImageVersion = get(id);
        if(null!=appImageVersion){
            onoUse(appImageVersion);
        }
    }
}