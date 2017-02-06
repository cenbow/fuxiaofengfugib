package com.cqliving.manager.security.shiro.filter;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Title:如果用户未登陆，且访问时是AJAX操作，则返回特定标识跳转
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author yuwu on 2016年5月3日
 */
public class PassThruAuthenticationFilter extends AuthenticationFilter {

    private Integer ajaxNotAuthCode = 999992;

    private String ajaxNotAuthMsg="您尚未登录或登录时间过长,请重新登录!";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;  
    	
    	Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
    	logger.info("当前访问的会话session------>>>>>>>>>{}",session);
        Object appInfo = session.getAttribute("session_user_obj");
        logger.info("当前访问的登录对象------>>>>>>>>>{}",appInfo);
        
    	if (isLoginRequest(request, response)) {
            logger.info("isLoginRequest11111------>>>>>>>>>");
            return true;
        } else {
        	if ((httpRequest.getHeader("x-requested-with") != null && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
                    || (httpRequest.getRequestURI()!=null && httpRequest.getRequestURI().indexOf("ajax")!=-1)) {
                /*PrintWriter printWriter = response.getWriter();
                printWriter.write(JsonMapper.nonDefaultMapper().toJson(new Response<Void>(ajaxNotAuthCode, ajaxNotAuthMsg)));
                printWriter.flush();
                printWriter.close();*/
                logger.info("isLoginRequest22222------>>>>>>>>>");
                PrintWriter printWriter = response.getWriter();
                httpRequest.getSession().setAttribute("refer",httpRequest.getHeader("referer"));
				response.setContentType("text/html;charset=UTF-8");
				printWriter.print("999992");
				printWriter.flush();
                printWriter.close();
            }else {  
                logger.info("isLoginRequest3333333------>>>>>>>>>");
                saveRequestAndRedirectToLogin(request, response);  
            }  
        	return false;
        }
    }

    public Integer getAjaxNotAuthCode() {
		return ajaxNotAuthCode;
	}

	public void setAjaxNotAuthCode(Integer ajaxNotAuthCode) {
		this.ajaxNotAuthCode = ajaxNotAuthCode;
	}

	public String getAjaxNotAuthMsg() {
        return ajaxNotAuthMsg;
    }

    public void setAjaxNotAuthMsg(String ajaxNotAuthMsg) {
        this.ajaxNotAuthMsg = ajaxNotAuthMsg;
    }
}
