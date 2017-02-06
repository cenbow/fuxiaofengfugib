package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.cloud.online.info.manager.InfoLableManager;
import com.cqliving.cloud.online.info.service.InfoLableService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoLableService")
@ServiceHandleMapping(managerClass = InfoLableManager.class)
public class InfoLableServiceImpl implements InfoLableService {
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯标签表失败")})
	public Response<PageInfo<InfoLable>> queryForPage(PageInfo<InfoLable> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯标签表失败")})
	public Response<InfoLable> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯标签表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="saveLable",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯标签表失败")})
	public Response<Void> save(InfoLable infoLable) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoLableService#findByAppId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InfoLable>> findByAppId(Long appId, Byte sourceType) {
		return null;
	}

	/**
     * 查询单条记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    @ServiceMethodHandle
    public Response<InfoLableDto> getById(Long id) {
        return null;
    }

    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    @ServiceMethodHandle(managerMethodName="queryInfoLabelDtoPage")
    public Response<PageInfo<InfoLableDto>> queryPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
    }
}
