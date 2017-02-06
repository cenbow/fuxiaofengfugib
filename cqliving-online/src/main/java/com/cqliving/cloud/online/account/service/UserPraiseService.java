package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.PraisesData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * user_用户点赞表 Service
 * Date: 2016-04-29 16:28:56
 * @author Code Generator
 */
public interface UserPraiseService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserPraise>> queryForPage(PageInfo<UserPraise> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserPraise> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(UserPraise domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title: 获取我收到的赞
	 * @author Tangtao on 2016年5月2日
	 * @param appId
	 * @param token
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<PraisesData>> getMyPraisePage(Long appId, String token, Long lastId);
	
	Response<UserPraise> saveUserPraise(UserPraise userPraise);
	
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
	Response<Boolean> isPraised(Long appId, String sessionId, String token, Long sourceId, Byte sourceType);
	
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
	Response<Boolean> praise(Long appId, String sessionId, String token, Long userId, String title, Byte operateType, Long sourceId, Byte sourceType, Byte type);
}
