package com.cqliving.cloud.online.county.manager;

import java.util.List;

import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.framework.common.service.EntityService;

/**
 * 区县表 Manager
 * Date: 2017-01-05 10:11:02
 * @author Code Generator
 */
public interface CountyManager extends EntityService<County> {
    
    /**
     * 获取区县信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午1:33:23
     */
    public List<County>getList(String token, String sessionId);
}
