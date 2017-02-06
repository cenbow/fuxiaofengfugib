package com.cqliving.cloud.online.county.dao;

import java.util.Map;

import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface CountyHousesDaoCustom {
    /**
     * 滚动分页获取区县楼盘信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午2:29:21
     */
    public ScrollPage<CountyHouses> getScrollPage(ScrollPage<CountyHouses> page,Map<String, Object> conditions);
}
