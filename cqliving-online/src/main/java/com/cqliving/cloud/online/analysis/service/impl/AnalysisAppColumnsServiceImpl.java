package com.cqliving.cloud.online.analysis.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.cloud.online.analysis.manager.AnalysisAppColumnsManager;
import com.cqliving.cloud.online.analysis.service.AnalysisAppColumnsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("analysisAppColumnsService")
@ServiceHandleMapping(managerClass = AnalysisAppColumnsManager.class)
public class AnalysisAppColumnsServiceImpl implements AnalysisAppColumnsService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisAppColumnsServiceImpl.class);
	
	@Autowired
	private AnalysisAppColumnsManager analysisAppColumnsManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯栏目统计表失败")})
	public Response<PageInfo<AnalysisAppColumns>> queryForPage(PageInfo<AnalysisAppColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯栏目统计表失败")})
	public Response<AnalysisAppColumns> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯栏目统计表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯栏目统计表失败")})
	public Response<Void> save(AnalysisAppColumns analysisAppColumns) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.service.AnalysisAppColumnsService#callProcedure()
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> callProcedure() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.service.AnalysisAppColumnsService#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Map<String, List<Map<String, String>>>> findByConditions(Map<String, Object> conditions,
			Map<String, Boolean> sortMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.service.AnalysisAppColumnsService#findPageInfo(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	@ServiceMethodHandle
	public Response<PageInfo<AnalysisAppColumnsDto>> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,
			Map<String, Object> conditions) {
		return null;
	}
}
