package com.cqliving.cloud.online.county.manager;

import java.util.Map;

import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 区县楼盘表 Manager
 * Date: 2017-01-05 10:11:11
 * @author Code Generator
 */
public interface CountyHousesManager extends EntityService<CountyHouses> {
    /**
     * 获取区县和楼盘信息并保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:15:54
     */
    public void getAndSaveTask();
    
    /**
     * 上线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:27:50
     */
    public void online();
    
    /**
     * 滚动分页获取区县楼盘信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午2:29:21
     */
    public ScrollPage<CountyHouses> getScrollPage(ScrollPage<CountyHouses> page, Map<String, Object> conditions, String sessionId, String token);
}
