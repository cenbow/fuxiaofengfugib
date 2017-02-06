package com.cqliving.cloud.online.sms.service;

import java.util.Map;

import com.cqliving.cloud.online.sms.domain.SmsStatus;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 状态报告表 Service
 * Date: 2016-04-29 16:40:01
 * @author Code Generator
 */
public interface SmsStatusService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<SmsStatus>> queryForPage(PageInfo<SmsStatus> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<SmsStatus> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(SmsStatus domain);
	/** @author Code Generator *****end*****/
	
}
