package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserShareHistory;
import com.cqliving.cloud.online.account.manager.UserShareHistoryManager;
import com.cqliving.cloud.online.account.service.UserShareHistoryService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userShareHistoryService")
@ServiceHandleMapping(managerClass = UserShareHistoryManager.class)
public class UserShareHistoryServiceImpl implements UserShareHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(UserShareHistoryServiceImpl.class);
	
	@Autowired
	private UserShareHistoryManager userShareHistoryManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询分享历史纪录表失败")})
	public Response<PageInfo<UserShareHistory>> queryForPage(PageInfo<UserShareHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询分享历史纪录表失败")})
	public Response<UserShareHistory> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除分享历史纪录表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存分享历史纪录表失败")})
	public Response<Void> save(UserShareHistory userShareHistory) {
		return null;
	}

	@Override
	public Response<Boolean> add(Long appId, String sessionId, String token, Byte platform, Long sourceId, Byte sourceType, String place, String lat, String lng) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = userShareHistoryManager.add(appId, sessionId, token, platform, sourceId, sourceType, place, lat, lng);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("保存分享成功日志失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("保存分享成功日志失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("保存分享成功日志失败");
			response.setData(false);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserShareHistoryService#canShareVote(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> canShareVote(Long voteId, Long appId, String sessionCode, String token) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserShareHistoryService#share(com.cqliving.cloud.online.account.domain.UserShareHistory)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> share(UserShareHistory userShareHistory,String token) {
		return null;
	}
}
