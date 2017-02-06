package com.cqliving.cloud.online.sms.service;

import java.util.Map;

import com.cqliving.cloud.online.sms.domain.SmsLog;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 发送短信表 Service
 * Date: 2016-04-29 16:40:00
 * @author Code Generator
 */
public interface SmsLogService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SmsLog>> queryForPage(PageInfo<SmsLog> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SmsLog> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SmsLog domain);
	/** @author Code Generator *****end*****/
	
}
