package com.cqliving.cloud.online.config.dao;

import java.util.Map;

import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 *  区域表 JPA Dao自定义接口
 * Date: 2016-07-29 11:54:13
 * @author huxiaoping
 */
public interface ConfigRegionDaoCustom{
    
    /**
     * 分页商情区域信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public PageInfo<ConfigRegionDto> queryByPage(PageInfo<ConfigRegionDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
}
