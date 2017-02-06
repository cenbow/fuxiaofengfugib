package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserPraise;
import com.cqliving.cloud.online.account.manager.UserPraiseManager;
import com.cqliving.cloud.online.account.service.UserPraiseService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.PraisesData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userPraiseService")
@ServiceHandleMapping(managerClass = UserPraiseManager.class)
public class UserPraiseServiceImpl implements UserPraiseService {

	private static final Logger logger = LoggerFactory.getLogger(UserPraiseServiceImpl.class);
	
	@Autowired
	private UserPraiseManager userPraiseManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询user_用户点赞表失败")})
	public Response<PageInfo<UserPraise>> queryForPage(PageInfo<UserPraise> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询user_用户点赞表失败")})
	public Response<UserPraise> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除user_用户点赞表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存user_用户点赞表失败")})
	public Response<Void> save(UserPraise userPraise) {
		return null;
	}

	@Override
	public Response<CommonListResult<PraisesData>> getMyPraisePage(Long appId, String token, Long lastId) {
		Response<CommonListResult<PraisesData>> response = Response.newInstance();
		try {
			CommonListResult<PraisesData> data = userPraiseManager.getMyPraisePage(appId, token, lastId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取收到的赞失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取收到的赞失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取收到的赞失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserPraiseService#saveUserPraise(com.cqliving.cloud.online.account.domain.UserPraise)
	 */
	@Override
	@ServiceMethodHandle
	public Response<UserPraise> saveUserPraise(UserPraise userPraise) {
		return null;
	}

	@Override
	public Response<Boolean> isPraised(Long appId, String sessionId, String token, Long sourceId, Byte sourceType) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = userPraiseManager.isPraised(appId, sessionId, token, sourceId, sourceType);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取是否已点赞失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("获取是否已点赞失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取是否已点赞失败");
			response.setData(false);
		}
		return response;
	}

	@Override
	public Response<Boolean> praise(Long appId, String sessionId, String token, Long userId, String title, Byte operateType, Long sourceId, Byte sourceType, Byte type) {
		Response<Boolean> response = Response.newInstance();
		try {
			boolean data = userPraiseManager.praise(appId, sessionId, token, userId, title, operateType, sourceId, sourceType, type);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("点赞/点踩失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
			response.setData(false);
		} catch (Exception e) {
			logger.error("点赞/点踩失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("点赞/点踩失败");
			response.setData(false);
		}
		return response;
	}
	
}
