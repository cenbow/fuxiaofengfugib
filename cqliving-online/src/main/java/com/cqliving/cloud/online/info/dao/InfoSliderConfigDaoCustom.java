package com.cqliving.cloud.online.info.dao;

import java.util.List;

import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;


public interface InfoSliderConfigDaoCustom {
    
    /**
     * 通过appId查询轮播图配置列表信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午2:08:49
     */
    public List<InfoSliderConfigDto> getListByAppId(Long appId);
}
