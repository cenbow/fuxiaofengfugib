package com.cqliving.cloud.online.analysis.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.cloud.online.analysis.manager.AnalysisNewsManager;
import com.cqliving.cloud.online.analysis.service.AnalysisNewsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("analysisNewsService")
@ServiceHandleMapping(managerClass = AnalysisNewsManager.class)
public class AnalysisNewsServiceImpl implements AnalysisNewsService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisNewsServiceImpl.class);
	
	@Autowired
	private AnalysisNewsManager analysisNewsManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯统计表失败")})
	public Response<PageInfo<AnalysisNews>> queryForPage(PageInfo<AnalysisNews> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯统计表失败")})
	public Response<AnalysisNews> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯统计表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯统计表失败")})
	public Response<Void> save(AnalysisNews analysisNews) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.service.AnalysisNewsService#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Map<String,List<Map<String,String>>>> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.service.AnalysisNewsService#callProcedure()
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> callProcedure() {
		return null;
	}
}
