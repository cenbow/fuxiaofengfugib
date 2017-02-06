/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
/**
 * 
 */
package com.cqliving.cloud.server.controller;

import java.util.List;
import java.util.Map;

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
import com.cqliving.cloud.online.consult.domain.ConsultInfo;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.cloud.online.consult.service.ConsultInfoService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * 咨询接口
 * <p>Title:ConsultInfoController </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2016年11月29日下午3:29:11
 *
 */
@Controller
@RequestMapping(value = "/consultInfo")
public class ConsultInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsultInfoController.class);
	
	@Autowired
	private ConsultInfoService consultInfoService;
	
	/**
     * <p>Description: 滚动分页查询咨询列表接口</p>
     * @author huxiaoping on 2016年11月29日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param lastId
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"getScrollPage"}, method = {RequestMethod.POST})
    public Response<CommonListResult<ConsultInfoDto>> getRecommedApps(HttpServletRequest request, 
            @RequestParam Long appId, 
            String sessionId, 
            String token, 
            Long lastId,
            Integer pageSize) {
        logger.debug("===================== 滚动分页查询咨询列表接口 =====================>"
                + String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastId=%d ,pageSize=%d", appId, sessionId, token, lastId, pageSize));
        Response<CommonListResult<ConsultInfoDto>> response = Response.newInstance();
        //检查参数的必要性
        if (appId == null) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("滚动分页查询咨询列表接口异常：" + response);
            return response;
        }
        //查询数据
        ScrollPage<ConsultInfoDto> scrollPage = new ScrollPage<ConsultInfoDto>();
        scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
        scrollPage.setPageSize(null==pageSize?10:pageSize);
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("NOTEQ_status", ConsultInfo.STATUS99);
        Response<ScrollPage<ConsultInfoDto>> pageResponse = consultInfoService.queryConsultScrollPage(scrollPage, conditions, sessionId, token);
        List<ConsultInfoDto> dataList = pageResponse.getData().getPageResults();
        CommonListResult<ConsultInfoDto> res = new CommonListResult<ConsultInfoDto>();
        res.setDataList(dataList);
        response.setData(res);
        
        logger.debug("滚动分页查询咨询列表接口结果：" + response);
        return response;
    }
    
    /**
     * <p>Description: 提交问题接口</p>
     * @author huxiaoping on 2016年11月29日
     * @param request
     * @param appId
     * @param type
     * @param content
     * @param enterpriseName
     * @param linkmanName
     * @param linkmanPhone
     * @param captcha
     * @param token
     * @param sessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    public Response<Boolean> saveQuestion(HttpServletRequest request, 
            Long appId,  /** 客户端_ID */
            String type, /** 咨询类别表（去基础数据查询名称） */
            String content, /** 咨询内容  */
            String enterpriseName, /** 企业名称  */
            String linkmanName,/** 联系人姓名 */
            String linkmanPhone,/** 联系人电话 */
            String captcha, /** 验证码 */
            String token, 
            String sessionId) {
            logger.debug("===================== 调用提交问题接口开始 =====================>"); 
            logger.debug(String.format("\n<请求参数：appId=%d, type=%s,content=%s,enterpriseName=%s,linkmanName=%s,linkmanPhone=%s, token=%s, sessionId=%s", 
                    appId, type, content,enterpriseName,linkmanName,linkmanPhone,token,sessionId));
        Response<Boolean> response = Response.newInstance();
        //检查参数的必要性
        if (appId==null||StringUtils.isBlank(type) ||StringUtils.isBlank(captcha)|| StringUtils.isBlank(content) || StringUtils.isBlank(enterpriseName) || StringUtils.isBlank(linkmanName) || StringUtils.isBlank(linkmanPhone) ||StringUtils.isBlank(sessionId)) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("提交问题接口异常,参数无效：" + response);
            return response;
        }
        response = consultInfoService.saveConsultInfo(appId, type, content, enterpriseName, linkmanName, linkmanPhone, token, sessionId, captcha);
        
        logger.debug("调用提交问题接口结果：" + response);
        return response;
    }
}