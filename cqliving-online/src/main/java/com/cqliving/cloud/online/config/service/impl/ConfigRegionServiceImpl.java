package com.cqliving.cloud.online.config.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.cloud.online.config.manager.ConfigRegionManager;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("configRegionService")
@ServiceHandleMapping(managerClass = ConfigRegionManager.class)
public class ConfigRegionServiceImpl implements ConfigRegionService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigRegionServiceImpl.class);
	
	@Autowired
	private ConfigRegionManager configRegionManager;

    @Override
    public Response<List<ConfigRegion>> getByPcode(String pcode, String query) {
        Response<List<ConfigRegion>> rs = Response.newInstance();
        try {
            List<ConfigRegion> data = configRegionManager.getByPcode(pcode, query);
            rs.setData(data);
        } catch (Exception e) {
            logger.error("获取区域失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取区域失败");
        }
        return rs;
    }

	@Override
	public Response<List<ConfigRegion>> getByAppAndType(Long[] appId,Byte type) {
		Response<List<ConfigRegion>> response = Response.newInstance();
        try {
        	List<ConfigRegion> data = configRegionManager.getByAppAndType(appId, type, null);
        	response.setData(data);
        } catch (Exception e) {
            logger.error("获取区域失败：" + e.getMessage(), e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("获取区域失败");
        }
        return response;
	}
	
	@Override
	public Response<List<ConfigRegion>> getByAppAndType(Long appId, Byte type, String query) {
		Response<List<ConfigRegion>> response = Response.newInstance();
		try {
			List<ConfigRegion> data = configRegionManager.getByAppAndType(new Long[]{appId}, type, query);
			response.setData(data);
		} catch (Exception e) {
			logger.error("获取区域失败：" + e.getMessage(), e);
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("获取区域失败");
		}
		return response;
	}

	@Override
	public Response<List<ConfigRegion>> getShopByAppAndType(Long appId, Long shopTypeId) {
		Response<List<ConfigRegion>> response = Response.newInstance();
        try {
        	List<ConfigRegion> data = configRegionManager.getShopByAppAndType(appId, shopTypeId);
        	response.setData(data);
        } catch (Exception e) {
            logger.error("获取商情区域失败：" + e.getMessage(), e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("获取商情区域失败");
        }
        return response;
	}	
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询区域表失败")})
	public Response<PageInfo<ConfigRegion>> queryForPage(PageInfo<ConfigRegion> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询区域表失败")})
	public Response<ConfigRegion> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区域表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区域表失败")})
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
	@ServiceMethodHandle(managerMethodName="saveInfo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存区域表失败")})
	public Response<Void> save(ConfigRegion configRegion) {
		return null;
	}
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
	@Override
    @ServiceMethodHandle(managerMethodName="updateSortNo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<Void> updateSortNo(Integer sortNo,Long... ids){
	    return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="queryByPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询商情区域信息失败")})
    public Response<PageInfo<ConfigRegionDto>> queryByPage(PageInfo<ConfigRegionDto> pageInfo, Map<String, Object> conditions,Map<String, Boolean> orders) {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.config.service.ConfigRegionService#findByType(java.lang.Byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<ConfigRegion>> findByType(Byte type) {
		return null;
	}
	
}
