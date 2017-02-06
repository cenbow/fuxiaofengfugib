package com.cqliving.cloud.online.info.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassifyCommentHis;
import com.cqliving.cloud.online.info.manager.InfoClassifyCommentHisManager;
import com.cqliving.cloud.online.info.service.InfoClassifyCommentHisService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoClassifyCommentHisService")
@ServiceHandleMapping(managerClass = InfoClassifyCommentHisManager.class)
public class InfoClassifyCommentHisServiceImpl implements InfoClassifyCommentHisService {

	//private static final Logger logger = LoggerFactory.getLogger(InfoClassifyCommentHisServiceImpl.class);
	
	@Autowired
	private InfoClassifyCommentHisManager infoClassifyCommentHisManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯栏目历史推荐表失败")})
	public Response<PageInfo<InfoClassifyCommentHis>> queryForPage(PageInfo<InfoClassifyCommentHis> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯栏目历史推荐表失败")})
	public Response<InfoClassifyCommentHis> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯栏目历史推荐表失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯栏目历史推荐表失败")})
	public Response<Void> save(InfoClassifyCommentHis infoClassifyCommentHis) {
		return null;
	}
}
