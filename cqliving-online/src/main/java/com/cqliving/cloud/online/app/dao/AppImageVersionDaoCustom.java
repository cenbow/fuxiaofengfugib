package com.cqliving.cloud.online.app.dao;

import java.util.Map;

import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface AppImageVersionDaoCustom {
    
    /**
     * Title:分页查询
     * @author huxiaoping on 2016年5月4日
     * @param map
     * @param sortMap
     * @return
     */
    public PageInfo<AppImageVersionDto> queryPage(PageInfo<AppImageVersionDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 查询单个记录（包括所有图片）
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月4日下午5:13:06
     */
    public AppImageVersionDto queryById(Long id);
}
