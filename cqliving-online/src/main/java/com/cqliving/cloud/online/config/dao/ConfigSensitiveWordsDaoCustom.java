package com.cqliving.cloud.online.config.dao;

import java.util.Map;

import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface ConfigSensitiveWordsDaoCustom {
    
    /**
     * 通过id查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月6日下午4:18:44
     */
    public ConfigSensitiveWordsDto getById(Long id);
    
    /**
     * Title:分页查询
     * @author huxiaoping 2016年7月6日下午4:18:44
     * @param map
     * @param sortMap
     * @return
     */
    public PageInfo<ConfigSensitiveWordsDto> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
}
