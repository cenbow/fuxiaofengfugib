package com.cqliving.cloud.online.county.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.county.dao.CountyDao;
import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.cloud.online.county.manager.CountyManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("countyManager")
public class CountyManagerImpl extends EntityServiceImpl<County, CountyDao> implements CountyManager {

    /**
     * 获取区县信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午1:33:23
     */
    @Override
    public List<County> getList(String token, String sessionId) {
        return this.getEntityDao().getList(County.STATUS3);
    }
}
