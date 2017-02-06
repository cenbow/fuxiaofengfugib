package com.cqliving.cloud.controller.module.analysis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.support.json.JSONUtils;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.account.dto.SmsStatisticsDto;
import com.cqliving.cloud.online.account.service.UserSmsLogService;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoColumns;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoDetail;
import com.cqliving.cloud.online.analysis.dto.AnalyInfoStatistics;
import com.cqliving.cloud.online.analysis.service.AnalysisInformationService;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.encrypt.Crypt;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/analysis")
public class AnalysisInformationController extends CommonController {

    @Autowired
    private AnalysisInformationService analysisInformationService;
	@Autowired
	private AppColumnsService appColumnsService;
	@Autowired
	private UserSmsLogService userSmsLogService;
	
	/**
	 * Title:1、编辑分栏目统计表
	 * @author yuwu on 2016年6月24日
	 * @return
	 */
    @RequestMapping(value ="analy_info_columns_list")
    public String listAnalyInfoColumns(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	,@RequestParam(value = "search_EQ_columnsId", required = false) Long columnsId
    	,@RequestParam(value = "search_LIKE_nickname", required = false) String nickname
        ,@RequestParam(value="search_GTE_onlineTime", required=false) Date search_GTE_onlineTime
        ,@RequestParam(value="search_LT_onlineTime", required=false) Date search_LT_onlineTime
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ,@RequestParam(value="isDownload", required = false) String isDownload
    	) {
    	
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        //sortMap.put("info_count", Boolean.FALSE);
        
        //必须手动将所有的参数放置进来，因为SQL查询中参数是用占位符的方式，如果没有参数名会报错
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("EQ_columnsId", columnsId);
        searchMap.put("LIKE_nickname", nickname);
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("EQ_appId", sessionUser.getAppId());
        //默认新闻发布时间范围3个月
        search_GTE_onlineTime = search_GTE_onlineTime != null ? search_GTE_onlineTime:DateUtils.addMonths(DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE), -3);
        map.put("search_GTE_onlineTime", search_GTE_onlineTime);
        searchMap.put("GTE_onlineTime", search_GTE_onlineTime);
        //结束时间，查询时用的小于等于号（<=）
        search_LT_onlineTime = search_LT_onlineTime != null ?search_LT_onlineTime: DateUtils.addSeconds(DateUtils.addDays(DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE), 1),-1);
        map.put("search_LT_onlineTime", search_LT_onlineTime);
        searchMap.put("LT_onlineTime", search_LT_onlineTime);
        
        
        //默认统计时间范围1天
        //开始时间
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        //结束时间，查询时用的小于等于号（<=）
        search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: DateUtils.addSeconds(DateUtils.truncate(DateUtils.addDays(search_GTE_createTime, 1), Calendar.DATE),-1);
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", search_LT_createTime);
        
        
        PageInfo<AnalyInfoColumns> pageInfo = getPageInfo(request);
        //导出明细数据
        if("true".equals(isDownload)){
        	//设置一次查询20000条
        	pageInfo.setCountOfCurrentPage(20000);
        	PageInfo<AnalyInfoColumns> page = analysisInformationService.queryAnalyInfoColumnsPage(pageInfo, searchMap, sortMap).getData();
        	//List<AnalyInfoColumns> list = analysisInformationService.queryAnalyInfoColumnsList(sessionUser.getAppId(), columnsId, nickname, search_GTE_onlineTime, search_LT_onlineTime).getData();
        	List<AnalyInfoColumns> list = page.getPageResults();
            String exportFileName = "编辑分栏目统计表";
            ExcelEntityUtil.doExportForXls(response, exportFileName, list);
            return null;
        }else{
        	map.put("pageInfo", analysisInformationService.queryAnalyInfoColumnsPage(pageInfo, searchMap, sortMap).getData());
        	this.setAppColumns(sessionUser,map);
            //查询按钮和点击页面是ajax操作。
            if(StringUtils.isNotBlank(isAjaxPage)){
            	return "/module/analysis/analy_info_columns_list_page";
            }else{
            	return "tiles.module.analysis.analy_info_columns_list";
            }
        }
    }
    
    /**
	 * Title:2、新闻详情统计表
	 * @author yuwu on 2016年6月24日
	 * @return
	 */
    @RequestMapping(value ="analy_info_detail_list")
    public String listAnalyInfoDetail(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	,@RequestParam(value = "search_EQ_columnsId", required = false) Long columnsId
    	,@RequestParam(value = "search_LIKE_nickname", required = false) String nickname
    	,@RequestParam(value = "search_LIKE_title", required = false) String title
        ,@RequestParam(value="search_GTE_onlineTime", required=false) Date search_GTE_onlineTime
        ,@RequestParam(value="search_LT_onlineTime", required=false) Date search_LT_onlineTime
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ,@RequestParam(value="isDownload", required = false) String isDownload
    	) {
    	
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        //sortMap.put("online_time", Boolean.FALSE);
        
        //必须手动将所有的参数放置进来，因为SQL查询中参数是用占位符的方式，如果没有参数名会报错
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("EQ_columnsId", columnsId);
        searchMap.put("LIKE_nickname", nickname);
        searchMap.put("LIKE_title", title);
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("EQ_appId", sessionUser.getAppId());
        //默认新闻发布时间范围3个月
        search_GTE_onlineTime = search_GTE_onlineTime != null ? search_GTE_onlineTime:DateUtils.addMonths(DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE), -3);
        map.put("search_GTE_onlineTime", search_GTE_onlineTime);
        searchMap.put("GTE_onlineTime", search_GTE_onlineTime);
        //结束时间，查询时用的小于等于号（<=）
        search_LT_onlineTime = search_LT_onlineTime != null ?search_LT_onlineTime: DateUtils.addSeconds(DateUtils.addDays(DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE), 1),-1);
        map.put("search_LT_onlineTime", search_LT_onlineTime);
        searchMap.put("LT_onlineTime", search_LT_onlineTime);
        
        
        //默认统计时间范围1天
        //开始时间
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.truncate(Calendar.getInstance().getTime(), Calendar.DATE);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        //结束时间，查询时用的小于等于号（<=）
        search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: DateUtils.addSeconds(DateUtils.truncate(DateUtils.addDays(search_GTE_createTime, 1), Calendar.DATE),-1);
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", search_LT_createTime);
        
        PageInfo<AnalyInfoDetail> pageInfo = getPageInfo(request);
        //导出明细数据
        if("true".equals(isDownload)){
        	//设置一次查询20000条
        	pageInfo.setCountOfCurrentPage(20000);
        	PageInfo<AnalyInfoDetail> page = analysisInformationService.queryAnalyInfoDetailPage(pageInfo, searchMap, sortMap).getData();
        	//List<AnalyInfoColumns> list = analysisInformationService.queryAnalyInfoColumnsList(sessionUser.getAppId(), columnsId, nickname, search_GTE_onlineTime, search_LT_onlineTime).getData();
        	List<AnalyInfoDetail> list = page.getPageResults();
            String exportFileName = "新闻详情数据统计表";
            ExcelEntityUtil.doExportForXls(response, exportFileName, list);
            return null;
        }else{
        	map.put("pageInfo", analysisInformationService.queryAnalyInfoDetailPage(pageInfo, searchMap, sortMap).getData());
        	this.setAppColumns(sessionUser,map);
            //查询按钮和点击页面是ajax操作。
            if(StringUtils.isNotBlank(isAjaxPage)){
            	return "/module/analysis/analy_info_detail_list_page";
            }else{
            	return "tiles.module.analysis.analy_info_detail_list";
            }
        }
    }
    
    
    /**
	 * Title:3、编辑发布新闻情况统计
	 * @author yuwu on 2016年6月24日
	 * @return
	 */
    @RequestMapping(value ="analy_info_statistics_list")
    public String listAnalyInfoStatistics(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	,@RequestParam(value = "search_EQ_columnsId", required = false) Long columnsId
    	,@RequestParam(value = "search_LIKE_nickname", required = false) String nickname
        ,@RequestParam(value="isDownload", required = false) String isDownload
    	) {
    	
		Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("viewTotalCount", Boolean.FALSE);
        sortMap.put("info_count", Boolean.FALSE);
        
        //必须手动将所有的参数放置进来，因为SQL查询中参数是用占位符的方式，如果没有参数名会报错
        Map<String, Object> searchMap = Maps.newHashMap();
        searchMap.put("LIKE_nickname", nickname);
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("EQ_appId", sessionUser.getAppId());
        
        //开始时间
        String search_GTE_onlineTime_str = request.getParameter("search_GTE_onlineTime");
        //默认开始时间为结束时间
        Date search_GTE_onlineTime = StringUtils.isBlank(search_GTE_onlineTime_str) ?DateUtils.truncate(DateUtil.add(Calendar.getInstance().getTime(), Calendar.DAY_OF_MONTH, -1), Calendar.DATE): DateUtil.parse(search_GTE_onlineTime_str,DateUtil.YYYY_MM_DD);
        map.put("search_GTE_onlineTime", search_GTE_onlineTime);
        searchMap.put("GTE_onlineTime", search_GTE_onlineTime);
        
        //结束时间
        String search_LT_onlineTime_str = request.getParameter("search_LT_onlineTime");
        //默认结束时间选择为昨天
        Date search_LT_onlineTime = StringUtils.isBlank(search_LT_onlineTime_str) ?DateUtils.truncate(DateUtil.add(Calendar.getInstance().getTime(), Calendar.DAY_OF_MONTH, -1), Calendar.DATE): DateUtil.parse(search_LT_onlineTime_str,DateUtil.YYYY_MM_DD);
        map.put("search_LT_onlineTime", search_LT_onlineTime);
        //查询时用的小于号（<）,所以当前天加1
        searchMap.put("LT_onlineTime", DateUtils.addDays(search_LT_onlineTime, 1));
        
        PageInfo<AnalyInfoStatistics> pageInfo = getPageInfo(request);
        //导出明细数据
        if("true".equals(isDownload)){
        	//设置一次查询最大值，获取所有数据
        	//设置一次查询20000条
        	pageInfo.setCountOfCurrentPage(20000);
        	PageInfo<AnalyInfoStatistics> page = analysisInformationService.queryAnalyInfoStatisticsPage(pageInfo, searchMap, sortMap).getData();
        	//List<AnalyInfoColumns> list = analysisInformationService.queryAnalyInfoColumnsList(sessionUser.getAppId(), columnsId, nickname, search_GTE_onlineTime, search_LT_onlineTime).getData();
        	List<AnalyInfoStatistics> list = page.getPageResults();
            String exportFileName = "编辑分栏目统计表";
            ExcelEntityUtil.doExportForXls(response, exportFileName, list);
            return null;
        }else{
        	map.put("pageInfo", analysisInformationService.queryAnalyInfoStatisticsPage(pageInfo, searchMap, sortMap).getData());
            //查询按钮和点击页面是ajax操作。
            if(StringUtils.isNotBlank(isAjaxPage)){
            	return "/module/analysis/analy_info_statistics_list_page";
            }else{
            	return "tiles.module.analysis.analy_info_statistics_list";
            }
        }
    }
    
    /**
     * <p>Description: 短信统计</p>
     * @author Tangtao on 2016年10月9日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping({"sms_statistics"})
    public String smsStatistics(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	//查询短信余额等信息
		String smsUrl = PropertiesConfig.getString(PropertyKey.SMS_INTERFACE_QUERY_URL);
		if (StringUtils.isBlank(smsUrl)) {
			throw new BusinessException(
					ErrorCodes.SmsErrorEnum.SMS_URL_NOT_EXIST.getCode(), 
					ErrorCodes.SmsErrorEnum.SMS_URL_NOT_EXIST.getDesc());
		}
		Date now = DateUtil.now();
		String smsAccount = PropertiesConfig.getString(PropertyKey.SMS_ACCOUNT);
		String smsPwd = PropertiesConfig.getString(PropertyKey.SMS_PWD);
		String smsUserId = PropertiesConfig.getString(PropertyKey.SMS_USERID);
		String timestamp = DateUtil.format(now, DateUtil.YYYYMMDDHHMMSS);
		String sign = Crypt.MD5(smsAccount + smsPwd + timestamp).toLowerCase();
		Map<String, String> params = Maps.newHashMap();
		params.put("userId", smsUserId);
		params.put("sign", sign);
		params.put("timestamp", timestamp);
		String result = HttpClientUtil.sendPostRequest(smsUrl, params, null, null);
//		String result = "{\"code\":0,\"message\":\"\",\"data\":{\"overage\":283156,\"sendTotal\":285757},\"sessionId\":\"\"}";
		
		//处理返回结果
		Map<String, Object> jsonResult = (Map<String, Object>) JSONUtils.parse(result);
		if (jsonResult.get("code").equals(0)) {	//调用成功
			Map<String, Object> data = (Map<String, Object>) jsonResult.get("data");
			String overage = data.get("overage").toString();
			String sendTotal = data.get("sendTotal").toString();
			map.put("overage", overage);
			map.put("sendTotal", sendTotal);
		}
		
    	//统计各 app 各类短信发送量
		PageInfo<SmsStatisticsDto> pageInfo = getPageInfo(request);
		Response<PageInfo<SmsStatisticsDto>> response = userSmsLogService.getStatistic(pageInfo);
		map.put("pageInfo", response.getData());
		if (StringUtils.isNotBlank(isAjaxPage)) {
         	return "/module/analysis/sms_statistics_page";
		} else {
			return "tiles.module.analysis.sms_statistics";
		}
    }
    		
    
    /**
     * Title:设置栏目查询条件
     * @author yuwu on 2016年6月24日
     * @param sessionUser
     * @param map
     */
    private void setAppColumns(SessionUser sessionUser,Map<String, Object> map){
    	//APP栏目选择
    	Map<String, Object> searchMap = Maps.newHashMap();
    	searchMap.put("EQ_appId",sessionUser.getAppId());
    	searchMap.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
    	String appColumnsJson = new JsonMapper().toJson(appColumnsService.getByConditions(searchMap));
    	map.put("appColumns", appColumnsJson);
    }
    
}
