package com.cqliving.manager.security.listener;

import com.cqliving.manager.security.exception.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/26.
 *
 * 登陆注销监听
 *
 */
public interface LoginLogoutListener {


    /**
     * 登陆前回调
     * @param userName 用户名
     * @param request
     * @param response
     */
    void beforeLogin(String userName, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException;

    /**
     * 登陆成功回调
     * @param userName 用户名
     * @param request
     * @param response
     */
    void onLoginSuccess(String userName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登陆失败回调
     * @param userName 用户名
     * @param e 登陆异常信息
     * @param request
     * @param response
     */
    void onLoginFailure(String userName, AuthenticationException e, HttpServletRequest request, HttpServletResponse response);

    /**
     * 记住我登陆后回调
     * @param userName 用户名
     * @param request
     * @param response
     */
    void afterRememberMe(String userName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 注销前回调
     * @param userName
     * @param request
     * @param response
     */
    void beforeLogout(String userName, HttpServletRequest request, HttpServletResponse response);
}
