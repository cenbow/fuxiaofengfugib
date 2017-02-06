package com.cqliving.cloud.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserGovAuthentication;
import com.cqliving.cloud.online.account.service.UserGovAuthenticationService;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/user/govauthentication")
public class UserGovAuthenticationController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserGovAuthenticationController.class);
    @Autowired
    private UserGovAuthenticationService userGovAuthenticationService;
    
    /**
     * <p>Description: 获取认证信息</p>
     * @author huxiaoping on 2016年12月29日
     * @param request
     * @param token
     * @param sessionId
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"get"}, method = {RequestMethod.POST})
    public Response<UserGovAuthentication> getGovAuthentication(HttpServletRequest request,
            @RequestParam String token, 
            @RequestParam String sessionId, 
            @RequestParam Long appId) {
        String parameters = String.format("\n<请求参数：token=%s, sessionId=%s, appId=%d", token,sessionId,appId);
        logger.debug("===================== 调用获取认证信息接口 =====================>" + parameters);
        Response<UserGovAuthentication> response = Response.newInstance();
        //检查参数的必要性
        if (StringUtils.isBlank(token)) {
            response.setCode(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
            logger.error("调用获取认证信息接口异常，请先登录：" + response + parameters);
            return response;
        }
        if (StringUtils.isBlank(sessionId)||appId == null) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("调用获取认证信息接口请求参数异常：" + response + parameters);
            return response;
        }
        
        //查询数据
        Response<UserGovAuthentication> res = userGovAuthenticationService.get(token,sessionId,appId);
        if(res.getCode()<0){
            response.setCode(res.getCode());
            response.setMessage(res.getMessage());
            logger.error("调用获取认证信息接口异常：" + response);
            return response;
        }
        response.setData(res.getData());
        logger.debug("调用获取认证信息接口结果：" + response);
        return response;
    }
    
    /**
     * <p>Description: 获取第三方OpenId</p>
     * @author huxiaoping on 2016年12月29日
     * @param request
     * @param token
     * @param sessionId
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getOpenId"}, method = {RequestMethod.POST})
    public Response<String> getOpenId(HttpServletRequest request,
            @RequestParam String token, 
            @RequestParam String sessionId, 
            @RequestParam Long appId) {
        String parameters = String.format("\n<请求参数：token=%s, sessionId=%s, appId=%d", token,sessionId,appId);
        logger.debug("===================== 调用获取第三方OpenId接口 =====================>" + parameters);
        Response<String> response = Response.newInstance();
        //检查参数的必要性
        if (StringUtils.isBlank(token)) {
            response.setCode(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
            logger.error("调用获取第三方OpenId接口异常，请先登录：" + response + parameters);
            return response;
        }
        if (StringUtils.isBlank(sessionId)||appId == null) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("调用获取第三方OpenId接口请求参数异常：" + response + parameters);
            return response;
        }
        
        //查询数据
        Response<UserGovAuthentication> res = userGovAuthenticationService.get(token,sessionId,appId);
        if(res.getCode()<0){
            response.setCode(res.getCode());
            response.setMessage(res.getMessage());
            logger.error("调用获取第三方OpenId接口异常：" + response);
            return response;
        }
        response.setData(res.getData().getThirdOpenId());
        logger.debug("调用获取第三方OpenId接口结果：" + response);
        return response;
    }
    
    /**
     * <p>Description: 认证</p>
     * @author huxiaoping on 2016年12月29日
     * @param request
     * @param token
     * @param sessionId
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    public Response<Boolean> save(HttpServletRequest request,
            @RequestParam String token, 
            @RequestParam String sessionId, 
            @RequestParam Long appId,
            /** 真实姓名 */
            String name,
            /** 身份证号码 */
            String idCard,
            /** 性别 */
            Byte sex,
            /** 民族code */
            String nation,
            /** 手机号码 */
            String phone,
            /** 验证码 */
            String captcha) {
        String parameters = String.format("\n<请求参数：token=%s, sessionId=%s, appId=%d, name=%s, idCard=%s, sex=%d, nation=%s, phone=%s", token,sessionId,appId,name,idCard,sex,nation,phone);
        logger.debug("===================== 调用认证信息修改接口 =====================>" + parameters);
        Response<Boolean> response = Response.newInstance();
        
        //检查参数的必要性
        if (StringUtils.isBlank(token)) {
            response.setCode(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
            logger.error("调用认证接口异常，请先登录：" + response + parameters);
            return response;
        }
        if (StringUtils.isBlank(sessionId)||appId == null||StringUtils.isBlank(name)||StringUtils.isBlank(idCard)||StringUtils.isBlank(nation)||StringUtils.isBlank(phone)||sex==null||StringUtils.isBlank(captcha)) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("调用认证接口异常：" + response + parameters);
            return response;
        }
        //查询数据
        Response<Void> res = userGovAuthenticationService.save(token, sessionId, appId, name, idCard, sex, nation, phone, captcha);
        if(res.getCode()<0){
            response.setCode(res.getCode());
            response.setMessage(res.getMessage());
            logger.error("调用认证口异常：" + response);
            return response;
        }
        response.setData(true);
        logger.debug("调用认证口结果：" + response);
        return response;
    }
}