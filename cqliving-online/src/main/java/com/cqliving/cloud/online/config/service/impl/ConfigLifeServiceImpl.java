package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.cloud.online.config.manager.ConfigLifeManager;
import com.cqliving.cloud.online.config.service.ConfigLifeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configLifeService")
@ServiceHandleMapping(managerClass = ConfigLifeManager.class)
public class ConfigLifeServiceImpl implements ConfigLifeService {

	//private static final Logger logger = LoggerFactory.getLogger(ConfigLifeServiceImpl.class);
	
	@Autowired
	private ConfigLifeManager configLifeManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询中国建行银行悦生活服务接口失败")})
	public Response<PageInfo<ConfigLife>> queryForPage(PageInfo<ConfigLife> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询中国建行银行悦生活服务接口失败")})
	public Response<ConfigLife> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除中国建行银行悦生活服务接口失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存中国建行银行悦生活服务接口失败")})
	public Response<Void> save(ConfigLife configLife) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="getByType",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询中国建行银行悦生活服务接口失败")})
	public Response<ConfigLife> getByType(Integer id) {
		return null;
	}
}
