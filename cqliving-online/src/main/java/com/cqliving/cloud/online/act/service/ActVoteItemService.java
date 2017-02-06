package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动投票分类选项表 Service
 * Date: 2016-06-07 09:23:54
 * @author Code Generator
 */
public interface ActVoteItemService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActVoteItem>> queryForPage(PageInfo<ActVoteItem> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActVoteItem> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ActVoteItem domain);
	/** @author Code Generator *****end*****/
	
}
