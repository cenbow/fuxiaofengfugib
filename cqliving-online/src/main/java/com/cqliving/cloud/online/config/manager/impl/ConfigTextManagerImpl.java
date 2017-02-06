package com.cqliving.cloud.online.config.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.config.dao.ConfigTextDao;
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.manager.ConfigTextManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("configTextManager")
public class ConfigTextManagerImpl extends EntityServiceImpl<ConfigText, ConfigTextDao> implements ConfigTextManager {

    /**
     * 通过appId和type查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月14日下午5:08:54
     */
    @Override
    public ConfigText getByAppId(Long appId, Byte type) {
        List<ConfigText> configTextList = this.getEntityDao().getByAppId(appId, type);
        if(null!=configTextList&&configTextList.size()>0){
            return configTextList.get(0);
        }
        return null;
    }
}
