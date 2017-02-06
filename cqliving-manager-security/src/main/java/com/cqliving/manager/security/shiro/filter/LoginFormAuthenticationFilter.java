package com.cqliving.manager.security.shiro.filter;

import com.cqliving.manager.security.listener.LoginLogoutListener;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 *
 * 登陆拦截器
 */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(LoginFormAuthenticationFilter.class);

    private List<LoginLogoutListener> listeners;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果是登陆页返回true
        return false == super.isLoginRequest(request, response);
        //return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }

        //登陆前回调
        if(listeners!=null) {
            for (LoginLogoutListener listener : listeners) {
                try {
                    listener.beforeLogin(token.getPrincipal().toString(), (HttpServletRequest) request, (HttpServletResponse) response);
                } catch (Exception e) {
                    throw e;
                }
            }
        }

        Subject subject = getSubject(request, response);
        try {
            subject.login(token);
            //登陆成功
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            if(null == subject.getSession().getAttribute("loginErrorNum")) {
                subject.getSession().setAttribute("loginErrorNum", 1);
            } else {
                int errorNum = (Integer) subject.getSession().getAttribute("loginErrorNum");
                subject.getSession().setAttribute("loginErrorNum", errorNum+1);
            }
            //登陆失败
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //登陆成功回调
        if(listeners!=null) {
            for (LoginLogoutListener listener : listeners) {
                try {
                    listener.onLoginSuccess(token.getPrincipal().toString(), (HttpServletRequest) request, (HttpServletResponse) response);
                } catch (Exception e) {
                    logger.error("listener callback on login success error", e);
                }
            }
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //登陆失败的处理 e 表示登陆的异常
        //异常转换
        com.cqliving.manager.security.exception.AuthenticationException ex = new com.cqliving.manager.security.exception.AuthenticationException(e);
        if(e instanceof org.apache.shiro.authc.UnknownAccountException){
            ex = new com.cqliving.manager.security.exception.UnknownAccountException(e);
        } else if(e instanceof org.apache.shiro.authc.IncorrectCredentialsException){
            ex = new com.cqliving.manager.security.exception.IncorrectCredentialsException(e);
        }

        //登陆失败回调
        if(listeners!=null) {
            for (LoginLogoutListener listener : listeners) {
                try {
                    listener.onLoginFailure(token.getPrincipal().toString(), ex, (HttpServletRequest) request, (HttpServletResponse) response);
                } catch (Exception e1) {
                	e1.printStackTrace();
                    logger.error("listener callback on login failure error", e1);
                }
            }
        }
        return super.onLoginFailure(token, e, request, response);
    }

    public List<LoginLogoutListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<LoginLogoutListener> listeners) {
        this.listeners = listeners;
    }
}
