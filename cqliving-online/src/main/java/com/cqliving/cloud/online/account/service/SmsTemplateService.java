package com.cqliving.cloud.online.account.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.account.domain.SmsTemplate;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 短信模版表 Service
 * Date: 2016-05-18 20:40:17
 * @author Code Generator
 */
public interface SmsTemplateService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SmsTemplate>> queryForPage(PageInfo<SmsTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SmsTemplate> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(SmsTemplate domain);
	/** @author Code Generator *****end*****/
	
	/**
     * <p>Description: 获取短信模板通过appId</p>
     * @author huxiaoping on 2016年6月1日
     * @param appId
     * @return
     */
    public Response<List<SmsTemplate>> getByAppId(Long appId);
	
}
