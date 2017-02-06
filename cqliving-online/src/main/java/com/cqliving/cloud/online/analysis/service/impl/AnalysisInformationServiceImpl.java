package com.cqliving.cloud.online.analysis.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.analysis.domain.AnalysisInformation;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoColumns;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoDetail;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoStatistics;
import com.cqliving.cloud.online.analysis.manager.AnalysisInformationManager;
import com.cqliving.cloud.online.analysis.service.AnalysisInformationService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("analysisInformationService")
@ServiceHandleMapping(managerClass = AnalysisInformationManager.class)
public class AnalysisInformationServiceImpl implements AnalysisInformationService {

	//private static final Logger logger = LoggerFactory.getLogger(AnalysisInformationServiceImpl.class);
	
	@Autowired
	private AnalysisInformationManager analysisInformationManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯基本浏览情况统计表失败")})
	public Response<PageInfo<AnalysisInformation>> queryForPage(PageInfo<AnalysisInformation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯基本浏览情况统计表失败")})
	public Response<AnalysisInformation> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯基本浏览情况统计表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯基本浏览情况统计表失败")})
	public Response<Void> save(AnalysisInformation analysisInformation) {
		return null;
	}
	
	/***
     * 1、编辑分栏目统计表
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param colunmsId  (非必填)栏目Id
     * @param userName   (非必填)编辑人员名称
     * @param startTime  (非必填)新闻发布开始时间
     * @param endTime    (非必填)新闻发布结束时间
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoColumnsList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询编辑分栏目统计失败")})
	public Response<List<AnalyInfoColumns>> queryAnalyInfoColumnsList(Long appId,Long colunmsId,String userName,Date startTime,Date endTime){
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoColumnsPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询编辑分栏目统计失败")})
	public Response<PageInfo<AnalyInfoColumns>> queryAnalyInfoColumnsPage(PageInfo<AnalyInfoColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	return null;
	}
	
    /***
     * 2、新闻详情数据
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param colunmsId  (非必填)栏目Id
     * @param userName   (非必填)编辑人员名称
     * @param title      (非必填)新闻标题
     * @param startTime  (非必填)新闻发布开始时间
     * @param endTime    (非必填)新闻发布结束时间
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoDetailList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询新闻详情数据失败")})
	public Response<List<AnalyInfoDetail>> queryAnalyInfoDetailList(Long appId,Long colunmsId,String userName,String title,Date startTime,Date endTime){
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoDetailPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询新闻详情数据失败")})
	public Response<PageInfo<AnalyInfoDetail>> queryAnalyInfoDetailPage(PageInfo<AnalyInfoDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
		return null;
	}
    
    /***
     * 3、编辑发布新闻情况统计
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param userName   (非必填)编辑人员名称
     * @param startTime  (非必填)统计--开始时间
     * @param endTime    (非必填)统计--结束时间
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoStatisticsList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询编辑发布新闻情况统计失败")})
	public Response<List<AnalyInfoStatistics>> queryAnalyInfoStatisticsList(Long appId,String userName,Date startTime,Date endTime){
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="queryAnalyInfoStatisticsPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询新闻详情数据失败")})
	public Response<PageInfo<AnalyInfoStatistics>> queryAnalyInfoStatisticsPage(PageInfo<AnalyInfoStatistics> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
		return null;
	}
	
	/**
     * Title:调用统计数据的存储过程
     * @author yuwu on 2016年6月25日
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="callProcInfoStatistics",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="调用统计数据的存储过程")})
	public Response<Void> callProcInfoStatistics(){
    	return null;
    }
}
