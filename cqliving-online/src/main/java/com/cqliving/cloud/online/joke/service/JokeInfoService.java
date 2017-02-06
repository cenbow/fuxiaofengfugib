package com.cqliving.cloud.online.joke.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.JokeInfoData;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.tool.common.Response;

/**
 * 段子表 Service
 * Date: 2016-06-28 11:18:14
 * @author Code Generator
 */
public interface JokeInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<JokeInfo>> queryForPage(PageInfo<JokeInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<JokeInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(JokeInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 批量发布</p>
	 * @author Tangtao on 2016年6月28日
	 * @param asList
	 * @return
	 */
	Response<Void> publishBatch(List<Long> asList);
	
	/**
	 * <p>Description: 批量下线</p>
	 * @author Tangtao on 2016年6月28日
	 * @param asList
	 * @return
	 */
	Response<Void> offlineBatch(List<Long> asList);
	
	/**
	 * <p>Description: 获取段子列表/详情</p>
	 * @author Tangtao on 2016年6月29日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @param lastId
	 * @param lastOnlineTime
	 * @return
	 */
	Response<CommonListResult<JokeInfoData>> getJokeInfo(Long appId, String sessionId, String token, Long id, Long lastId, String lastOnlineTime);
	
}
