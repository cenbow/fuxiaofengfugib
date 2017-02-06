package com.cqliving.cloud.online.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.app.manager.AppResouseManager;
import com.cqliving.cloud.online.app.service.AppResouseService;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("appResouseService")
@ServiceHandleMapping(managerClass = AppResouseManager.class)
public class AppResouseServiceImpl implements AppResouseService {

	//private static final Logger logger = LoggerFactory.getLogger(AppResouseServiceImpl.class);
	
	@Autowired
	private AppResouseManager appResouseManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯资源表【文字,图文,视频,音频】失败")})
	public Response<PageInfo<AppResouse>> queryForPage(PageInfo<AppResouse> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯资源表【文字,图文,视频,音频】失败")})
	public Response<AppResouse> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯资源表【文字,图文,视频,音频】失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯资源表【文字,图文,视频,音频】失败")})
	public Response<Void> save(AppResouse appResouse) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppResouseService#findByInfoContentId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<AppResouse>> findByInfoContentId(Long informationContentId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppResouseService#saveAppResouse(com.cqliving.cloud.online.app.domain.AppResouse)
	 */
	@Override
	@ServiceMethodHandle
	public Response<AppResouse> saveAppResouse(AppResouse[] appResouse,InfoFile infoFile) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.app.service.AppResouseService#findByInformationId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<AppResouse>> findByInformationId(Long informationId) {
		return null;
	}
}
