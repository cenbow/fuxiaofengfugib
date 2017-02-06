package com.cqliving.cloud.online.analysis.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananData;
import com.cqliving.cloud.online.analysis.manager.AnalysisNananDataManager;
import com.cqliving.cloud.online.analysis.service.AnalysisNananDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("analysisNananDataService")
@ServiceHandleMapping(managerClass = AnalysisNananDataManager.class)
public class AnalysisNananDataServiceImpl implements AnalysisNananDataService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisNananDataServiceImpl.class);
	
	@Autowired
	private AnalysisNananDataManager analysisNananDataManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询南岸讲学赞栏目统计失败")})
	public Response<PageInfo<AnalysisNananData>> queryForPage(PageInfo<AnalysisNananData> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询南岸讲学赞栏目统计失败")})
	public Response<AnalysisNananData> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除南岸讲学赞栏目统计失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存南岸讲学赞栏目统计失败")})
	public Response<Void> save(AnalysisNananData analysisNananData) {
		return null;
	}
}
