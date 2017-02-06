package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动集合表，一个活动包含 Manager
 * Date: 2016-06-07 09:21:44
 * @author Code Generator
 */
public interface ActInfoListManager extends EntityService<ActInfoList> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id,String updator,Long updateUserId);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,String updator,Long updateUserId,Long... ids);

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
	CommonListResult<ActInfoData> getScrollPage(Long appId, String sessionId, String token, Byte rangeType, Byte showType, Long lastId, Integer lastSortNo, String lastStartTime, Byte isRecommend);
	
}