package com.cqliving.log.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cqliving.log.domain.BaseLog;

/**
 * com.CQLIVING.edu.log.aop.
 * User: wangyx
 * Date: 14-4-3
 * Time: 下午4:23
 */
@Service("eduLogAdviceExpand")
public class EduLogAdviceExpandImpl implements EduLogAdviceExpand {
    @Override
    public void expand(HttpServletRequest request, BaseLog baseLog) {
    	baseLog.setOperateUserId(null);
    	String token = request.getParameter("token");
    	if(token != null && StringUtils.isBlank(baseLog.getOperateUser())){
    		baseLog.setOperateUser(token);
    	}
    	String appId = request.getParameter("appId");
    	if(StringUtils.isNotBlank(appId)){
    		baseLog.setAppId(Long.parseLong(appId));
    	}
    }
}
