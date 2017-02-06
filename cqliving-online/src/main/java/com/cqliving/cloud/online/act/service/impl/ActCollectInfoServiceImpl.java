package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActCollectInfo;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.cloud.online.act.manager.ActCollectInfoManager;
import com.cqliving.cloud.online.act.service.ActCollectInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actCollectInfoService")
@ServiceHandleMapping(managerClass = ActCollectInfoManager.class)
public class ActCollectInfoServiceImpl implements ActCollectInfoService {

	private static final Logger logger = LoggerFactory.getLogger(ActCollectInfoServiceImpl.class);
	
	@Autowired
	private ActCollectInfoManager actCollectInfoManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动信息收集表失败")})
	public Response<PageInfo<ActCollectInfo>> queryForPage(PageInfo<ActCollectInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动信息收集表失败")})
	public Response<ActCollectInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动信息收集表失败")})
	public Response<Void> delete(Long... ids) {
		logger.info("删除操作");
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动信息收集表失败")})
	public Response<Void> save(ActCollectInfo actCollectInfo) {
		return null;
	}

	@Override
	public Response<List<ActCollectInfo>> getByApp(Long appId) {
		Response<List<ActCollectInfo>> rs = Response.newInstance();
		try {
			rs.setData(actCollectInfoManager.getByApp(appId));
		} catch (Exception e) {
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取活动信息收集集合失败");
		}
		return rs;
	}
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
	@Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询配置信息失败")})
    public Response<PageInfo<ActCollectInfoDto>> queryPage(PageInfo<ActCollectInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap){
	    return null;
    }
    
    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
	@Override
    @ServiceMethodHandle(managerMethodName="findById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id查询配置失败")})
    public Response<ActCollectInfoDto> findById(Long id){
	    return null;
	}
	
	/**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
	@Override
	@ServiceMethodHandle(managerMethodName="saveInfo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存配置失败")})
    public Response<Void> save(ActCollectInfo domain,String[] value){
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="delById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过id删除活动配置失败")})
    public Response<Void> delById(Long... id) {
        return null;
    }
}
