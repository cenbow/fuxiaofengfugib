package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.manager.UserActListManager;
import com.cqliving.cloud.online.account.service.UserActListService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActListService")
@ServiceHandleMapping(managerClass = UserActListManager.class)
public class UserActListServiceImpl implements UserActListService {

	private static final Logger logger = LoggerFactory.getLogger(UserActListServiceImpl.class);
	
	@Autowired
	private UserActListManager userActListManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户参与活动表失败")})
	public Response<PageInfo<UserActList>> queryForPage(PageInfo<UserActList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户参与活动表失败")})
	public Response<UserActList> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户参与活动表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户参与活动表失败")})
	public Response<Void> save(UserActList userActList) {
		return null;
	}

	@Override
	public Response<Long> save(Long appId, String sessionId, String token, Long actInfoId, Long actInfoListId, Long[] optionIds, String[] optionValues, Long[] inputIds, String[] inputValues, String ip) {
		Response<Long> res = Response.newInstance();
		try {
			res.setData(userActListManager.save(appId, sessionId, token, actInfoId, actInfoListId, optionIds, optionValues, inputIds, inputValues, ip));
		} catch (BusinessException e) {
			logger.debug("用户活动开始答题失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.debug("用户活动开始答题失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserActListService#findTotalByInfoListId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Long> findTotalByInfoListId(Long actInfoListId) {
		return null;
	}
}
