package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigWeatherCondition;
import com.cqliving.cloud.online.config.manager.ConfigWeatherConditionManager;
import com.cqliving.cloud.online.config.service.ConfigWeatherConditionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configWeatherConditionService")
@ServiceHandleMapping(managerClass = ConfigWeatherConditionManager.class)
public class ConfigWeatherConditionServiceImpl implements ConfigWeatherConditionService {

	//private static final Logger logger = LoggerFactory.getLogger(ConfigWeatherConditionServiceImpl.class);
	
	@Autowired
	private ConfigWeatherConditionManager configWeatherConditionManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询天气预报状态表失败")})
	public Response<PageInfo<ConfigWeatherCondition>> queryForPage(PageInfo<ConfigWeatherCondition> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询天气预报状态表失败")})
	public Response<ConfigWeatherCondition> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除天气预报状态表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存天气预报状态表失败")})
	public Response<Void> save(ConfigWeatherCondition configWeatherCondition) {
		return null;
	}
	
	/**
	 * Title:返回天气状况CODE与图片关系表
	 * @author yuwu on 2016年5月25日
	 * @return
	 */
	@Override
	@ServiceMethodHandle(managerMethodName="geConfigWeatherMap",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存天气预报状态表失败")})
	public Response<Map<String,String>> geConfigWeatherMap() {
		return null;
	}
	
}
