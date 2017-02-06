package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.manager.AppInfoManager;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appInfoService")
@ServiceHandleMapping(managerClass = AppInfoManager.class)
public class AppInfoServiceImpl implements AppInfoService {

	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端失败")})
	public Response<PageInfo<AppInfo>> queryForPage(PageInfo<AppInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询客户端失败")})
	public Response<AppInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除客户端失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端失败")})
	public Response<Void> save(AppInfo appInfo) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="getAllCache",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="缓存中获取所有客户端失败")})
	public Response<List<AppInfo>> getAll() {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="update",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="更新客户端失败")})
	public Response<Void> update(AppInfo appInfo) {
	    return null;
	}

    @Override
    @ServiceMethodHandle(managerMethodName="saveAppInfo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存客户端失败")})
    public Response<Void> saveAppInfo(AppInfo domain,String password, String username,String nickname,AppQiniu qiniu,AppWeather weather,Byte[] type,String[] content,String downLoadUrl) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询客户端失败")})
    public Response<PageInfo<AppInfoDto>> queryPage(PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
    }

    /**
     * 查询某用户对应数据权限表的App
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月29日上午9:47:42
     */
    @Override
    @ServiceMethodHandle(managerMethodName="queryPageByUser",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询某用户对应数据权限表的App失败")})
    public Response<PageInfo<AppInfoDto>> queryPageByUser(Byte usertype,Long userId,PageInfo<AppInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppInfoService#findByIds(java.util.List)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<AppInfo>> findByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppInfoService#findByDomain(java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<AppInfo> findByDomain(String domain) {
		return null;
	}
	
	/**
	 * 根据登录用户的用户类型，用户id查询客户端信息
	 * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月2日
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getBySysUser",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="根据登录用户的用户类型，用户id查询客户端信息失败")})
    public Response<List<AppInfoDto>> getBySysUser(Byte usertype, Long userId) {
        return null;
    }
}
