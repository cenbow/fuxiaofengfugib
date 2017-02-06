package com.cqliving.cloud.online.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.info.manager.InfoCorrelationManager;
import com.cqliving.cloud.online.info.service.InfoCorrelationService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoCorrelationService")
@ServiceHandleMapping(managerClass = InfoCorrelationManager.class)
public class InfoCorrelationServiceImpl implements InfoCorrelationService {

	private static final Logger logger = LoggerFactory.getLogger(InfoCorrelationServiceImpl.class);
	
	@Autowired
	private InfoCorrelationManager infoCorrelationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询相关资讯表失败")})
	public Response<PageInfo<InfoCorrelation>> queryForPage(PageInfo<InfoCorrelation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询相关资讯表失败")})
	public Response<InfoCorrelation> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除相关资讯表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存相关资讯表失败")})
	public Response<Void> save(InfoCorrelation infoCorrelation) {
		return null;
	}

	@Override
	public Response<Void> clearSortNo(List<Long> asList) {
		Response<Void> response = Response.newInstance();
		try {
			infoCorrelationManager.clearSortNo(asList);
		} catch (Exception e) {
			logger.error("清空排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("清空排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> modifySortNo(Long id, Integer sortNo) {
		Response<Void> response = Response.newInstance();
		try {
			infoCorrelationManager.modifySortNo(id, sortNo);
		} catch (Exception e) {
			logger.error("修改排序失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("修改排序失败");
		}
		return response;
	}

	@Override
	public Response<Void> moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoCorrelationManager.moveOut(infoClasifyIds, infoCorrelationIds, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("移出专题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("移出专题失败");
		}
		return response;
	}

	@Override
	public Response<Void> moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Long appColumnId, Date updateTime, Long updatorId, String updator) {
		Response<Void> response = Response.newInstance();
		try {
			infoCorrelationManager.moveOut(infoClasifyIds, infoCorrelationIds, appColumnId, updateTime, updatorId, updator);
		} catch (Exception e) {
			logger.error("移出专题失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("移出专题失败");
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoCorrelationService#joinSpecialInfo(java.lang.Long, java.lang.Long, java.lang.Long[])
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> joinSpecialInfo(Long[] infoClassifyIds, Long refInfoClassId, Long[] infoThemeIds,Long[] appIds,String sessionUser,Long sessionUserId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoCorrelationService#infoCorrelation(java.lang.Long, java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> infoCorrelation(Long infoClassifyId, Long refInfoClassifyId,Long appId,String sessionUser,Long sessionUserId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoCorrelationService#cancelCorrelation(java.lang.Long, java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> cancelCorrelation(Long infoClassifyId, Long refInfoClassifyId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoCorrelationService#joinInfo(java.lang.Long, java.lang.Long[], java.lang.Long[])
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> joinInfo(Long[] infoClassIds, Long refInfoClassifyId, Long appId,Long infoThemeId,String sessionUser,Long sessionUserId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoCorrelationService#findByInfoIdAndRefId(java.lang.Long, java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InfoCorrelation>> findByInfoIdAndRefId(Long infoClassifyId, Long refClassifyId) {
		return null;
	}

}
