package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动信息收集选项表 Service
 * Date: 2016-06-07 09:18:50
 * @author Code Generator
 */
public interface ActCollectOptionService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActCollectOption>> queryForPage(PageInfo<ActCollectOption> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActCollectOption> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActCollectOption domain);
	/** @author Code Generator *****end*****/
	
}
