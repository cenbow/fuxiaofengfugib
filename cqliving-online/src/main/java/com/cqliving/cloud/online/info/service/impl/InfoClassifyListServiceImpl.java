package com.cqliving.cloud.online.info.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.manager.InfoClassifyListManager;
import com.cqliving.cloud.online.info.service.InfoClassifyListService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoClassifyListService")
@ServiceHandleMapping(managerClass = InfoClassifyListManager.class)
public class InfoClassifyListServiceImpl implements InfoClassifyListService {

	//private static final Logger logger = LoggerFactory.getLogger(InfoClassifyListServiceImpl.class);
	
	@Autowired
	private InfoClassifyListManager infoClassifyListManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯栏目列表图片表失败")})
	public Response<PageInfo<InfoClassifyList>> queryForPage(PageInfo<InfoClassifyList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯栏目列表图片表失败")})
	public Response<InfoClassifyList> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯栏目列表图片表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯栏目列表图片表失败")})
	public Response<Void> save(InfoClassifyList infoClassifyList) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.info.service.InfoClassifyListService#findByInfoId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<InfoClassifyList> findByInfoId(Long infoId) {
		return null;
	}
}
