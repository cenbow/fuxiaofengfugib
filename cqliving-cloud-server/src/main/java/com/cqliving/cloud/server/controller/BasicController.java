package com.cqliving.cloud.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.tool.common.Response;

/**
 * Title: 获取基础数据接口
 * <p>Description:</p>
 * Copyright (c) xinhualong 2013-2016
 * @author huxiaoping on 2016年6月14日
 */
@Controller
@RequestMapping(value = "/basic")
public class BasicController {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);
	
	@Autowired
    private BasicService basicService;
	
	/**
	 * Title: 获取举报类型接口
	 * @author  on 2016年6月14日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getReportType"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Option>> getReportType(HttpServletRequest request ) {
		logger.debug("===================== 调用获取举报类型接口 =====================>");
		Response<CommonListResult<Option>> response = Response.newInstance();
		//获得举报类型
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE1);	
        CommonListResult<Option> res = new CommonListResult<Option>();
        res.setDataList(reportTypeList);
        response.setData(res);
		logger.debug("调用获取举报类型接口结果：" + response);
		return response;
	}
	
	/**
	 * Title: 通过typeCode获取基础数据接口
	 * @author  on 2016年6月14日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getBasicDataByType"}, method = {RequestMethod.POST})
	public Response<CommonListResult<Option>> getQuestionType(HttpServletRequest request,String type ) {
	    logger.debug("===================== 调用通过typeCode获取基础数据接口开始 =====================>");
	    Response<CommonListResult<Option>> response = Response.newInstance();
	    if(StringUtils.isBlank(type)){
	        response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("调用通过typeCode获取基础数据接口,参数无效：" + response);
            return response;
	    }
	    logger.debug("type="+type);
	    //获得举报类型
	    List<Option> reportTypeList = basicService.getOptionListByType(type);	
	    CommonListResult<Option> res = new CommonListResult<Option>();
	    res.setDataList(reportTypeList);
	    response.setData(res);
	    logger.debug("调用通过typeCode获取调用基础数据接口结果：" + response);
	    return response;
	}
}