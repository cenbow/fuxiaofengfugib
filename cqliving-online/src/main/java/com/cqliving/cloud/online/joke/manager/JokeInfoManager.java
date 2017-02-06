package com.cqliving.cloud.online.joke.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.JokeInfoData;
import com.cqliving.cloud.online.joke.domain.JokeInfo;

/**
 * 段子表 Manager
 * Date: 2016-06-28 11:18:14
 * @author Code Generator
 */
public interface JokeInfoManager extends EntityService<JokeInfo> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);

	/**
	 * <p>Description: 批量发布</p>
	 * @author Tangtao on 2016年6月28日
	 * @param idList
	 */
	void publishBatch(List<Long> idList);

	/**
	 * <p>Description: 批量下线</p>
	 * @author Tangtao on 2016年6月28日
	 * @param idList
	 */
	void offlineBatch(List<Long> idList);

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
	CommonListResult<JokeInfoData> getJokeInfo(Long appId, String sessionId, String token, Long id, Long lastId, String lastOnlineTime);
	
}