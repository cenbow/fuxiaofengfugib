package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.cloud.online.app.manager.AllMediaManager;
import com.cqliving.cloud.online.app.service.AllMediaService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("allMediaService")
@ServiceHandleMapping(managerClass = AllMediaManager.class)
public class AllMediaServiceImpl implements AllMediaService {

	private static final Logger logger = LoggerFactory.getLogger(AllMediaServiceImpl.class);
	
	@Autowired
	private AllMediaManager allMediaManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询全媒体表失败")})
	public Response<PageInfo<AllMedia>> queryForPage(PageInfo<AllMedia> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询全媒体表失败")})
	public Response<AllMedia> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除全媒体表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除全媒体表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存全媒体表失败")})
	public Response<Void> save(AllMedia allMedia) {
		return null;
	}
	
	@ServiceMethodHandle
	public Response<PageInfo<AllMediaDto>> queryPage(PageInfo<AllMediaDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
		return null;
	}

	@Override
	public Response<List<AllMedia>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap) {
		Response<List<AllMedia>> response = Response.newInstance();
		try {
			List<AllMedia> data = allMediaManager.query(map, sortMap);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取全媒体列表失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取全媒体列表失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取全媒体列表失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AllMediaService#updateSortNo(java.lang.Long, java.lang.Integer)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateSortNo(Long id, Integer sortNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}