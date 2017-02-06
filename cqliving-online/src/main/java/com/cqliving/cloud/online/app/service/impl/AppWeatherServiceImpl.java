package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.manager.AppWeatherManager;
import com.cqliving.cloud.online.app.service.AppWeatherService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appWeatherService")
@ServiceHandleMapping(managerClass = AppWeatherManager.class)
public class AppWeatherServiceImpl implements AppWeatherService {

	//private static final Logger logger = LoggerFactory.getLogger(AppWeatherServiceImpl.class);
	
	@Autowired
	private AppWeatherManager appWeatherManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询天气预报表失败")})
	public Response<PageInfo<AppWeather>> queryForPage(PageInfo<AppWeather> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询天气预报表失败")})
	public Response<AppWeather> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除天气预报表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存天气预报表失败")})
	public Response<Void> save(AppWeather appWeather) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="getAll",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存天气预报表失败")})
	public Response<List<AppWeather>> getAll() {
		return null;
	}
	
	/**
	 * Title:获取APP所在区域的天气预报,如果没有则获取默认地区的天气预报
	 * @author yuwu on 2016年5月26日
	 * @param appId
	 * @return
	 */
	@Override
	@ServiceMethodHandle(managerMethodName="getAppWeatherByAppId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="APP获取天气预报失败")})
	public Response<AppWeather> getAppWeatherByAppId(Long appId) {
		return null;
	}
	
	/**
	 * Title:同步天气
	 * @author yuwu on 2016年5月25日
	 * @param appId
	 * @return
	 */
	@Override
	@ServiceMethodHandle(managerMethodName="syncWeather",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="APP获取天气预报失败")})
	public Response<Void> syncWeather() {
		return null;
	}
}
