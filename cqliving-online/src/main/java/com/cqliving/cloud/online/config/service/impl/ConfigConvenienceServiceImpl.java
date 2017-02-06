package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.cloud.online.config.manager.ConfigConvenienceManager;
import com.cqliving.cloud.online.config.service.ConfigConvenienceService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ConvenienceData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configConvenienceService")
@ServiceHandleMapping(managerClass = ConfigConvenienceManager.class)
public class ConfigConvenienceServiceImpl implements ConfigConvenienceService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigConvenienceServiceImpl.class);
	
	@Autowired
	private ConfigConvenienceManager configConvenienceManager;
	
	@ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询便民表失败")})
	public Response<PageInfo<ConfigConvenienceDto>> queryForPage(PageInfo<ConfigConvenienceDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询config_便民表失败")})
	public Response<ConfigConvenience> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_便民表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_便民表失败")})
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
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存config_便民表失败")})
	public Response<Void> save(ConfigConvenience configConvenience) {
		return null;
	}

	@Override
	public Response<CommonListResult<ConvenienceData>> getByApp(Long appId) {
		Response<CommonListResult<ConvenienceData>> response = Response.newInstance();
		try {
			CommonListResult<ConvenienceData> data = configConvenienceManager.getByApp(appId);
			response.setData(data);
		} catch (BusinessException e) {
			logger.error("获取便民信息失败", e);
			response.setCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取便民信息失败", e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取便民信息失败");
		}
		return response;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="updateSort",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="便民修改排序失败")})
	public Response<Void> updateSort(Long id, Integer sortNo) {
		return null;
	}
}
