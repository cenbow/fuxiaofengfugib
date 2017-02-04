package com.org.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cqliving.tool.common.util.SpringUtil;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.date.DateUtil;
import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.common.Constants;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.client.api.SnsAPI;
import com.org.weixin.module.szc.constant.SzcConstant;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.dto.AccInfoDto;
import com.org.weixin.system.dto.ModuleInfoDto;

/**
 * Title:微信模块认证拦截器
 * <p>Description:微信认证拦截器</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月12日
 */
public class WeixinAuthFilter extends OncePerRequestFilter {

	private static Logger logger = LoggerFactory.getLogger(WeixinAuthFilter.class);
	
	@Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
   		SpyMemcachedClient spyMemcachedClient = (SpyMemcachedClient) SpringUtil.getBean("spyMemcachedClient");
   		String queryStr = request.getQueryString();
   		String district = request.getParameter("district");
		// 需要过滤的uri
		Map<Long,ModuleInfoDto> filterMap = spyMemcachedClient.get(Constants.Weixin.WEIXIN_FILTER_MAP);
		
		if(StringUtil.isEmpty(district))district = String.valueOf(SzcConstant.HEFEI);
		
        if(null == filterMap){
        	filterChain.doFilter(request, response);
        	return;
        }
        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = false;
        //模块id
        Long mId = null;
        
        byte status = (byte)0;
        ModuleInfoDto nowModule = null;
        //URL不存在过滤器里
        for (Entry<Long, ModuleInfoDto> filter : filterMap.entrySet()) {
            if (uri.indexOf(filter.getValue().getUrl()) != -1) {
            	//获取当前的Module
            	nowModule = filter.getValue();
                // 如果uri中包含过滤的uri，则进行过滤
            	status = nowModule.getStatus();            	
            	mId = filter.getKey();
                doFilter = true;
                break;
            }
        }
        if(uri.indexOf("/yfgroup/")!=-1){
            filterChain.doFilter(request, response);
        }else if (!doFilter) {
        	 // this.setSessionUser(request);
            filterChain.doFilter(request, response);
        } else {
        	//正常开始
    		boolean isStart = false;
    		//正常结束
     		boolean isnotEnd = false;
     		
     		if (nowModule.getStartTime() == null || DateUtil.now().after(nowModule.getStartTime())) {
     			isStart = true;
     		}
     		if (nowModule.getEndTime() == null || nowModule.getEndTime().after(DateUtil.now())) {
     			isnotEnd = true;
     		}
     		//正常活动时间
     		if (isStart&& isnotEnd) {//正常活动时间
            	if (status == ModuleInfoDto.STATUE_WEIXIN_TRUE) {
                	//获取公众号Id（要拦截必须按照：【域名/模块/公众号id/方法.html】才可拦截成功！）
                	Long accId = Long.valueOf(uri.split("/")[2]);
                	//将公众账号放入session
            		SessionFace.setAttribute(request, "accId", accId);
                    // 从session中获取登录者实体
                	SessionUser sessionUser = SessionFace.getSessionUser(request);
            		StringBuffer sb = request.getRequestURL(); 
            		logger.info("用户访问URL地址：url："+sb.toString());
                	//用户未认证或者公众账号不一致或者模块不一致，重新认证
                    if (null == sessionUser || !accId.equals(sessionUser.getAccId()) || !mId.equals(sessionUser.getModuleId())) {
                    	//如果已经验证过，将带有openId,从缓存里面取出用户信息
                    	String openId = (String) request.getParameter("openId");
                    	if(StringUtils.isNotBlank(openId)){
                    		SysWechatUser user = spyMemcachedClient.get(Constants.Weixin.WEIXIN_CACHE_USER + openId);
                    		
                    		logger.info("-------------在缓存里边找到的微信用户：{}",user);
                    		
                    		//取到用户信息
                    		if(null != user){
                    			sessionUser = new  SessionUser();
                    			BeanUtils.copyProperties(user, sessionUser);
                    			sessionUser.setAccId(user.getAccountId());
                    			//放入session
                    			sessionUser.setDistrict(StringUtil.stringToInteger(district));
                    			SessionFace.setSessionUser(request, sessionUser);
                    			//从缓存里面删除用户信息
                    			spyMemcachedClient.delete(Constants.Weixin.WEIXIN_CACHE_USER + openId);
                    			filterChain.doFilter(request, response);
                    			return;
                    		}
                    	}
                        // 未找到认证信息，跳转到微信授权认证界面
                    	Map<Long,AccInfoDto> accInfoMap = spyMemcachedClient.get(Constants.Weixin.WEIXIN_ACC_MAP);
                    	if(null == accInfoMap || null == accId || null == accInfoMap.get(accId)){
                    		logger.error("公众账号未启用或配置错误！accId:{}",accInfoMap,accId);
                    	    //this.setSessionUser(request,district);
                    		filterChain.doFilter(request, response);
                    		return;
                    	} 
                		//动态生成跳转链接
                		sb.delete(sb.length() - uri.length(), sb.length()).append("/weixin/auth2Service/").append(accId+"/").append(mId).append(".html");
                		if(!StringUtil.isEmpty(queryStr)){
                			sb.append("?").append(queryStr);
                		}
                		//TODO Tangtao SysModules增加是否需要用户授权字段，使用此字段进行判断
//                		if (uri.indexOf("/zjchj/") >= 0) {	//静默授权
//                			url = SnsAPI.connectOauth2Authorize(accInfoMap.get(accId).getAppid(), rurl, false, uri);
//						} else {	//用户授权
                		 String	url = SnsAPI.connectOauth2Authorize(accInfoMap.get(accId).getAppid(), sb.toString(), true, uri);
//						}
                		response.sendRedirect(url);
                    } else {
                        // 如果session中存在登录者实体，则继续
                    	sessionUser.setDistrict(StringUtil.stringToInteger(district));
                    	logger.info("-------->>>>>>>在filter修改区域后的登录用户：{}",sessionUser);
                    	SessionFace.setSessionUser(request, sessionUser);
                        filterChain.doFilter(request, response);
                    }
                } else if (status == ModuleInfoDto.STATUE_WEIXIN_DEL) {                	
                	response.sendRedirect("/common/pages/404.htm");
                	return ;
            	} else {
            		filterChain.doFilter(request, response);
            	}
     		} else if (!isStart) {//活动未开始
     			SessionFace.setAttribute(request, "START_TIME", DateUtil.toString(nowModule.getStartTime(), DateUtil.FORMAT_YYYY_YEAR_MM_MONETH_DD_DAY_HH_MM_SS));
     			response.sendRedirect("/common/pages/no_start.jsp");
     			return ;
     		} else {//活动已结束
     			response.sendRedirect("/common/pages/end.htm");
     			return ;
     		}
        }        
    }
	  
	private void setSessionUser(HttpServletRequest request,String district){
		// 如果不执行过滤，则继续
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if(null != sessionUser){
    		return;
    	}
    	sessionUser = new SessionUser();
    	sessionUser.setId(8l);
    	sessionUser.setAccId(6l);
    	sessionUser.setNickname("test");
    	sessionUser.setPhone("18423511926");
    	sessionUser.setOpenid("oCRiQw2qHwTPQjAK115JPyAGpRAI");
    	sessionUser.setDistrict(StringUtil.stringToInteger(district));
    	SessionFace.setSessionUser(request, sessionUser);
	}
}
