package com.cqliving.cloud.online.actcustom.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.tool.common.Response;

/**
 * 用户自定义投票活动表 Service
 * Date: 2017-01-03 10:31:16
 * @author Code Generator
 */
public interface UserActCustomVoteService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActCustomVote>> queryForPage(PageInfo<UserActCustomVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActCustomVote> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActCustomVote domain);
	/** @author Code Generator *****end*****/
	public Response<Void> addActeVote(String actQrcodeCode,String token, String sessionId, UserActCustomVote userActCustomVote);
	
}
