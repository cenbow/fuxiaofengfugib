package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.account.domain.UserActList;

/**
 * 用户参与活动表 Manager
 * Date: 2016-06-07 09:29:35
 * @author Code Generator
 */
public interface UserActListManager extends EntityService<UserActList> {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月30日
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
	public Long save(Long appId, String sessionId, String token, Long actInfoId, Long actInfoListId, Long[] optionIds, String[] optionValues, Long[] inputIds, String[] inputValues, String ip);
	
	public Long findTotalByInfoListId(Long actInfoListId);
}
