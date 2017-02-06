package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.cloud.online.act.manager.ActCollectOptionManager;
import com.cqliving.cloud.online.act.service.ActCollectOptionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actCollectOptionService")
@ServiceHandleMapping(managerClass = ActCollectOptionManager.class)
public class ActCollectOptionServiceImpl implements ActCollectOptionService {

	//private static final Logger logger = LoggerFactory.getLogger(ActCollectOptionServiceImpl.class);
	
	@Autowired
	private ActCollectOptionManager actCollectOptionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动信息收集选项表失败")})
	public Response<PageInfo<ActCollectOption>> queryForPage(PageInfo<ActCollectOption> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动信息收集选项表失败")})
	public Response<ActCollectOption> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动信息收集选项表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动信息收集选项表失败")})
	public Response<Void> save(ActCollectOption actCollectOption) {
		return null;
	}
}
