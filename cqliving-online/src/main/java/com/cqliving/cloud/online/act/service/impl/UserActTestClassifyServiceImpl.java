package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;
import com.cqliving.cloud.online.act.manager.UserActTestClassifyManager;
import com.cqliving.cloud.online.act.service.UserActTestClassifyService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActTestClassifyService")
@ServiceHandleMapping(managerClass = UserActTestClassifyManager.class)
public class UserActTestClassifyServiceImpl implements UserActTestClassifyService {

	//private static final Logger logger = LoggerFactory.getLogger(UserActTestClassifyServiceImpl.class);
	
	@Autowired
	private UserActTestClassifyManager userActTestClassifyManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户答题分类表，一个用户对应一个分类测试题只有一条记录。失败")})
	public Response<PageInfo<UserActTestClassify>> queryForPage(PageInfo<UserActTestClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户答题分类表，一个用户对应一个分类测试题只有一条记录。失败")})
	public Response<UserActTestClassify> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户答题分类表，一个用户对应一个分类测试题只有一条记录。失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户答题分类表，一个用户对应一个分类测试题只有一条记录。失败")})
	public Response<Void> save(UserActTestClassify userActTestClassify) {
		return null;
	}
}
