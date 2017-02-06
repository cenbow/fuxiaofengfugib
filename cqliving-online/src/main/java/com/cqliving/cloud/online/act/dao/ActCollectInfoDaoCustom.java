package com.cqliving.cloud.online.act.dao;

import java.util.Map;

import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface ActCollectInfoDaoCustom {
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public PageInfo<ActCollectInfoDto> queryPage(PageInfo<ActCollectInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public ActCollectInfoDto findById(Long id);
}
