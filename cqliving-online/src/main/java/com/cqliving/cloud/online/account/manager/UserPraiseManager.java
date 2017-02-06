package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.PraisesData;
import com.cqliving.framework.common.service.EntityService;

/**
 * user_用户点赞表 Manager
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserPraiseManager extends EntityService<UserPraise> {

	/**
	 * Title: 获取我收到的赞
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param token
	 * @param lastId
	 * @return
	 */
	CommonListResult<PraisesData> getMyPraisePage(Long appId, String token, Long lastId);

	public UserPraise saveUserPraise(UserPraise userPraise);

	/**
	 * <p>Description: 是否已点赞</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	boolean isPraised(Long appId, String sessionId, String token, Long sourceId, Byte sourceType);

	/**
	 * <p>Description: 点赞/鄙视</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param userId
	 * @param title
	 * @param operateType 
	 * @param sourceId
	 * @param sourceType
	 * @param type 
	 * @return
	 */
	boolean praise(Long appId, String sessionId, String token, Long userId, String title, Byte operateType, Long sourceId, Byte sourceType, Byte type);
	
}
