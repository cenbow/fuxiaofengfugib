package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigHotline;
import com.cqliving.cloud.online.config.dto.ConfigHotlineDto;
import com.cqliving.cloud.online.config.manager.ConfigHotlineManager;
import com.cqliving.cloud.online.config.service.ConfigHotlineService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configHotlineService")
@ServiceHandleMapping(managerClass = ConfigHotlineManager.class)
public class ConfigHotlineServiceImpl implements ConfigHotlineService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigHotlineServiceImpl.class);
	
//	@Autowired
//	private ConfigHotlineManager configHotlineManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询config_热线表失败")})
	public Response<PageInfo<ConfigHotline>> queryForPage(PageInfo<ConfigHotline> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询config_热线表失败")})
	public Response<ConfigHotline> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_热线表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除config_热线表失败")})
	public Response<Void> deleteLogic(String updator,Long updatorId,Long... id) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(String updator,Long updatorId,Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存config_热线表失败")})
	public Response<Void> save(ConfigHotline configHotline) {
		return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryByPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询热线表失败")})
    public Response<PageInfo<ConfigHotlineDto>> queryByPage(PageInfo<ConfigHotlineDto> pageInfo,
            Map<String, Object> conditions, Map<String, Boolean> orders) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="getById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询热线详情失败")})
    public Response<ConfigHotlineDto> getById(Long id) {
        return null;
    }

	@Override
	@ServiceMethodHandle(managerMethodName = "query", exceptionClass = {@ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "查询热线列表失败")})
	public Response<List<ConfigHotline>> queryForList(Map<String, Object> map, Map<String, Boolean> sortMap) {
		return null;
	}
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="updateSortNo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<Void> updateSortNo(Integer sortNo, String updator, Long updateUserId, Long... ids) {
        return null;
    }
}