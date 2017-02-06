package com.cqliving.jpush.service;

import com.cqliving.tool.common.Response;

import cn.jpush.api.report.ReceivedsResult;

/**
 * Title: 报告 Service
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月13日
 */
public interface ReportService {
	
	/**
	 * <p>Description: 推送送达统计</p>
	 * @author Tangtao on 2016年4月13日
	 * @param masterSecret 应用 Master Secret
	 * @param appKey 应用 AppKey
	 * @param msgIdArray  推送API返回的 msg_id 列表，最多支持100个msg_id
	 * @return
	 */
	Response<ReceivedsResult> getReceiveds(String masterSecret, String appKey, String... msgIdArray);
	
}