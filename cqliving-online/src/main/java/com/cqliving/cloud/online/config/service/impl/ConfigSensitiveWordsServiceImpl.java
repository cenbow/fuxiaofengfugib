package com.cqliving.cloud.online.config.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigSensitiveWords;
import com.cqliving.cloud.online.config.dto.ConfigSensitiveWordsDto;
import com.cqliving.cloud.online.config.manager.ConfigSensitiveWordsManager;
import com.cqliving.cloud.online.config.service.ConfigSensitiveWordsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configSensitiveWordsService")
@ServiceHandleMapping(managerClass = ConfigSensitiveWordsManager.class)
public class ConfigSensitiveWordsServiceImpl implements ConfigSensitiveWordsService {
    
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询敏感词表失败")})
	public Response<PageInfo<ConfigSensitiveWords>> queryForPage(PageInfo<ConfigSensitiveWords> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询敏感词表失败")})
	public Response<ConfigSensitiveWords> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除敏感词表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除敏感词表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存敏感词表失败")})
	public Response<Void> save(ConfigSensitiveWords configSensitiveWords) {
		return null;
	}
	
	/**
     * 通过id查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月6日下午4:12:54
     */
	@Override
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id查询敏感词表失败")})
    public Response<ConfigSensitiveWordsDto> getById(Long id) {
        return null;
    }
    
    /**
     * Title:分页查询
     * @author huxiaoping 2016年7月6日下午4:18:44
     * @param map
     * @param sortMap
     * @return
     */
	@Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询敏感词表失败")})
    public Response<PageInfo<ConfigSensitiveWordsDto>> queryPage(PageInfo<ConfigSensitiveWordsDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap) {
        return null;
    }
}