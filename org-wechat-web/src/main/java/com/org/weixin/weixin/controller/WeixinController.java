package com.org.weixin.weixin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.module.memcached.SpyMemcachedClient;
import com.org.weixin.client.bean.base.EventMessage;
import com.org.weixin.client.bean.base.message.req.ReqTextMessage;
import com.org.weixin.system.service.SysAccountWechatsService;
import com.org.weixin.system.service.SysModulesService;
import com.org.weixin.util.ExpireSet;
import com.org.weixin.util.SignatureUtil;
import com.org.weixin.util.XMLConverUtil;
import com.org.weixin.weixin.service.WeixinCoreService;

/**
 * Title:微信核心业务控制器
 * <p>Description:微信核心业务controller</p>
 * Copyright (c) feinno 2013-2016
 * @author fengshi on 2015年7月9日
 */
@Controller
@RequestMapping("weixin")
public class WeixinController{
	
	@Resource
	SpyMemcachedClient spyMemcachedClient;//memcache service
	@Resource
	WeixinCoreService weixinCoreService;//微信核心业务 service
	@Resource
	SysAccountWechatsService accountWechatsService;//用户 service
	@Autowired
	private SysModulesService sysModulesService;
	
	private Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
    //重复通知过滤  时效60秒
    private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

    /**
     * 微信验证
     * <p>Description:</p>
     * @param request
     * @param response
     * @throws IOException
     * @author fengshi on 2015年7月19日
     */
    @RequestMapping(value = "/service/{accId}",method = RequestMethod.GET)
    public void serviceByGet(HttpServletRequest request,HttpServletResponse response,@PathVariable Long accId) throws IOException{
        ServletOutputStream outputStream = response.getOutputStream();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");
        String nonce = request.getParameter("nonce");
    	String token = accountWechatsService.queryTokenById(accId);
    	if(null == token){
    		logger.error("未找到对应的token。accId：{}，token：{}",accId,token);
    		outputStream.write("".getBytes("utf-8"));
    		return;
    	}
    	if(StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce) && StringUtils.isNotBlank(echostr)){
    		//验证签名
    		if(signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
    			logger.debug("微信验证成功！echostr："+echostr);
    			outputStream.write(echostr.getBytes("utf-8"));
    			return;
    		}
    	}
        logger.debug("微信验证失败！signature："+signature+",timestamp："+timestamp+",nonce："+nonce+",echostr:"+echostr);
        outputStream.write("".getBytes("utf-8"));
        return;
    }

	/**
	 * 微信请求处理
	 * <p>Description:</p>
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author fengshi on 2015年7月9日
	 */
    @RequestMapping(value = "/service/{accId}",method = RequestMethod.POST)
    public void serviceByPost(HttpServletRequest request , HttpServletResponse response , @PathVariable Long accId) throws IOException{
    	ServletInputStream inputStream= request.getInputStream();
    	ServletOutputStream outputStream = response.getOutputStream();
		 if(inputStream!=null){
	         //获取事件
	         EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
	         //去除重复通知
	         String expireKey = eventMessage.getFromUserName() + "__" + eventMessage.getToUserName() + "__" + eventMessage.getMsgId() + "__" + eventMessage.getCreateTime();
	         if(expireSet.contains(expireKey)){
	         	//重复通知不作处理
	         	return;
	         }else{
	         	expireSet.add(expireKey);
	         }
	         
	         //调用核心业务处理
	         String result = weixinCoreService.TypeHandle(eventMessage ,accId );
	         //测试回复
	         ReqTextMessage xmlTextMessage = new ReqTextMessage(
	                 eventMessage.getFromUserName(),
	                 eventMessage.getToUserName(),
	                 result);
	         //回复
	         xmlTextMessage.outputStreamWrite(outputStream);
	     }
        return;
    }
    
    /**
     * 微信auth2.0认证
     * <p>Description:</p>
     * @param code
     * @param state
     * @author fengshi on 2015年7月10日
     */
    @RequestMapping("auth2Service/{accId}/{mId}")
    public String Auth2(HttpServletRequest request, 
    		@RequestParam(value = "code", required = false) String code, 
    		@RequestParam(value = "state", required = true) String state,
    		@PathVariable Long accId, 
    		@PathVariable Long mId) {
    	String queryStr = request.getQueryString();
    	if (StringUtils.isNotBlank(code)) {
    		String openId = null;
    		//TODO Tangtao SysModules增加是否需要用户授权字段，使用此字段进行判断
//    		SysModules modules = sysModulesService.get(mId);
//    		if ("zjchj".equals(modules.getName())) {	//正佳吃货节活动，静默授权
//    			openId = weixinCoreService.Auth2OpenId(accId, mId, code, false);
//			} else {
				openId = weixinCoreService.Auth2OpenId(accId, mId, code, true);
//			}
    		if(null != openId){
    			logger.debug("用户授权成功！openId:" + openId);
    			return "redirect:" + state + "?openId=" + openId + "&" + queryStr;
    		}
    	}
		logger.error("用户未授权或未找到公众号！code:{}", code);
		return "redirect:" + state;
    }
    
}
