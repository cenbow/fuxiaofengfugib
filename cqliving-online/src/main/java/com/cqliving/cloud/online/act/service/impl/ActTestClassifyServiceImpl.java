package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.manager.ActTestClassifyManager;
import com.cqliving.cloud.online.act.service.ActTestClassifyService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTestClassifyService")
@ServiceHandleMapping(managerClass = ActTestClassifyManager.class)
public class ActTestClassifyServiceImpl implements ActTestClassifyService {

	private static final Logger logger = LoggerFactory.getLogger(ActTestClassifyServiceImpl.class);
	
	@Autowired
	private ActTestClassifyManager actTestClassifyManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动答题分类表失败")})
	public Response<PageInfo<ActTestClassify>> queryForPage(PageInfo<ActTestClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动答题分类表失败")})
	public Response<ActTestClassify> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动答题分类表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动答题分类表失败")})
	public Response<Void> save(ActTestClassify actTestClassify) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="update",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改活动答题分类表失败")})
	public Response<Void> update(ActTestClassify actTestClassify) {
		return null;
	}

	@Override
	public Response<List<ActTestClassify>> getByActTest(Long actInfoListId, Long actTestId) {
		Response<List<ActTestClassify>> rs = Response.newInstance();
		try {
			rs.setData(actTestClassifyManager.getByActTest(actInfoListId, actTestId));
		} catch (Exception e) {
			logger.error("获得活动答题分类集合失败:" + e.getMessage());
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获得活动答题分类集合失败");
		}
		return rs;
	}
}
