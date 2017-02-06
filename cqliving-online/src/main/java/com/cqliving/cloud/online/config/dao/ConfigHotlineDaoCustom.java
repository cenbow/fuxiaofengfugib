package com.cqliving.cloud.online.config.dao;

import java.util.Map;

import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * config_热线表 JPA Dao
 * Date: 2016-07-07 11:54:13
 * @author Code Generator
 */
public interface ConfigHotlineDaoCustom{
    /**
     * 分页查询热线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public PageInfo<ConfigHotlineDto> queryByPage(PageInfo<ConfigHotlineDto> pageInfo,Map<String, Object> conditions,Map<String, Boolean> orders);
    
    /**
     * 查询详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月7日
     */
    public ConfigHotlineDto getById(Long id);
}
