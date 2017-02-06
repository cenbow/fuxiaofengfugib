package com.cqliving.cloud.online.actcustom.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;
import com.cqliving.cloud.online.actcustom.manager.UserActCustomVoteManager;
import com.cqliving.cloud.online.actcustom.service.UserActCustomVoteService;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("userActCustomVoteService")
@ServiceHandleMapping(managerClass = UserActCustomVoteManager.class)
public class UserActCustomVoteServiceImpl implements UserActCustomVoteService {

	private static final Logger logger = LoggerFactory.getLogger(UserActCustomVoteServiceImpl.class);
	
	@Autowired
	private UserActCustomVoteManager userActCustomVoteManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户自定义投票活动表失败")})
	public Response<PageInfo<UserActCustomVote>> queryForPage(PageInfo<UserActCustomVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户自定义投票活动表失败")})
	public Response<UserActCustomVote> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户自定义投票活动表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户自定义投票活动表失败")})
	public Response<Void> save(UserActCustomVote userActCustomVote) {
		return null;
	}

	@Override
	public Response<Void> addActeVote(String actQrcodeCode,String token, String sessionId, UserActCustomVote userActCustomVote) {
		Response<Void> response = Response.newInstance();
		try {
			Byte status =userActCustomVoteManager.addActeVote(actQrcodeCode,token,sessionId,userActCustomVote);
			response.setMessage(ShoppingUserAddress.STATUS3.equals(status) ? "投票成功" : "投票失败");
			response.setCode(ShoppingUserAddress.STATUS3.equals(status) ? 0 : 1);
		} catch (BusinessException e) {
			logger.error("投票失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("投票失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("投票失败");
		}
		return response;
	}
}
