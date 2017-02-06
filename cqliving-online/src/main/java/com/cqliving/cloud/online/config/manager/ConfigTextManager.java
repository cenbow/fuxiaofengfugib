package com.cqliving.cloud.online.config.manager;

import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.framework.common.service.EntityService;

/**
 * 联系我们、区情介绍、反馈回复 Manager
 * Date: 2016-07-13 17:16:59
 * @author Code Generator
 */
public interface ConfigTextManager extends EntityService<ConfigText> {
    
    /**
     * 通过appId和type查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月14日下午5:08:54
     */
    public ConfigText getByAppId(Long appId,Byte type);
    
}
