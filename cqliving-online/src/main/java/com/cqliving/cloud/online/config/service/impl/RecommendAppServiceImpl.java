package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.cloud.online.config.manager.RecommendAppManager;
import com.cqliving.cloud.online.config.service.RecommendAppService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("recommendAppService")
@ServiceHandleMapping(managerClass = RecommendAppManager.class)
public class RecommendAppServiceImpl implements RecommendAppService {

	private static final Logger logger = LoggerFactory.getLogger(RecommendAppServiceImpl.class);
	
	@Autowired
	private RecommendAppManager recommendAppManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询区县推荐APP表，在重庆APP中使用失败")})
	public Response<PageInfo<RecommendApp>> queryForPage(PageInfo<RecommendApp> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询区县推荐APP表，在重庆APP中使用失败")})
	public Response<RecommendApp> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区县推荐APP表，在重庆APP中使用失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区县推荐APP表，在重庆APP中使用失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存区县推荐APP表，在重庆APP中使用失败")})
	public Response<Void> save(RecommendApp recommendApp) {
		return null;
	}
	
	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> response = Response.newInstance();
		try {
			recommendAppManager.modifySortNo(id, sortNo);
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改排序失败");
		}
		return response;
	}

	@Override
	public Response<PageInfo<RecommendAppDto>> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<PageInfo<RecommendAppDto>> response = Response.newInstance();
		try {
			PageInfo<RecommendAppDto> data = recommendAppManager.queryDtoForPage(pageInfo, searchMap, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("查询区县推荐管理列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("查询区县推荐管理列表失败");
		}
		return response;
	}

	@Override
	public Response<ScrollPage<RecommendAppDto>> queryForScrollPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions) {
		Response<ScrollPage<RecommendAppDto>> response = Response.newInstance();
		try {
			ScrollPage<RecommendAppDto> data = recommendAppManager.queryDtoForPage(scrollPage, conditions);
			response.setData(data);
		} catch (Exception e) {
			logger.error("查询区县推荐管理列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("查询区县推荐管理列表失败");
		}
		return response;
	}
	
}