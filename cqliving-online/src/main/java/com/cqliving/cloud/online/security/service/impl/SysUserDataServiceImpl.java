package com.cqliving.cloud.online.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.cloud.online.security.manager.SysUserDataManager;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("sysUserDataService")
@ServiceHandleMapping(managerClass = SysUserDataManager.class)
public class SysUserDataServiceImpl implements SysUserDataService {

	//private static final Logger logger = LoggerFactory.getLogger(SysUserDataServiceImpl.class);
	
	@Autowired
	private SysUserDataManager sysUserDataManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询系统用户数据权限表，目前数据权限的值为app_id对应的值失败")})
	public Response<PageInfo<SysUserData>> queryForPage(PageInfo<SysUserData> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询系统用户数据权限表，目前数据权限的值为app_id对应的值失败")})
	public Response<SysUserData> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除系统用户数据权限表，目前数据权限的值为app_id对应的值失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存系统用户数据权限表，目前数据权限的值为app_id对应的值失败")})
	public Response<Void> save(SysUserData sysUserData) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysUserDataService#findByUserId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<SysUserData>> findByUserId(Long userId,byte type) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysUserDataService#findAppIdsByUserId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Long[]> findValueByUserId(Long userId,byte type) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysUserDataService#saveSysUserDataByType(java.lang.Long, java.lang.Long[], byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<SysUserData>> saveSysUserDataByType(Long userId, Long[] value, byte type) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysUserDataService#findPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<SysUserDataDto>> findPage(PageInfo<SysUserDataDto> pageInfo, Map<String, Object> map,
			Map<String, Boolean> orderMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysUserDataService#deleteByUserIdAndType(java.lang.Long, byte)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> deleteByUserIdAndType(Long userId, byte type) {
		return null;
	}
	
}
