package com.cqliving.cloud.online.county.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.cloud.online.county.manager.CountyManager;
import com.cqliving.cloud.online.county.service.CountyService;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("countyService")
@ServiceHandleMapping(managerClass = CountyManager.class)
public class CountyServiceImpl implements CountyService {

	private static final Logger logger = LoggerFactory.getLogger(CountyServiceImpl.class);
	
	@Autowired
	private CountyManager countyManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询区县表失败")})
	public Response<PageInfo<County>> queryForPage(PageInfo<County> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询区县表失败")})
	public Response<County> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区县表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存区县表失败")})
	public Response<Void> save(County county) {
		return null;
	}

	/**
     * 获取区县信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午1:33:23
     */
    @Override
    public Response<List<County>> queryList(String token, String sessionId) {
        Response<List<County>> response = Response.newInstance();
        try {
            List<County> countyList = countyManager.getList(token, sessionId);
            response.setMessage("获取区县信息成功");
            response.setData(countyList);
        } catch (BusinessException e) {
            logger.error("获取区县信息失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取区县信息失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("获取区县信息失败");
        }
        return response;
    }
}
