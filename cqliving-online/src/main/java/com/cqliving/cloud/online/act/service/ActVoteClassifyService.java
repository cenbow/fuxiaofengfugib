package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动投票分类表 Service
 * Date: 2016-06-07 09:23:15
 * @author Code Generator
 */
public interface ActVoteClassifyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActVoteClassify>> queryForPage(PageInfo<ActVoteClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActVoteClassify> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActVoteClassify domain);
	/** @author Code Generator *****end*****/
	public Response<ActVoteClassifyDto> findByClassifyId(Long classifyId);
}
