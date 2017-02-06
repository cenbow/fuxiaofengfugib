/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.interfacc.dto.CommonKeyValueData;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.RecruitConditionResult;
import com.cqliving.cloud.online.interfacc.dto.RecruitInfoData;
import com.cqliving.cloud.online.interfacc.dto.RecruitInfoDetailData;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.cloud.online.recruitinfo.service.RecruitImageService;
import com.cqliving.cloud.online.recruitinfo.service.RecruitInfoService;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title: 招聘相关接口
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年10月12日
 */
@Controller
public class RecruitController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecruitController.class);
	
	@Autowired
	private BasicService basicService;
	@Autowired
	private RecruitImageService recruitImageService;
	@Autowired
	private RecruitInfoService recruitInfoService;
	
	/**
	 * <p>Description: 招聘职位详情</p>
	 * @author Tangtao on 2016年10月12日
	 * @param request
	 * @param res
	 * @param appId
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"recuitDetail"}, method = {RequestMethod.POST})
	public Response<RecruitInfoDetailData> detail(HttpServletRequest request, HttpServletResponse res,
			@RequestParam Long appId, 
			@RequestParam Long id) {
		logger.debug("===================== 调用招聘职位详情接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, id=%d", appId, id));
		res.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		Response<RecruitInfoDetailData> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || id == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用招聘职位列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		RecruitInfo info = recruitInfoService.get(id).getData();
		if (info == null || !RecruitInfo.STATUS3.equals(info.getStatus()) || info.getPublicTime().after(DateUtil.now()) || !info.getAppId().equals(appId)) {
			response.setCode(ErrorCodes.FAILURE);
			response.setMessage("记录不存在");
			logger.error("调用招聘职位列表接口异常：" + response);
			return response;
		}
		//查询图片列表
		List<RecruitImage> imgData = recruitImageService.getByRecruitInfoId(id).getData();
		List<String> imgUrls = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(imgData)) {
			for (RecruitImage obj : imgData) {
				imgUrls.add(obj.getUrl());
			}
		}
		
		//组装数据
		RecruitInfoDetailData data = new RecruitInfoDetailData();
		data.setAddress(StringUtils.defaultString(info.getAddress()));
		data.setDescription(StringUtils.defaultString(info.getDescription()));
		data.setEducation(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE14, info.getEducation())));
		data.setId(info.getId());
		data.setNature(StringUtils.defaultString(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE4, info.getNature()))));
		data.setNumberPeople(StringUtils.defaultString(info.getNumberPeople()));
		data.setPosition(StringUtils.defaultString(info.getPosition()));
		data.setPublicTime(DateUtil.toString(info.getPublicTime(), DateUtil.FORMAT_YYYY_MM_DD));
		data.setSalary(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE13, info.getSalary())));
		data.setScale(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE10, info.getScale())));
		data.setSynopsis(StringUtils.defaultString(info.getSynopsis()));
		data.setTelephone(StringUtils.defaultString(info.getTelephone()));
		data.setWorkmode(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE11, info.getWorkmode())));
		data.setImageUrls(imgUrls);
		response.setData(data);
		logger.debug("调用招聘职位详情接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 招聘职位列表</p>
	 * @author Tangtao on 2016年10月12日
	 * @param request
	 * @param res
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param salary
	 * @param education
	 * @param workmode
	 * @param position
	 * @param lastSortNo
	 * @param lastPublicTime
	 * @param lastId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"recuitList"}, method = {RequestMethod.POST})
	public Response<CommonListResult<RecruitInfoData>> list(HttpServletRequest request, HttpServletResponse res,
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			String salary, 
			String education, 
			String workmode, 
			String position, 
			Integer lastSortNo,
			String lastPublicTime, 
			Long lastId) {
		logger.debug("===================== 调用招聘职位列表接口 =====================>"
				+ String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, salary=%s, education=%s, workmode=%s, position=%s, lastSortNo=%d, lastPublicTime=%s, lastId=%d", 
						appId, sessionId, token, salary, education, workmode, position, lastSortNo, lastPublicTime, lastId));
		res.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		Response<CommonListResult<RecruitInfoData>> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用招聘职位列表接口异常：" + response);
			return response;
		}
		
		//查询数据
		ScrollPage<RecruitInfo> scrollPage = new ScrollPage<RecruitInfo>();
		scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo == null ? 0 : lastSortNo));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("public_time", ScrollPage.DESC, StringUtils.isBlank(lastPublicTime) ? null : lastPublicTime));
		scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
		scrollPage.setPageSize(10);
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("EQ_appId", appId);
		conditions.put("LTE_publicTime", DateUtil.now());
		conditions.put("EQ_status", InfoClassify.STATUS3);
		conditions.put("EQ_salary", salary);
		conditions.put("EQ_education", education);
		conditions.put("EQ_workmode", workmode);
		conditions.put("LIKE_position", position);
		Response<ScrollPage<RecruitInfo>> pageResponse = recruitInfoService.queryForScrollPage(scrollPage, conditions);
		List<RecruitInfo> dtos = pageResponse.getData().getPageResults();
		//组装数据
		CommonListResult<RecruitInfoData> data = new CommonListResult<RecruitInfoData>();
		List<RecruitInfoData> dataList = Lists.newArrayList();
		RecruitInfoData obj;
		for (RecruitInfo dto : dtos) {
			obj = new RecruitInfoData();
			obj.setEducation(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE14, dto.getEducation())));
			obj.setEntLabel(StringUtils.defaultString(dto.getEntLabel()));
			obj.setId(dto.getId());
			obj.setName(StringUtils.defaultString(dto.getName()));
			obj.setNumberPeople(StringUtils.defaultString(dto.getNumberPeople()));
			obj.setPosition(StringUtils.defaultString(dto.getPosition()));
			obj.setPublicTime(DateUtil.toString(dto.getPublicTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
			obj.setPublicTimeStr(DateUtil.toString(dto.getPublicTime(), DateUtil.FORMAT_YYYY_MM_DD));
			obj.setSalary(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE13, dto.getSalary())));
			obj.setSortNo(dto.getSortNo());
			obj.setWorkmode(StringUtils.defaultString(basicService.getOptionNameByTypeCode(Option.TYPECODE11, dto.getWorkmode())));
			dataList.add(obj);
		}
		data.setDataList(dataList);
		response.setData(data);
		logger.debug("调用招聘职位列表接口结果：" + response);
		return response;
	}
	
	/**
	 * <p>Description: 获取招聘列表查询条件</p>
	 * @author Tangtao on 2016年10月12日
	 * @param request
	 * @param res
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"recuitQueryCondition"}, method = {RequestMethod.POST})
	public Response<RecruitConditionResult> queryCondition(HttpServletRequest request, HttpServletResponse res, @RequestParam Long appId) {
		logger.debug("===================== 调用招聘查询条件接口 =====================>"
				+ String.format("\n<请求参数：appId=%d", appId));
		res.setHeader("Access-Control-Allow-Origin", "*");	//解决跨域问题
		Response<RecruitConditionResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用招聘查询条件接口异常：" + response);
			return response;
		}
		
		//获取月薪范围、学历、工作性质基础数据
		List<Option> educationList = basicService.getOptionListByType(Option.TYPECODE14);
		List<Option> salaryList = basicService.getOptionListByType(Option.TYPECODE13);
		List<Option> workmodeList = basicService.getOptionListByType(Option.TYPECODE11);
		RecruitConditionResult data = new RecruitConditionResult();
		data.setEducations(transfer(educationList));
		data.setSalaries(transfer(salaryList));
		data.setWorkmodes(transfer(workmodeList));
		response.setData(data);
		logger.debug("调用招聘查询条件接口结果：" + response);
		return response;
	}

	// -------------------------------------------- 私有方法 --------------------------------------------
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月12日
	 * @param originalList
	 * @return
	 */
	private List<CommonKeyValueData> transfer(List<Option> originalList) {
		List<CommonKeyValueData> list = Lists.newArrayList();
		CommonKeyValueData data;
		if (CollectionUtils.isNotEmpty(originalList)) {
			for (Option option : originalList) {
				data = new CommonKeyValueData();
				data.setCode(option.getCode());
				data.setValue(option.getName());
				list.add(data);
			}
		}
		return list;
	}
	
}