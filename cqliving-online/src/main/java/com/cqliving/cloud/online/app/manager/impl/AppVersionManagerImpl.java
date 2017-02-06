package com.cqliving.cloud.online.app.manager.impl;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppDetailVersionDao;
import com.cqliving.cloud.online.app.dao.AppVersionDao;
import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.cloud.online.app.manager.AppVersionManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

/**
 * 
 * <p>Title:AppVersionManagerImpl </p>
 * <p>Description:App版本管理 业务处理</p>
 * <p>Company: </p>
 * @author huxiaoping 2016年4月26日下午5:20:54
 *
 */
@Service("appVersionManager")
public class AppVersionManagerImpl extends EntityServiceImpl<AppVersion, AppVersionDao> implements AppVersionManager {
    @Autowired
    private AppDetailVersionDao appDetailVersionDao;
    
    /**
     * 分页查询版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月26日下午5:20:31
     */
    @Override
    public PageInfo<AppVersionDto> queryByPage(PageInfo<AppVersionDto> pageInfo, Map<String, Object> conditions,Map<String, Boolean> orders){
        return this.getEntityDao().queryByPage(pageInfo, conditions, orders);
    }
    
    /**
     * 保存客户端版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void saveVersion(AppVersion version) {
        /*
        Integer vno = this.getEntityDao().findMaxVNoByAppId(version.getAppId());
        if(null==vno){
            vno = 0;
        }
        version.setVesionNo(vno+1);
        */
        this.save(version);
        //一个APP对应版本总表只有一条记录
        List<AppDetailVersion> detailList = appDetailVersionDao.getByAppId(version.getAppId(), AppDetailVersion.TYPE0);
        if(null!=detailList&&detailList.size()>0){
            AppDetailVersion detail = detailList.get(0);
            detail.setVersionNo(version.getVesionNo());
            detail.setPublishTime(version.getPublishTime());
            appDetailVersionDao.update(detail);
        }else{
            AppDetailVersion detail = new AppDetailVersion();
            detail.setAppId(version.getAppId());
            detail.setVersionNo(version.getVesionNo());
            detail.setPublishTime(version.getPublishTime());
            detail.setType(AppDetailVersion.TYPE0);
            appDetailVersionDao.save(detail);
        }
    }
    
    /**
     * 逻辑删除
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void deleteById(Long id) {
        this.getEntityDao().changeStatus(id, AppVersion.STATUS3);
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
		return this.getEntityDao().updateStatus(AppVersion.STATUS99, idList);
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
     * 修改客户端版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void updateVersion(AppVersion version) {
        this.update(version);
        List<AppDetailVersion> detailList = appDetailVersionDao.getByAppId(version.getAppId(), AppDetailVersion.TYPE0);
        if(null!=detailList){
            AppDetailVersion detail = detailList.get(0);
            detail.setVersionNo(version.getVesionNo());
            detail.setPublishTime(version.getPublishTime());
            appDetailVersionDao.update(detail);
        }
    }

    @Override
    public AppVersionDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }

	@Override
	public AppVersion getLatest(Long appId, Integer type) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_type", type);
		map.put("EQ_status", AppVersion.STATUS3);
		map.put("LTE_publishTime", DateUtil.now());
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("vesionNo", false);
		List<AppVersion> list = query(map, sortMap);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
}
