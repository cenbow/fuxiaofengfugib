package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户参与活动表 Service
 * Date: 2016-06-07 09:29:35
 * @author Code Generator
 */
public interface UserActListService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserActList>> queryForPage(PageInfo<UserActList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserActList> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserActList domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月24日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param actInfoId
	 * @param actInfoListId
	 * @param optionIds
	 * @param optionValues
	 * @param inputIds
	 * @param inputValues
	 * @param ip
	 * @return
	 */
	Response<Long> save(Long appId, String sessionId, String token, Long actInfoId, Long actInfoListId, Long[] optionIds, String[] optionValues, Long[] inputIds, String[] inputValues, String ip);
	
	public Response<Long> findTotalByInfoListId(Long actInfoListId);
}
