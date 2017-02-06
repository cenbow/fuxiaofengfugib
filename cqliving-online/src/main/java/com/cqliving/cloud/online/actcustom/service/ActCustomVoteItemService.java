package com.cqliving.cloud.online.actcustom.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.tool.common.Response;

/**
 * 自定义活动投票选项表 Service
 * Date: 2017-01-03 10:19:03
 * @author Code Generator
 */
public interface ActCustomVoteItemService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActCustomVoteItem>> queryForPage(PageInfo<ActCustomVoteItem> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActCustomVoteItem> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ActCustomVoteItem domain);
	/** @author Code Generator *****end*****/
	/**
	 * 投票项详情
	 * @param token
	 * @param sessionId
	 * @param actCustomVoteItemId
	 * @return
	 */
	public Response<ActCustomVoteItem> acteVoteDetail(String token, String sessionId,
			Long actCustomVoteItemId);
	/**
	 * 
	 * @param actQrcodeCode
	 * @param itemTitle
	 * @param lastId 
	 * @return
	 */
	public Response<CommonListResult<ActCustomVoteItemDto>> getColumnsByAcode(String actQrcodeCode, String itemTitle, Long lastId,Long ranking);
	/**
	 * 
	 * @param token
	 * @param sessionId
	 * @param actQrcodeCode
	 * @return
	 */
	public Response<ActCustomVoteDto> acteDetail(String token, String sessionId, String actQrcodeCode);
	
}
