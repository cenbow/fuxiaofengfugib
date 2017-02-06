package com.cqliving.cloud.online.info.dao;

import java.util.Map;

import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface InfoLableDaoCustom {
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public PageInfo<InfoLableDto> queryInfoLabelDtoPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
    
    /**
     * 查询单条记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    public InfoLableDto getById(Long id);
}