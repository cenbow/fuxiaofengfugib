package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动集合表，一个活动包含 Service
 * Date: 2016-06-07 09:21:44
 * @author Code Generator
 */
public interface ActInfoListService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActInfoList>> queryForPage(PageInfo<ActInfoList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActInfoList> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(String updator,Long updateUserId,Long... id);
	public Response<Void> updateStatus(Byte status,String updator,Long updateUserId,Long... ids);
	public Response<Void> save(ActInfoList domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取活动列表</p>
	 * @author Tangtao on 2016年6月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param showType 
	 * @param rangeType 
	 * @param lastId
	 * @param lastSortNo
	 * @param lastStartTime
	 * @param isRecommend
	 * @return
	 */
	Response<CommonListResult<ActInfoData>> getScrollPage(Long appId, String sessionId, String token, Byte rangeType, Byte showType, Long lastId, Integer lastSortNo, String lastStartTime, Byte isRecommend);
	
}