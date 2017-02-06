package com.cqliving.cloud.online.account.manager;

import java.util.List;

import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.framework.common.service.EntityService;

/**
 * 短信模版表 Manager
 * Date: 2016-05-18 20:40:17
 * @author Code Generator
 */
public interface SmsTemplateManager extends EntityService<SmsTemplate> {
    
    /**
     * <p>Description: 获取短信模板通过appId</p>
     * @author huxiaoping on 2016年6月1日
     * @param appId
     * @return
     */
    public List<SmsTemplate> getByAppId(Long appId);
}
