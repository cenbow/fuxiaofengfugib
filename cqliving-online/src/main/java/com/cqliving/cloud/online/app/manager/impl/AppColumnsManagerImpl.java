package com.cqliving.cloud.online.app.manager.impl;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.online.app.dao.AppColumnsDao;
import com.cqliving.cloud.online.app.dao.AppDetailVersionDao;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.domain.AppDetailVersion;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.manager.AppColumnsManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.cloud.online.interfacc.dto.InitStartColumns;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("appColumnsManager")
public class AppColumnsManagerImpl extends EntityServiceImpl<AppColumns, AppColumnsDao> implements AppColumnsManager {
    @Autowired
    private AppDetailVersionDao appDetailVersionDao;
    @Autowired
    private AbstractRedisClient abstractRedisClient;
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月30日下午1:10:51
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void saveColumns(AppColumns appColumns) {
        /**
         * 增加修改栏目，不对总版本做删除功能。由发布功能去操作总版本
         */
        //设置版本号
        Integer versionNo = this.getEntityDao().findVersionNoByAppId(appColumns.getAppId());
        if(null!=versionNo){
            appColumns.setVersionNo(versionNo+1);
        }else{
            appColumns.setVersionNo(1);
        }
        if(null==appColumns.getParentId()){
        	appColumns.setParentId(0l);
        }
        //设置排序号
        Integer maxSortNo  = this.getEntityDao().getMaxSortNo(appColumns.getParentId());
        appColumns.setSortNo(maxSortNo==null?1:maxSortNo+1);
        
        if(StringUtils.isBlank(appColumns.getParentCode())){
        	appColumns.setParentCode("0");
        }
        appColumns.setCode("0");
        
        //保存栏目
        this.save(appColumns);
        //修改栏目code
        if(0l==appColumns.getParentId().longValue()){
            appColumns.setParentCode("0");
        	appColumns.setCode(appColumns.getId()+"");
        }else{
            if("0".equals(appColumns.getParentCode())){
                appColumns.setCode(appColumns.getParentId()+"."+appColumns.getId());
                appColumns.setParentCode(appColumns.getParentId()+"");
            }else{
                appColumns.setCode(appColumns.getParentCode()+"."+appColumns.getParentId()+"."+appColumns.getId());
                appColumns.setParentCode(appColumns.getParentCode()+"."+appColumns.getParentId());
            }
        	//修改父栏目为非叶子节点
        	this.getEntityDao().changeLeafType(appColumns.getParentId(), AppColumns.LEAFTYPE0);
        }
        
        this.update(appColumns);
    }

    /* (non-Javadoc)
     * @see com.cqliving.cloud.online.app.manager.AppColumnsManager#getByConditions(java.util.Map)
     */
    @Override
    public List<AppColumnsDto> getByConditions(Map<String, Object> conditions) {
        
    	AppColumnsDto dto = null;
    	if(null != conditions && !conditions.isEmpty()){
    		Boolean appendAll =  (Boolean) conditions.get("appendAll");
    		if(null == appendAll || appendAll){
    			dto = new AppColumnsDto();
        		dto.setName("全部");
        		dto.setText("全部");
    		}
    		conditions.remove("appendAll");
    	}
    	List<AppColumnsDto> data = this.getEntityDao().getByConditions(conditions);
    	
    	if(CollectionUtils.isNotEmpty(data) && null != dto){
    		data.add(0, dto);
    	}
        return data;
    }

	@Override
	public List<InitStartColumns> getUpdateParentCols(Long appId, Integer version) {
		List<AppColumns> list = getEntityDao().getUpdateParentCols(appId, version);
		List<InitStartColumns> columns = Lists.newArrayList();
		InitStartColumns col;
		if (CollectionUtils.isNotEmpty(list)) {
			for (AppColumns obj : list) {
				col = new InitStartColumns();
				col.setParentColumnId(obj.getId());
				columns.add(col);
			}
		}
		return columns;
	}

	@Override
	public CommonListResult<GetColumnsData> getChildren(Long appId, String parentCode) {
		//查询数据
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		map.put("EQ_parentCode", parentCode);
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo", true);
		sortMap.put("id", false);
		List<AppColumns> list = query(map, sortMap);
		
		//返回数据
		CommonListResult<GetColumnsData> result = new CommonListResult<GetColumnsData>();
		List<GetColumnsData> columns = Lists.newArrayList();
		GetColumnsData c;
		if (CollectionUtils.isNotEmpty(list)) {
			for (AppColumns obj : list) {
				c = new GetColumnsData();
				c.setCode(obj.getCode());
				c.setColumnsType(obj.getColumnsType());
				c.setColumnsUrl(obj.getColumnsUrl());
				c.setId(obj.getId());
				c.setImageUrl(obj.getImageUrl());
				c.setName(obj.getName());
				c.setParentCode(obj.getParentCode());
				c.setSortNo(obj.getSortNo());
				c.setTempletCode(obj.getTempletCode());
				columns.add(c);
			}
			result.setDataList(columns);
		}
		return result;
	}
	
	/**
     * 修改
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月2日下午1:10:51
     */
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	@Override
	public void updateColumns(AppColumns appColumns) {
		AppColumns old = this.get(appColumns.getId());
		if(null!=old){
			//是否叶子节点
			appColumns.setLeafType(old.getLeafType());
			//排序号
			appColumns.setSortNo(old.getSortNo());
			
	        //比较看是否有栏目属性被改了，改了就要更改版本
	        boolean flag = eq(appColumns, old);
	        if(flag){
	            Integer versionNo = this.getEntityDao().findVersionNoByAppId(appColumns.getAppId());
	            if(null!=versionNo){
	                appColumns.setVersionNo(versionNo+1);
	            }else{
	                appColumns.setVersionNo(old.getVersionNo()+1);
	            }
	        }else{
	        	appColumns.setVersionNo(old.getVersionNo());
	        }
	        //保存栏目
	        this.update(appColumns);
		}
	}
	
	/**
     * 发布
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月20日
     */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void send(Long AppId){
	    // 修改状态为发布
	    this.getEntityDao().sendByAppId(AppColumns.STATUS3, AppId, AppColumns.STATUS88);
	    //获取栏目总版本
        List<AppDetailVersion> detailList = appDetailVersionDao.getByAppId(AppId, AppDetailVersion.TYPE2);
        //保存栏目版本
        if(null!=detailList&&detailList.size()>0){
            AppDetailVersion detail = detailList.get(0);
            if(null!=detail){
                detail.setVersionNo(null==detail.getVersionNo()?2:(detail.getVersionNo()+1));
                detail.setPublishTime(new Date());
                appDetailVersionDao.update(detail);
                //缓存操作
                saveCache(AppId);
            }
        }else{
            AppDetailVersion detail = new AppDetailVersion();
            detail.setAppId(AppId);
            detail.setVersionNo(2);
            detail.setPublishTime(new Date());
            detail.setType(AppDetailVersion.TYPE2);
            appDetailVersionDao.save(detail);
            //缓存操作
            saveCache(AppId);
        }
	    
	}
	
	
	/**
	 * 比较对象是否变更
	 * @param appColumns 改变后的对象
	 * @param old 源对象
	 * @return
	 */
	private boolean eq(AppColumns appColumns,AppColumns old){
		 //名称
        if(StringUtils.isNotBlank(appColumns.getName())&&(!appColumns.getName().equals(old.getName()))){
        	return true;
        }
        //CODE
        if(StringUtils.isNotBlank(appColumns.getCode())&&(!appColumns.getCode().equals(old.getCode()))){
        	return true;
        }
        //图片
        if(StringUtils.isNotBlank(appColumns.getImageUrl())&&(!appColumns.getImageUrl().equals(old.getImageUrl()))){
        	return true;
        }
        //平台可见
        if(StringUtils.isNotBlank(appColumns.getPlViewType())&&(!appColumns.getPlViewType().equals(old.getPlViewType()))){
        	return true;
        }
        //前台显示
        if(null!=appColumns.getStatus()&&(!appColumns.getStatus().equals(old.getStatus()))){
        	return true;
        }
        //栏目类型
        if(null!=appColumns.getColumnsType()&&(!appColumns.getColumnsType().equals(old.getColumnsType()))){
        	return true;
        }
        //外链类
        if(null!=appColumns.getColumnsType()&&AppColumns.COLUMNSTYPE2.equals(appColumns.getColumnsType())){
        	if(StringUtils.isNotBlank(appColumns.getColumnsUrl())&&!appColumns.getColumnsUrl().equals(old.getColumnsUrl())){
        		return true;
        	}
        }else{
        	//允许评论
        	if(null!=appColumns.getCommentType()&&!appColumns.getCommentType().equals(old.getCommentType())){
        		return true;
        	}
        	//需审核
        	if(null!=appColumns.getValidateType()&&!appColumns.getValidateType().equals(old.getValidateType())){
        		return true;
        	}
        	//列表显示日期
        	if(null!=appColumns.getViewDate()&&!appColumns.getViewDate().equals(old.getViewDate())){
        		return true;
        	}
        	//显示浏览数
        	if(null!=appColumns.getViewCountType()&&!appColumns.getViewCountType().equals(old.getViewCountType())){
        		return true;
        	}
        	//显示评论数
        	if(null!=appColumns.getViewReplyCount()&&!appColumns.getViewReplyCount().equals(old.getViewReplyCount())){
        		return true;
        	}
        	//模板
        	if(StringUtils.isNotBlank(appColumns.getTempletCode())&&!appColumns.getTempletCode().equals(old.getTempletCode())){
        		return true;
        	}
        }
        return false;
	}
	
	/**
     * 修改状态
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月2日下午4:10:1
     */
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	@Override
	public void updateStatus(AppColumns appColumns) {
	    appColumns = this.get(appColumns.getId());
	    if(null!=appColumns){
	        // 修改版本
	        Integer versionNo = this.getEntityDao().findVersionNoByAppId(appColumns.getAppId());
	        versionNo = versionNo == null ? 1 : (versionNo+1);
	        // 删除当前节点（并且修改版本号）
	        this.getEntityDao().updateStatus(appColumns.getId(), AppColumns.STATUS99, appColumns.getUpdatorId(), appColumns.getUpdator(), new Date(),versionNo);
	        // 修改父节点是否为叶子节点
	        List<AppColumns> columnsList = this.getEntityDao().findByPid(appColumns.getParentId(),AppColumns.STATUS99);
	        if(null==columnsList||columnsList.size()<1){
                this.getEntityDao().changeLeafType(appColumns.getParentId(), AppColumns.LEAFTYPE1);
	        }
	    }
	}
	
	/**
	 * 修改排序
	 * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月3日下午1:10:1
     */
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds) {
	    //修改版本号
	    if(null!=ids&&ids.length>0){
	        AppColumns column = this.getEntityDao().get(ids[0]);
	        Integer versionNo = this.getEntityDao().findVersionNoByAppId(column.getAppId());
	        versionNo = null==versionNo ? 1 : versionNo+1;
	        this.getEntityDao().changeVersionNo(column.getId(), versionNo);
	    }
	    //修改排序
        for(int j=0; j<ids.length; j++){
            super.getEntityDao().sort(ids[j], sortNums[j], parentIds[j]);
        }
        //修改是否叶子节点
        for(Long id : ids){
            //查询是否有子节点
            List<AppColumns> columsList = this.getEntityDao().findByPid(id,AppColumns.STATUS99);
            if(null!=columsList&&columsList.size()>0){
                //修改为非叶子节点
            }else{
                //修改为叶子节点
                this.getEntityDao().changeLeafType(id, AppColumns.LEAFTYPE1);
            }
        }
    }

	/**
     * 查询栏目列表
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月16日下午1:51:13
     */
    @Override
    public List<AppColumnsDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return this.getEntityDao().getList(conditions, orderMap);
    }
    
    /**
     * 获取栏目列表
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月25日下午5:51:56
     */
    private List<GetColumnsData> getByAppId(Long appId){
        Map<String, Object> searchMap = new TreeMap<String, Object>();
        searchMap.put("EQ_appId", appId);
        //缓存中只存显示状态的数据
        searchMap.put("EQ_status", AppColumns.STATUS3);
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("sortNo", true);
        sortMap.put("id", true);
        return this.getEntityDao().getByAppId(searchMap, sortMap);
    }

    /**
     * 保存栏目缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月25日下午5:51:56
     */
    @Override
    public List<GetColumnsData> saveCache(Long appId) {
        String key = CacheConstant.APPCACHE+appId;
        List<GetColumnsData> listColumns = getByAppId(appId);
        abstractRedisClient.del(key);
        abstractRedisClient.set(key, listColumns);
        return listColumns ;
    }
    
    /**
     * 获取栏目缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月25日下午5:51:56
     */
    @Override
    public List<GetColumnsData> getCache(Long appId) {
        String key = CacheConstant.APPCACHE+appId;
        List<GetColumnsData> obj = abstractRedisClient.getList(key, GetColumnsData.class);
        if(null!=obj){
            return obj;
        }else{
            return saveCache(appId);
        }
    }
}