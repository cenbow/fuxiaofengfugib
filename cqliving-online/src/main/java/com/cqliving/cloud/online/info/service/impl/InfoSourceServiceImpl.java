package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.cloud.online.info.manager.InfoSourceManager;
import com.cqliving.cloud.online.info.service.InfoSourceService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoSourceService")
@ServiceHandleMapping(managerClass = InfoSourceManager.class)
public class InfoSourceServiceImpl implements InfoSourceService {

	//private static final Logger logger = LoggerFactory.getLogger(InfoSourceServiceImpl.class);
	
	@Autowired
	private InfoSourceManager infoSourceManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯来源表失败")})
	public Response<PageInfo<InfoSource>> queryForPage(PageInfo<InfoSource> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯来源表失败")})
	public Response<InfoSource> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯来源表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯来源表失败")})
	public Response<Void> save(InfoSource infoSource) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoSourceService#findBySource(java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InfoSource>> findByConditions(Map<String,Object> conditions) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoSourceService#updateStatus(java.lang.Byte, java.lang.Long[])
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateStatus(Byte status, Long... id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoSourceService#updateSortNo(java.lang.Long, java.lang.Integer)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> updateSortNo(Long id, Integer sortNo) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoSourceService#queryForPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<InfoSourceDto>> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
