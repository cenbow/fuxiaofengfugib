package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.manager.AppQiniuManager;
import com.cqliving.cloud.online.app.service.AppQiniuService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appQiniuService")
@ServiceHandleMapping(managerClass = AppQiniuManager.class)
public class AppQiniuServiceImpl implements AppQiniuService {

	//private static final Logger logger = LoggerFactory.getLogger(AppQiniuServiceImpl.class);
	
	@Autowired
	private AppQiniuManager appQiniuManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询APP七牛云服务配置失败")})
	public Response<PageInfo<AppQiniu>> queryForPage(PageInfo<AppQiniu> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询APP七牛云服务配置失败")})
	public Response<AppQiniu> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除APP七牛云服务配置失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除APP七牛云服务配置失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存APP七牛云服务配置失败")})
	public Response<Void> save(AppQiniu appQiniu) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppQiniuService#findByAppId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<AppQiniu>> findByAppId(Long appId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppQiniuService#findByDefault(java.lang.Byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<AppQiniu>> findByDefault(Byte isDefault) {
		return null;
	}
}
