package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.UserActTestClassifyHistory;
import com.cqliving.cloud.online.act.manager.UserActTestClassifyHistoryManager;
import com.cqliving.cloud.online.act.service.UserActTestClassifyHistoryService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActTestClassifyHistoryService")
@ServiceHandleMapping(managerClass = UserActTestClassifyHistoryManager.class)
public class UserActTestClassifyHistoryServiceImpl implements UserActTestClassifyHistoryService {

	//private static final Logger logger = LoggerFactory.getLogger(UserActTestClassifyHistoryServiceImpl.class);
	
	@Autowired
	private UserActTestClassifyHistoryManager userActTestClassifyHistoryManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。失败")})
	public Response<PageInfo<UserActTestClassifyHistory>> queryForPage(PageInfo<UserActTestClassifyHistory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。失败")})
	public Response<UserActTestClassifyHistory> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户答题分类历史表。一个用户对应一个分类测试题可以有多条记录。失败")})
	public Response<Void> save(UserActTestClassifyHistory userActTestClassifyHistory) {
		return null;
	}
}
