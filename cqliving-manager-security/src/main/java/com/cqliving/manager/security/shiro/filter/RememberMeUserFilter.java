package com.cqliving.manager.security.shiro.filter;

import com.cqliving.manager.security.listener.LoginLogoutListener;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by Administrator on 2015/5/26.
 *
 * 记住我登陆的回调过滤器
 *
 */
public class RememberMeUserFilter extends UserFilter {

    //记住我登陆回调的session标识， 确保只回调一次
    public final static String CQLIVING_SECURITY2_REMEMBER_ME_LOGIN="CQLIVING_security2_remember_me_login";

    private Logger logger = LoggerFactory.getLogger(RememberMeUserFilter.class);

    private List<LoginLogoutListener> listeners;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);

        if(subject.isRemembered() && subject.getSession().getAttribute(CQLIVING_SECURITY2_REMEMBER_ME_LOGIN)==null) {
            logger.debug("principal {}, call listeners after remember me");
            //设置自动登陆回调
            if(listeners!=null) {
                for (LoginLogoutListener listener : listeners) {
                    try {
                        listener.afterRememberMe(subject.getPrincipal().toString(), (HttpServletRequest) request, (HttpServletResponse) response);
                    } catch (Exception ex) {
                        logger.error("listener callback after remember me error", ex);
                    }
                }
            }
            subject.getSession().setAttribute(CQLIVING_SECURITY2_REMEMBER_ME_LOGIN, CQLIVING_SECURITY2_REMEMBER_ME_LOGIN);
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    public List<LoginLogoutListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<LoginLogoutListener> listeners) {
        this.listeners = listeners;
    }
}
