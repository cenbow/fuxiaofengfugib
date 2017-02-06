package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.framework.common.service.EntityService;

/**
 * 分享历史纪录表 Manager
 * Date: 2016-04-29 16:28:57
 * @author Code Generator
 */
public interface UserShareHistoryManager extends EntityService<UserShareHistory> {

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
	boolean add(Long appId, String sessionId, String token, Byte platform, Long sourceId, Byte sourceType, String place, String lat, String lng);

	//判断是否可以分享投票
	public void canShareVote(Long voteId, Long appId, String sessionCode, String token);
	
	public void share(UserShareHistory userShareHistory,String token);
}