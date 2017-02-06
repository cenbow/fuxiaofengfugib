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
import com.cqliving.cloud.online.question.service.QuestionService;
import com.cqliving.tool.common.Response;

/**
 * Title: 江北城管系统问题接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author huxiaoping on 2016年11月14日
 */
@Controller
@RequestMapping(value = "/jbUpload")
public class JiangBeiUploadController {
    private static final Logger logger = LoggerFactory.getLogger(JiangBeiUploadController.class);
    @Autowired
    private QuestionService questionService;
    /**
     * <p>Description: 问题上报</p>
     * @author huxiaoping on 2016年11月14日
     * @param request
     * @param eventId
     * @param token
     * @param sessionId
     * @param appId
     * @param imgs
     * @param recDesc
     * @param address
     * @param coordX
     * @param coordY
     * @param reporterName
     * @param reporterMobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"saveQuestion"}, method = {RequestMethod.POST})
    public Response<Boolean> saveQuestion(HttpServletRequest request, 
            @RequestParam String eventId,
            @RequestParam String imgs, 
            @RequestParam String token, 
            String sessionId, 
            String appId, 
            String recDesc, 
            String address, 
            String coordX,
            String coordY,
            String reporterName,
            String reporterMobile) {
            logger.debug("===================== 调用问题上报接口开始 =====================>"); 
            logger.debug(String.format("\n<请求参数：eventId=%s, appId=%s, imgs=%s, recDesc=%s, address=%s, coordX=%s, coordY=%s, reporterName=%s, reporterMobile=%s, token=%s, sessionId=%s", 
                    eventId, appId, imgs, recDesc, address, coordX, coordY, reporterName, reporterMobile,token,sessionId));
        Response<Boolean> response = Response.newInstance();
        //检查参数的必要性
        if (StringUtils.isBlank(eventId) ||(StringUtils.isNotBlank(eventId)&&eventId.split(",").length<2) || StringUtils.isBlank(reporterMobile)||StringUtils.isBlank(token)) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("问题上报接口异常,参数无效：" + response);
            return response;
        }
        
        response = questionService.saveQuestion(eventId, appId, imgs, recDesc, address, coordX, coordY, reporterName, reporterMobile, token, sessionId);
        
        logger.debug("调用问题上报接口结果：" + response);
        return response;
    }
}
