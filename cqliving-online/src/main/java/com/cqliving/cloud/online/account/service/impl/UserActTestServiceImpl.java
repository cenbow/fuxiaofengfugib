package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserActTest;
import com.cqliving.cloud.online.account.manager.UserActTestManager;
import com.cqliving.cloud.online.account.service.UserActTestService;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActTestService")
@ServiceHandleMapping(managerClass = UserActTestManager.class)
public class UserActTestServiceImpl implements UserActTestService {

	private static final Logger logger = LoggerFactory.getLogger(UserActTestServiceImpl.class);
	
	@Autowired
	private UserActTestManager userActTestManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户答题表失败")})
	public Response<PageInfo<UserActTest>> queryForPage(PageInfo<UserActTest> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户答题表失败")})
	public Response<UserActTest> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户答题表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户答题表失败")})
	public Response<Void> save(UserActTest userActTest) {
		return null;
	}

	@Override
	public Response<UserActTestClassify> save(Long appId, String sessionId, String token, Long actTestClassifyId, Long startTime, Integer isFinish, Long[] questionIds, String[] answerIds, Long[] inputQuestionIds, String[] inputAnswerValues) {
		Response<UserActTestClassify> res = Response.newInstance();
		try {
			res.setData(userActTestManager.save(appId, sessionId, token, actTestClassifyId, startTime, isFinish, questionIds, answerIds, inputQuestionIds, inputAnswerValues));
		} catch (BusinessException e) {
			logger.error("用户活动答题失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户活动答题失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("用户活动答题失败");
		}
		return res;
	}
}
