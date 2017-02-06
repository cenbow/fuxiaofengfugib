/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.framework.utils.StringUtil;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.log.domain.BaseLog;
import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.domain.LogPage;
import com.cqliving.log.service.LogOperateService;
import com.cqliving.log.service.LogPageService;
import com.cqliving.log.thread.ThreadPoolFactory;
import com.cqliving.log.thread.WorkEduLog;
import com.cqliving.log.util.RemoteAdressTools;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2015年11月26日
 */
@Component
public class CloudLogService {

	private static List<LogPage> pageLogList = new CopyOnWriteArrayList<LogPage>();
	private static List<LogOperate> operateLogList = new CopyOnWriteArrayList<LogOperate>();

	private final int limit = 0;
	@Autowired
	private LogPageService logPageService;
	@Autowired
	private LogOperateService logOperateService;

	private static final Logger logger = LoggerFactory.getLogger(CloudLogService.class);
	
	
	public void log(HttpServletRequest request,BaseLog log){
		
		log.setClientInformations(getClientInformations(request));
		log.setDes(getDescn(request,log));
		log.setRequestParameters(this.getRequestParams(request));
		this.saveLog(log);
	};
	
	public void log(HttpServletRequest request,EduLog eduLog,Class<?> clazz,String methodName,Long operateId,String operateName){
		if (eduLog == null || !eduLog.isNeedLog()) {//日志为空或者不需要记录日志则直接返回
			return;
		}
		BaseLog log = buildRealLog(eduLog.paged());
		// 收集日志信息，并持久化，如果出错忽略。
		log.setTag(eduLog.tag());
		log.setModule(eduLog.module());
		log.setModuleName(eduLog.moduleName());
		log.setAction(eduLog.action());
		log.setActionName(eduLog.actionName());
		//设置默认值
		if(StringUtil.isEmpty(eduLog.module())){
			log.setModule(clazz.getName());
			log.setModuleName(clazz.getName());
		}
        if(StringUtil.isEmpty(eduLog.action())){
        	log.setAction(methodName);
    		log.setActionName(methodName);
		}
		log.setOperateTime(Calendar.getInstance().getTime());
		log.setOperateResult(1);
		
		try {
			log.setClientInformations(getClientInformations(request));
			//用户session信息
			log.setSessionId(request.getSession().getId());
			log.setOperateUser(operateName);
			log.setOperateUserId(operateId);
			//备注
			log.setDes(getDescn(request,log));
			
			log.setRequestParameters(this.getRequestParams(request));
		} catch (Exception e) {
			logger.error("log request error:", e);
		}
		//有limit个日志后批量写入数据，线程执行
		Long preTime = (Long) request.getAttribute("preTime");
		log.setExecuteMilliseconds(System.currentTimeMillis() - preTime.longValue());
		this.saveLog(log);
	}
	
	public void saveLog(BaseLog log){
		addToLogList(log);
		if (pageLogList.size() > limit) {
			List<LogPage> writeList = new ArrayList<LogPage>(pageLogList);
			pageLogList.clear();
			ThreadPoolFactory.get().execute(new WorkEduLog(logPageService, writeList));
		}
		if (operateLogList.size() > limit) {
			List<LogOperate> writeList = new ArrayList<LogOperate>(operateLogList);
			operateLogList.clear();
			ThreadPoolFactory.get().execute(new WorkEduLog(logOperateService, writeList));
		}
	}
	
	/**
	 * <p>Description:根据类型加入不同的列表中</p>
	 * @param baseLog
	 * @author tangqiang on 2015年1月9日
	 */
	private void addToLogList(BaseLog baseLog) {
		if (baseLog instanceof LogPage) {
			pageLogList.add((LogPage) baseLog);
		} else {
			operateLogList.add((LogOperate) baseLog);
		}
	}

	/**
	 * <p>Description:根据paged实例化日志实体</p>
	 * @param baseLog
	 * @param paged
	 * @author tangqiang on 2015年1月9日
	 */
	private static BaseLog buildRealLog(boolean paged) {
		if (paged) {
			return new LogPage();
		} else {
			return new LogOperate();
		}
	}
	
	/**
	 * 获取请求客户端信息
	 * 
	 * @param request
	 * @return
	 */
	protected String getClientInformations(HttpServletRequest request) {
		if (request == null)
			return "";
		String clientIP = RemoteAdressTools.getRemoteAddrIp(request);
		String requestUserAgent = request.getHeader("User-Agent");
		if(requestUserAgent == null || requestUserAgent.equals("")){
			return "";
		}
		UserAgent userAgent = UserAgent.parseUserAgentString(requestUserAgent);
		OperatingSystem os = userAgent.getOperatingSystem();
		Browser browser = userAgent.getBrowser();
		String clientInfo = clientIP + " - " + os.getName() + " - "
				+ browser.getName() + "/" + browser.getBrowserType().getName();
		return clientInfo;
	}
	
	protected String getDescn(HttpServletRequest request,BaseLog baseLog){
		if(request == null) return "";
		String requestURI = request.getRequestURI();
		String ref = request.getParameter("ref");
		String referer = request.getHeader("referer");
		if ((ref == null || "".equals(ref.trim())) && (referer != null && referer.indexOf(request.getServerName()) != -1 )) {
			ref = referer;
		}
		ref=ref==null?"":ref;
		String desc = "request:"+requestURI+" && ref:"+ref;
		if(desc.length()>=201)desc = desc.substring(0, 200);
		
		if(!StringUtil.isEmpty(ref) && ref.length() >=200)ref = ref.substring(0,199);
		baseLog.setRefer(ref);
		return desc;
	}
	
	protected String getRequestParams(HttpServletRequest request){
		
		if(null == request) return null;
		Map<String,String[]> params = request.getParameterMap();
		String requestParam = JSONObject.toJSONString(params);
		if(requestParam.length()>=2001){
			requestParam = requestParam.substring(0,2000);
		}
		return requestParam;
	}
}
