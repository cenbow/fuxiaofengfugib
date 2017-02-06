package com.cqliving.cloud.online.tourism.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.manager.TourismSpecialManager;
import com.cqliving.cloud.online.tourism.service.TourismSpecialService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("tourismSpecialService")
@ServiceHandleMapping(managerClass = TourismSpecialManager.class)
public class TourismSpecialServiceImpl implements TourismSpecialService {

	private static final Logger logger = LoggerFactory.getLogger(TourismSpecialServiceImpl.class);
	
	@Autowired
	private TourismSpecialManager tourismSpecialManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询旅游专题关系表失败")})
	public Response<PageInfo<TourismSpecial>> queryForPage(PageInfo<TourismSpecial> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询旅游专题关系表失败")})
	public Response<TourismSpecial> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除旅游专题关系表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除旅游专题关系表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存旅游专题关系表失败")})
	public Response<Void> save(TourismSpecial tourismSpecial) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.service.TourismSpecialService#queryForSpecialSub(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<TourismInfoDto>> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.service.TourismSpecialService#updateSortNo(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateSortNo(Long tourismSpecialId,Integer sortNo) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.service.TourismSpecialService#queryForNoJoinSpecial(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<TourismInfoDto>> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.tourism.service.TourismSpecialService#joinSecial(java.lang.Long, java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> joinSecial(TourismSpecial tourismSpecial, Long[] refTourismId) {
		return null;
	}

	@Override
	public Response<List<TourismInfoDto>> queryForSubList(Long appId, Long tourismId, double lat, double lng) {
		Response<List<TourismInfoDto>> response = Response.newInstance();
		try {
			List<TourismInfoDto> data = tourismSpecialManager.queryForSubList(appId, tourismId, lat, lng);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取旅游子列表失败：" + e.getMessage(), e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取旅游子列表失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取旅游子列表失败");
		}
		return response;
	}
	
}