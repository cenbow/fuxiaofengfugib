package com.cqliving.cloud.online.analysis.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananTimes;
import com.cqliving.cloud.online.analysis.manager.AnalysisNananTimesManager;
import com.cqliving.cloud.online.analysis.service.AnalysisNananTimesService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.google.common.collect.Maps;

@Service("analysisNananTimesService")
@ServiceHandleMapping(managerClass = AnalysisNananTimesManager.class)
public class AnalysisNananTimesServiceImpl implements AnalysisNananTimesService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisNananTimesServiceImpl.class);
	
	@Autowired
	private AnalysisNananTimesManager analysisNananTimesManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询南岸讲学赞栏目统计期数表失败")})
	public Response<PageInfo<AnalysisNananTimes>> queryForPage(PageInfo<AnalysisNananTimes> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询南岸讲学赞栏目统计期数表失败")})
	public Response<AnalysisNananTimes> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除南岸讲学赞栏目统计期数表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存南岸讲学赞栏目统计期数表失败")})
	public Response<Void> save(AnalysisNananTimes analysisNananTimes) {
		return null;
	}

	@Override
	public Response<List<AnalysisNananTimes>> getAll() {
		Response<List<AnalysisNananTimes>> res = Response.newInstance();
		try {
			Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
			sortMap.put("id", false);
			List<AnalysisNananTimes> data = analysisNananTimesManager.query(null, sortMap);
			res.setData(data);
		} catch (BusinessException e) {
			logger.debug("获取全部讲学赞期数失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.debug("获取全部讲学赞期数失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("获取全部讲学赞期数失败");
		}
		return res;
	}

	@Override
	public Response<Void> createTimes(String beginDate, String endDate, Long columnsId) {
		Response<Void> res = Response.newInstance();
		try {
			analysisNananTimesManager.createTimes(beginDate, endDate, columnsId);
		} catch (BusinessException e) {
			logger.debug("生成南岸讲学赞统计期数失败：" + e.getMessage(), e);
			res.setCode(e.getErrorCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.debug("生成南岸讲学赞统计期数失败：" + e.getMessage(), e);
			res.setCode(ErrorCodes.FAILURE);
			res.setMessage("生成期数失败");
		}
		return res;
	}
	
}