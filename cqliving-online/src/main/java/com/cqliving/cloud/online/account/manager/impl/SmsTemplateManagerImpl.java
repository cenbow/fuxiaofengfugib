package com.cqliving.cloud.online.account.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.SmsTemplateDao;
import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.cloud.online.account.manager.SmsTemplateManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("smsTemplateManager")
public class SmsTemplateManagerImpl extends EntityServiceImpl<SmsTemplate, SmsTemplateDao> implements SmsTemplateManager {
    /**
     * <p>Description: 获取短信模板通过appId</p>
     * @author huxiaoping on 2016年6月1日
     * @param appId
     * @return
     */
    public List<SmsTemplate> getByAppId(Long appId){
        return this.getEntityDao().getByAppId(appId);
    }
}
