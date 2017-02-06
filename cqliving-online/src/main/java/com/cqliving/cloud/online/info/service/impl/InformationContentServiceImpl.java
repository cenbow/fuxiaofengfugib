package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.manager.InformationContentManager;
import com.cqliving.cloud.online.info.service.InformationContentService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("informationContentService")
@ServiceHandleMapping(managerClass = InformationContentManager.class)
public class InformationContentServiceImpl implements InformationContentService {

	//private static final Logger logger = LoggerFactory.getLogger(InformationContentServiceImpl.class);
	
	@Autowired
	private InformationContentManager informationContentManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯内容表失败")})
	public Response<PageInfo<InformationContent>> queryForPage(PageInfo<InformationContent> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯内容表失败")})
	public Response<InformationContent> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯内容表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯内容表失败")})
	public Response<Void> save(InformationContent informationContent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationContentService#findByInfoId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<List<InformationContent>> findByInfoId(Long infoId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InformationContentService#infoContentSort(java.lang.Integer[], java.lang.Long[])
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> infoContentSort(Integer[] sorts, Long[] contentIds) {
		return null;
	}
}
