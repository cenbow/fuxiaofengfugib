package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 分享历史纪录表 Service
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserShareHistoryService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserShareHistory>> queryForPage(PageInfo<UserShareHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserShareHistory> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserShareHistory domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 保存用户分享成功日志</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param platform
	 * @param sourceId
	 * @param sourceType
	 * @param place
	 * @param lat
	 * @param lng
	 * @return
	 */
	Response<Boolean> add(Long appId, String sessionId, String token, Byte platform, Long sourceId, Byte sourceType, String place, String lat, String lng);
	
	Response<Void> canShareVote(Long voteId,Long appId,String sessionCode,String token);
	
	Response<Void> share(UserShareHistory userShareHistory,String token);
}
