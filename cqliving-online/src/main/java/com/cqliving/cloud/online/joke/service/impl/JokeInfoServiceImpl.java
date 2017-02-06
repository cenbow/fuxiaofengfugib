package com.cqliving.cloud.online.joke.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.JokeInfoData;
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.joke.manager.JokeInfoManager;
import com.cqliving.cloud.online.joke.service.JokeInfoService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("jokeInfoService")
@ServiceHandleMapping(managerClass = JokeInfoManager.class)
public class JokeInfoServiceImpl implements JokeInfoService {

	private static final Logger logger = LoggerFactory.getLogger(JokeInfoServiceImpl.class);
	
	@Autowired
	private JokeInfoManager jokeInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询段子表失败")})
	public Response<PageInfo<JokeInfo>> queryForPage(PageInfo<JokeInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询段子表失败")})
	public Response<JokeInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除段子表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除段子表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存段子表失败")})
	public Response<Void> save(JokeInfo jokeInfo) {
		return null;
	}

	@Override
	public Response<Void> publishBatch(List<Long> idList) {
		Response<Void> response = Response.newInstance();
		try {
			jokeInfoManager.publishBatch(idList);
		} catch (Exception e) {
			logger.error("批量发布失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量发布失败");
		}
		return response;
	}

	@Override
	public Response<Void> offlineBatch(List<Long> idList) {
		Response<Void> response = Response.newInstance();
		try {
			jokeInfoManager.offlineBatch(idList);
		} catch (Exception e) {
			logger.error("批量下线失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("批量下线失败");
		}
		return response;
	}

	@Override
	public Response<CommonListResult<JokeInfoData>> getJokeInfo(Long appId, String sessionId, String token, Long id, Long lastId, String lastOnlineTime) {
		Response<CommonListResult<JokeInfoData>> response = Response.newInstance();
		try {
			CommonListResult<JokeInfoData> data = jokeInfoManager.getJokeInfo(appId, sessionId, token, id, lastId, lastOnlineTime);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取段子失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取段子失败");
		} catch (Exception e) {
			logger.error("获取段子失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取段子失败");
		}
		return response;
	}
	
}