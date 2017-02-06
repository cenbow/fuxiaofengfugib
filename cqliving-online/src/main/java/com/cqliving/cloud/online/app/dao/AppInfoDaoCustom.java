package com.cqliving.cloud.online.app.dao;

import java.util.Map;

import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;



/**
 * 
 * Title:
 * <p>Description:</p>
 * Copyright (c) xhl
 * @author huxiaoping
 */
public interface AppInfoDaoCustom {
    /**
     * Title:分页查询区县APP信息
     * @author huxiaoping on 2016年4月20日
     * @param map
     * @param sortMap
     * @return
     */
    public PageInfo<AppInfoDto> queryPage(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 查询某用户对应数据权限表的App
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
    public PageInfo<AppInfoDto> queryPageByUser(PageInfo<AppInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
}
