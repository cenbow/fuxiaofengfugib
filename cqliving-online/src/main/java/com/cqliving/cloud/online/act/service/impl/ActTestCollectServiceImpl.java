package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.manager.ActTestCollectManager;
import com.cqliving.cloud.online.act.service.ActTestCollectService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actTestCollectService")
@ServiceHandleMapping(managerClass = ActTestCollectManager.class)
public class ActTestCollectServiceImpl implements ActTestCollectService {

	private static final Logger logger = LoggerFactory.getLogger(ActTestCollectServiceImpl.class);
	
	@Autowired
	private ActTestCollectManager actTestCollectManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动答题表失败")})
	public Response<PageInfo<ActTestCollect>> queryForPage(PageInfo<ActTestCollect> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动答题表失败")})
	public Response<ActTestCollect> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动答题表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动答题表失败")})
	public Response<Void> save(ActTestCollect actTestCollect) {
		return null;
	}

	@Override
	public Response<List<ActTestCollect>> getByActTestId(Long actTestId) {
		Response<List<ActTestCollect>> rs = Response.newInstance();
		try {
			rs.setData(actTestCollectManager.getByActTestId(actTestId));
		} catch (Exception e) {
			logger.error("根据答题id获取收集信息集合失败:" + e.getMessage());
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("根据答题id获取收集信息集合失败");
		}
		return rs;
	}
}
