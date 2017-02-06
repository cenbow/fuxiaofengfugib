package com.cqliving.log.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.framework.utils.Reflections;
import com.cqliving.framework.utils.StringUtil;
import com.cqliving.log.aop.annotation.EduLog;
import com.cqliving.log.domain.BaseLog;
import com.cqliving.log.domain.LogOperate;
import com.cqliving.log.domain.LogPage;
import com.cqliving.log.service.LogOperateService;
import com.cqliving.log.service.LogPageService;
import com.cqliving.log.thread.ThreadPoolFactory;
import com.cqliving.log.thread.WorkEduLog;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EduLogControllerAdvice extends BaseLogControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(EduLogControllerAdvice.class);

	private String ignoreParameters;
	private LogPageService logPageService;
	private LogOperateService logOperateService;
	private String tag;//标识日志来源，例如：cloud-cms或cloud-web，代表项目名称
	private boolean paged;//{true:放到log_page表,false:放到log_operate表}
	private EduLogAdviceExpand eduLogAdviceExpand;

	private static List pageLogList = new CopyOnWriteArrayList<String>();
	private static List operateLogList = new CopyOnWriteArrayList<String>();

	private final int limit = 0;

	@Override
	protected void log(ProceedingJoinPoint pjp, Object result, long executeMilliseconds) {
		super.log(pjp, result, executeMilliseconds);
	}

	/**
	 * Spring3mvc 拦截日志处理
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object interceptEduLog(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		// 计算处理时间，注意這個處理時間因為是代理執行數據庫操作原因，可能存在誤差。
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		try {
			result = pjp.proceed();
		} catch (Exception e) {
			result = e;
			throw e;
		} finally {
			clock.stop(); // 计时结束
			long executeMilliseconds = clock.getTime();
			try {
				log(pjp, result, executeMilliseconds);
			} catch (Exception ex) {
				logger.error("edu log error", ex);
			}
		}
		return result;

	}

	/**
	 * <p>Description:</p>
	 * @param pjp
	 * @param target
	 * @param result
	 * @param methodName
	 * @param executeMilliseconds
	 * @see com.cqliving.log.aop.BaseLogControllerAdvice#writeLog(org.aspectj.lang.ProceedingJoinPoint, java.lang.Object, java.lang.Object, java.lang.String, long)
	 * @author tangqiang on 2015年5月19日
	 */
	@Override
	protected void writeLog(ProceedingJoinPoint pjp, Object target, Object result, String methodName,
			long executeMilliseconds) {
		Method method = Reflections.getAccessibleMethodByName(target, methodName);
		EduLog eduLog = method.getAnnotation(EduLog.class);
		BaseLog log = new LogPage();
		if (eduLog != null) {
			if (!eduLog.isNeedLog()) {//不需要记录日志则直接返回
				return;
			}
			log = buildRealLog(eduLog.paged());
			log.setTag(eduLog.tag());
			
			log.setModule(eduLog.module());
			log.setModuleName(eduLog.moduleName());
			log.setAction(eduLog.action());
			log.setActionName(eduLog.actionName());
		}else{
			log = buildRealLog(paged);
			log.setTag(tag);
		}
		// 收集日志信息，并持久化，如果出错忽略。
		log.setExecuteMilliseconds(executeMilliseconds);
		
		//设置默认值
		//Class<?>  cl = Reflections.getClassGenricType(target.getClass());
		if(StringUtil.isEmpty(log.getModule())){
			log.setModule(pjp.getTarget().getClass().getName());
		}
		if(StringUtil.isEmpty(log.getModuleName())){
			log.setModuleName(pjp.getTarget().getClass().getName());
		}
        if(StringUtil.isEmpty(log.getAction())){
        	log.setAction(methodName);
		}
        if(StringUtil.isEmpty(log.getActionName())){
    		log.setActionName(methodName);
		}
		log.setOperateTime(Calendar.getInstance().getTime());

		if (result instanceof Exception) {
			log.setOperateResult(2);
			log.setOperateMessage(result.getClass().getName());
		} else {
			log.setOperateResult(1);
		}
		try {
			HttpServletRequest request = getRequest(pjp.getArgs());
			log.setClientInformations(getClientInformations(request));
			handleParameters(request, target, methodName, pjp.getArgs(), log, eduLog);
			//用户session信息
			String sessionId = request.getParameter("sessionId");
			sessionId = StringUtils.isBlank(sessionId) ? request.getSession().getId():sessionId;
			log.setSessionId(sessionId);
			//备注
			log.setDes(getDescn(request));
			if (eduLogAdviceExpand != null) {
				eduLogAdviceExpand.expand(request, log);
			}
		} catch (Exception e) {
			logger.error("log request error:", e);
		}
		//有limit个日志后批量写入数据，线程执行
		addToLogList(log);
		//	        ologList.add(log);
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
			pageLogList.add(baseLog);
		} else {
			operateLogList.add(baseLog);
		}
	}

	/**
	 * <p>Description:根据paged实例化日志实体</p>
	 * @param baseLog
	 * @param paged
	 * @author tangqiang on 2015年1月9日
	 */
	private BaseLog buildRealLog(boolean paged) {
		if (paged) {
			return new LogPage();
		} else {
			return new LogOperate();
		}
	}

	protected void handleParameters(HttpServletRequest request, Object target, String methodName, Object args[],
			BaseLog baseLog, EduLog eduLog) {
		if (eduLog != null && !eduLog.needRecordParameter()) {
			return;
		}
		String requestParameters = Arrays.toString(args);
		if (request != null) {
			if(eduLog != null){
				requestParameters = getRequestParameters(request, target, methodName, eduLog.paramMapping(),
						eduLog.ignorParam(), eduLog.needRecordParameter(), requestParameters, args);
			}else{
				requestParameters = getRequestParameters(request, target, methodName, null,null, true, requestParameters, args);
			}
		}
		requestParameters = StringUtils.substring(requestParameters, 0, 256);
		baseLog.setRequestParameters(requestParameters);
	}

	public String getIgnoreAttribute() {
		return ignoreAttribute;
	}

	public void setIgnoreAttribute(String ignoreAttribute) {
		this.ignoreAttribute = ignoreAttribute;
	}

	public String getIgnoreParameters() {
		return ignoreParameters;
	}

	public void setIgnoreParameters(String ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}

	public LogPageService getLogPageService() {
		return logPageService;
	}

	public void setLogPageService(LogPageService logPageService) {
		this.logPageService = logPageService;
	}

	public void setEduLogAdviceExpand(EduLogAdviceExpand eduLogAdviceExpand) {
		this.eduLogAdviceExpand = eduLogAdviceExpand;
	}

	public LogOperateService getLogOperateService() {
		return logOperateService;
	}

	public void setLogOperateService(LogOperateService logOperateService) {
		this.logOperateService = logOperateService;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isPaged() {
		return paged;
	}

	public void setPaged(boolean paged) {
		this.paged = paged;
	}
}
