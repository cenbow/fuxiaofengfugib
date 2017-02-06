package com.cqliving.cloud.online.app.dao;

import java.util.Map;

import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface AppVersionDaoCustom {
    /**
     * 分页查询App版本
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月26日下午5:03:48
     */
    public PageInfo<AppVersionDto> queryByPage(PageInfo<AppVersionDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月28日上午9:09:05
     */
    public AppVersionDto getById(Long id);
}