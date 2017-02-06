package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.manager.ConfigTextManager;
import com.cqliving.cloud.online.config.service.ConfigTextService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configTextService")
@ServiceHandleMapping(managerClass = ConfigTextManager.class)
public class ConfigTextServiceImpl implements ConfigTextService {

	//private static final Logger logger = LoggerFactory.getLogger(ConfigTextServiceImpl.class);
	
//	@Autowired
//	private ConfigTextManager configTextManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询联系我们、区情介绍、反馈回复失败")})
	public Response<PageInfo<ConfigText>> queryForPage(PageInfo<ConfigText> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询联系我们、区情介绍、反馈回复失败")})
	public Response<ConfigText> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除联系我们、区情介绍、反馈回复失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存联系我们、区情介绍、反馈回复失败")})
	public Response<Void> save(ConfigText configText) {
		return null;
	}
	
	/**
     * 通过appId和type查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月14日下午5:08:54
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getByAppId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过appId和type查询失败")})
    public Response<ConfigText> getByAppId(Long appId, Byte type) {
        return null;
    }
}
