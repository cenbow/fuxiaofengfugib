package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.cloud.online.config.manager.RecommendInfoManager;
import com.cqliving.cloud.online.config.service.RecommendInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("recommendInfoService")
@ServiceHandleMapping(managerClass = RecommendInfoManager.class)
public class RecommendInfoServiceImpl implements RecommendInfoService {

	private static final Logger logger = LoggerFactory.getLogger(RecommendInfoServiceImpl.class);
	
	@Autowired
	private RecommendInfoManager recommendInfoManager;
	
	@ServiceMethodHandle(managerMethodName="queryForPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询recommend_info失败")})
	public Response<PageInfo<RecommendInfoDto>> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap, Byte sourceType) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询recommend_info失败")})
	public Response<RecommendInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除recommend_info失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除recommend_info失败")})
	public Response<Void> deleteLogic(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	
	@Override
	public Response<Void> updateStatus(Byte status,Long... ids) {
		Response<Void> res = Response.newInstance();
		try {
			recommendInfoManager.updateStatus(status, ids);
		} catch (BusinessException e) {
			logger.error("修改推荐配置表状态失败:" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改推荐配置表状态失败:" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("修改推荐配置表状态失败");
		}
		return res;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存recommend_info失败")})
	public Response<Void> save(RecommendInfo recommendInfo) {
		return null;
	}

	@ServiceMethodHandle(managerMethodName="getDetail",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询recommend_info失败")})
	public Response<RecommendInfoDto> getDetail(Long id, Byte sourceType) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="updateSort",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="recommend_info排序失败")})
	public Response<Void> updateSort(Long id, Integer sortNo) {
		return null;
	}
}
