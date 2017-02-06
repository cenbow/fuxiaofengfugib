package com.cqliving.cloud.online.analysis.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.analysis.dao.AnalysisInformationDao;
import com.cqliving.cloud.online.analysis.dao.AnalysisInformationDaoCustom;
import com.cqliving.cloud.online.analysis.domain.AnalysisInformation;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoColumns;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoDetail;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoStatistics;
import com.cqliving.cloud.online.analysis.manager.AnalysisInformationManager;
import com.cqliving.cloud.online.info.manager.InformationManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("analysisInformationManager")
public class AnalysisInformationManagerImpl extends EntityServiceImpl<AnalysisInformation, AnalysisInformationDao> implements AnalysisInformationManager {
	@Autowired
	AnalysisInformationDaoCustom analysisInformationDaoCustom;
	@Autowired
	InformationManager informationManager;
	
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
    public List<AnalyInfoColumns> queryAnalyInfoColumnsList(Long appId,Long colunmsId,String userName,Date startTime,Date endTime){
    	return analysisInformationDaoCustom.queryAnalyInfoColumnsList(appId, colunmsId, userName, startTime, endTime);
    }
    @Override
    public PageInfo<AnalyInfoColumns> queryAnalyInfoColumnsPage(PageInfo<AnalyInfoColumns> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	return analysisInformationDaoCustom.queryAnalyInfoColumnsPage(pageInfo, map, orderMap);
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
    public List<AnalyInfoDetail> queryAnalyInfoDetailList(Long appId,Long colunmsId,String userName,String title,Date startTime,Date endTime){
    	return analysisInformationDaoCustom.queryAnalyInfoDetailList(appId, colunmsId, userName, title, startTime, endTime);
    }
    @Override
    public PageInfo<AnalyInfoDetail> queryAnalyInfoDetailPage(PageInfo<AnalyInfoDetail> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	
    	pageInfo = analysisInformationDaoCustom.queryAnalyInfoDetailPage(pageInfo, map, orderMap);
    	return pageInfo;
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
    public List<AnalyInfoStatistics> queryAnalyInfoStatisticsList(Long appId,String userName,Date startTime,Date endTime){
    	return analysisInformationDaoCustom.queryAnalyInfoStatisticsList(appId, userName, startTime, endTime);
    }
    @Override
    public PageInfo<AnalyInfoStatistics> queryAnalyInfoStatisticsPage(PageInfo<AnalyInfoStatistics> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap){
    	return analysisInformationDaoCustom.queryAnalyInfoStatisticsPage(pageInfo, map, orderMap);
    }
    
    /**
     * Title:调用统计数据的存储过程
     * @author yuwu on 2016年6月25日
     * @return
     */
    @Override
    public void callProcInfoStatistics(){
    	this.analysisInformationDaoCustom.callProcInfoStatistics();
    }
}
