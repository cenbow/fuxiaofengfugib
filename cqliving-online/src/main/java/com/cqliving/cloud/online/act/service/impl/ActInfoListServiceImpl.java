package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.manager.ActInfoListManager;
import com.cqliving.cloud.online.act.service.ActInfoListService;
import com.cqliving.cloud.online.interfacc.dto.ActInfoData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actInfoListService")
@ServiceHandleMapping(managerClass = ActInfoListManager.class)
public class ActInfoListServiceImpl implements ActInfoListService {

	private static final Logger logger = LoggerFactory.getLogger(ActInfoListServiceImpl.class);
	
	@Autowired
	private ActInfoListManager actInfoListManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动集合表，一个活动包含失败")})
	public Response<PageInfo<ActInfoList>> queryForPage(PageInfo<ActInfoList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动集合表，一个活动包含失败")})
	public Response<ActInfoList> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动集合表，一个活动包含失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动集合表，一个活动包含失败")})
	public Response<Void> deleteLogic(String updator,Long updateUserId,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,String updator,Long updateUserId,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动集合表，一个活动包含失败")})
	public Response<Void> save(ActInfoList actInfoList) {
		return null;
	}

	@Override
	public Response<CommonListResult<ActInfoData>> getScrollPage(Long appId, String sessionId, String token, Byte rangeType, Byte showType, Long lastId, Integer lastSortNo, String lastStartTime, Byte isRecommend) {
		Response<CommonListResult<ActInfoData>> response = Response.newInstance();
		try {
			CommonListResult<ActInfoData> data = actInfoListManager.getScrollPage(appId, sessionId, token, rangeType, showType, lastId, lastSortNo, lastStartTime, isRecommend);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取活动列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取活动列表失败");
		} catch (Exception e) {
			logger.error("获取活动列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取活动列表失败");
		}
		return response;
	}
	
}