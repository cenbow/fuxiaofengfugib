package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.HousingPublic;
import com.cqliving.cloud.online.config.manager.HousingPublicManager;
import com.cqliving.cloud.online.config.service.HousingPublicService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("housingPublicService")
@ServiceHandleMapping(managerClass = HousingPublicManager.class)
public class HousingPublicServiceImpl implements HousingPublicService {

	private static final Logger logger = LoggerFactory.getLogger(HousingPublicServiceImpl.class);
	
	@Autowired
	private HousingPublicManager housingPublicManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询公租房表失败")})
	public Response<PageInfo<HousingPublic>> queryForPage(PageInfo<HousingPublic> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询公租房表失败")})
	public Response<HousingPublic> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除公租房表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除公租房表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存公租房表失败")})
	public Response<Void> save(HousingPublic housingPublic) {
		return null;
	}

	@Override
	public Response<List<HousingPublic>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap) {
		Response<List<HousingPublic>> response = Response.newInstance();
		try {
			List<HousingPublic> data = housingPublicManager.query(map, sortMap);
			response.setData(data);
		} catch (Exception e) {
			logger.error("查询公租房配租结果失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("查询公租房配租结果失败");
		}
		return response;
	}
	
}