package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.manager.ActVoteClassifyManager;
import com.cqliving.cloud.online.act.service.ActVoteClassifyService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actVoteClassifyService")
@ServiceHandleMapping(managerClass = ActVoteClassifyManager.class)
public class ActVoteClassifyServiceImpl implements ActVoteClassifyService {

//	private static final Logger logger = LoggerFactory.getLogger(ActVoteClassifyServiceImpl.class);
	
	@Autowired
	private ActVoteClassifyManager actVoteClassifyManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动投票分类表失败")})
	public Response<PageInfo<ActVoteClassify>> queryForPage(PageInfo<ActVoteClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动投票分类表失败")})
	public Response<ActVoteClassify> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动投票分类表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动投票分类表失败")})
	public Response<Void> save(ActVoteClassify actVoteClassify) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActVoteClassifyService#findByClassifyId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ActVoteClassifyDto> findByClassifyId(Long classifyId) {
		return null;
	}
}
