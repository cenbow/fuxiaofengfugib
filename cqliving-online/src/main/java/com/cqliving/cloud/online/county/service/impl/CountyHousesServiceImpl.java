package com.cqliving.cloud.online.county.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.cloud.online.county.manager.CountyHousesManager;
import com.cqliving.cloud.online.county.service.CountyHousesService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("countyHousesService")
@ServiceHandleMapping(managerClass = CountyHousesManager.class)
public class CountyHousesServiceImpl implements CountyHousesService {

	private static final Logger logger = LoggerFactory.getLogger(CountyHousesServiceImpl.class);
	
	@Autowired
	private CountyHousesManager countyHousesManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询区县楼盘表失败")})
	public Response<PageInfo<CountyHouses>> queryForPage(PageInfo<CountyHouses> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询区县楼盘表失败")})
	public Response<CountyHouses> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除区县楼盘表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存区县楼盘表失败")})
	public Response<Void> save(CountyHouses countyHouses) {
		return null;
	}

	/**
     * 获取区县和楼盘信息并保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:15:54
     */
    @Override
    public Response<Void> getAndSaveTask() {
        Response<Void> rs = Response.newInstance();
        try {
            countyHousesManager.getAndSaveTask();
        }catch(BusinessException e){
            logger.error("定时处理获取区县和楼盘信息并保存任务失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("定时处理获取区县和楼盘信息并保存任务失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("定时处理获取区县和楼盘信息并保存任务失败");
        }
        return rs;
    }

    /**
     * 上线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:27:50
     */
    @Override
    public Response<Void> online() {
        Response<Void> rs = Response.newInstance();
        try {
            countyHousesManager.online();
        }catch(BusinessException e){
            logger.error("定时处理区县和楼盘数据上线任务失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("定时处理区县和楼盘数据上线任务失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("定时处理区县和楼盘数据上线任务失败");
        }
        return rs;
    }

    /**
     * 滚动分页获取区县楼盘信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午2:29:21
     */
    @Override
    public Response<ScrollPage<CountyHouses>> getScrollPage(ScrollPage<CountyHouses> page, Map<String, Object> conditions,String token,String sessionId) {
        Response<ScrollPage<CountyHouses>> rs = Response.newInstance();
        try {
            ScrollPage<CountyHouses> data = countyHousesManager.getScrollPage(page, conditions, sessionId, token);
            rs.setData(data);
        }catch(BusinessException e){
            logger.error("滚动分页获取区县楼盘信息失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("滚动分页获取区县楼盘信息失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("滚动分页获取区县楼盘信息失败");
        }
        return rs;
    }
}
