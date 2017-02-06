package com.cqliving.cloud.online.analysis.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.analysis.domain.AnalysisInformation;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoColumns;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoDetail;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoStatistics;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯基本浏览情况统计表 Service
 * Date: 2016-06-22 10:29:43
 * @author Code Generator
 */
public interface AnalysisInformationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<AnalysisInformation>> queryForPage(PageInfo<AnalysisInformation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<AnalysisInformation> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(AnalysisInformation domain);
	/** @author Code Generator *****end*****/
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
    public Response<List<AnalyInfoColumns>> queryAnalyInfoColumnsList(Long appId,Long colunmsId,String userName,Date startTime,Date endTime);
    public Response<PageInfo<AnalyInfoColumns>> queryAnalyInfoColumnsPage(PageInfo<AnalyInfoColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
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
    public Response<List<AnalyInfoDetail>> queryAnalyInfoDetailList(Long appId,Long colunmsId,String userName,String title,Date startTime,Date endTime);
    public Response<PageInfo<AnalyInfoDetail>> queryAnalyInfoDetailPage(PageInfo<AnalyInfoDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
    
    /***
     * 3、编辑发布新闻情况统计
     * @author yuwu on 2016年06月21日
     * @param appId      (必填)客户端ID
     * @param userName   (非必填)编辑人员名称
     * @param startTime  (非必填)统计--开始时间
     * @param endTime    (非必填)统计--结束时间
     * @return
     */
    public Response<List<AnalyInfoStatistics>> queryAnalyInfoStatisticsList(Long appId,String userName,Date startTime,Date endTime);
    public Response<PageInfo<AnalyInfoStatistics>> queryAnalyInfoStatisticsPage(PageInfo<AnalyInfoStatistics> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);

    /**
     * Title:调用统计数据的存储过程
     * @author yuwu on 2016年6月25日
     * @return
     */
    public Response<Void> callProcInfoStatistics();
    
}
