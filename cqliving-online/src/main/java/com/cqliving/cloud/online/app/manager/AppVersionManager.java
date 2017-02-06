package com.cqliving.cloud.online.app.manager;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端版本表 Manager
 * Date: 2016-04-26 16:28:10
 * @author Code Generator
 */
public interface AppVersionManager extends EntityService<AppVersion> {
    
    /**
     * 分页查询版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月26日下午5:20:31
     */
    public PageInfo<AppVersionDto> queryByPage(PageInfo<AppVersionDto> pageInfo, Map<String, Object> conditions,Map<String, Boolean> orders);
    
    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月28日上午9:09:05
     */
    public AppVersionDto getById(Long id);
    
    /**
     * 保存客户端版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    public void saveVersion(AppVersion version);
    
    /**
     * 修改客户端版本信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    public void updateVersion(AppVersion version);
    
    /**
     * 逻辑删除
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月27日上午11:48:20
     */
    public void deleteById(Long id);

    /**
     * Title: 获取客户端最新版本信息
     * @author Tangtao on 2016年4月30日
     * @param appId
     * @param type
     * @return
     */
    AppVersion getLatest(Long appId, Integer type);
    
    /**
     * 逻辑删除
     * @param ids
     * @return
     */
    public int deleteLogic(Long[] ids);
    
    /**
     * 修改状态
     * @param status 状态
     * @param ids
     * @return
     */
    public int updateStatus(Byte status,Long... ids);
    
}