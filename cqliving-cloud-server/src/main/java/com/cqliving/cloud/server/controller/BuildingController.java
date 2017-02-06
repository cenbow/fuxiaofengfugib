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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.dto.BuildingInfoDto;
import com.cqliving.cloud.online.building.service.BuildingInfoService;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年10月12日
 */
@Controller
@RequestMapping(value="building")
public class BuildingController {

	@Autowired
	ConfigRegionService configRegionService;
	@Autowired
	BuildingInfoService buildingInfoService; 
	
	//区域范围
	private static final String RGION_AREA = "region_area";
	//价格区间
	private static final String PRICE_RANGE="price_range";
	//房产户型
	private static final String BUILDING_TYPE = "building_type";
	
	private static final int PAGE_SIZE = 10;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Title:/building/search_condition.html
	 * <p>Description:获取查询条件接口,前台查询列表时：map对象用key作为条件值,region对象用code作为条件值</p>
	 * @author fuxiaofeng on 2016年10月12日
	 * @param request
	 * @return 返回map json对象
	 */
	@RequestMapping(value="search_condition")
	@ResponseBody
	public Response<Map<String,Object>> getSearchCondition(HttpServletRequest request,HttpServletResponse response,Byte type){
		
		Response<Map<String,Object>> rp = Response.newInstance();
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			Map<String,Object> data = Maps.newHashMap();
			data.put(RGION_AREA,configRegionService.findByType(ConfigRegion.TYPE11).getData());
			data.put(BUILDING_TYPE, BuildingInfo.allHouseTypeList);
			if(null == type){
				type = 1;
			}
			data.put(PRICE_RANGE,BuildingInfo.allTypesPriceRange.get(type));
			rp.setData(data);
		} catch (Exception e) {
			rp.setCode(ErrorCodes.FAILURE);
			e.printStackTrace();
		}
		return rp;
	}
	
	/**
	 * Title:/building/list.html
	 * <p>Description:置业滚动分页列表</p>
	 * @author fuxiaofeng on 2016年10月12日
	 * @param request
	 * @param houseType 户型
	 * @param price 价格
	 * @param regionCode 区域
	 * @param sortValue 滚动分页排序值修改时间  updateTime
	 * @return 返回BuildingInfo实体
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Response<CommonListResult<BuildingInfoDto>> scrollPage(HttpServletRequest request,HttpServletResponse response,String houseType,
			String price,String regionCode,String sortNoValue,String updateTimeValue,String idValue,String keyword,Long appId,Byte type){
		
		Response<CommonListResult<BuildingInfoDto>> resp = Response.newInstance();
		try {
			
			response.setHeader("Access-Control-Allow-Origin", "*");
			logger.info("----->>>>>>>前台传入查询条件:houseType={};price={};regionCode={};sortNoValue={};updateTimeValue={},idValue={}<<<<<<<",
					houseType,price,regionCode,sortNoValue,updateTimeValue,idValue);
			
			ScrollPage<BuildingInfo> scrollPage = new ScrollPage<BuildingInfo>();
			scrollPage.setPageSize(PAGE_SIZE);
			scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no",ScrollPage.ASC,sortNoValue));
			scrollPage.addScrollPageOrder(new ScrollPageOrder("update_time",ScrollPage.DESC,updateTimeValue));
			scrollPage.addScrollPageOrder(new ScrollPageOrder("id",ScrollPage.DESC,idValue));
			Map<String,Object> conditions = Maps.newHashMap();
			if(!StringUtil.isEmpty(price)){
				BuildingInfo.resolvePriceRange(conditions, price);
			}
			if(!StringUtil.isEmpty(houseType))
			     conditions.put("LIKE_houseType", ","+houseType+",");
			conditions.put("EQ_regionCode", regionCode);
			conditions.put("LIKE_keyword",keyword);
			conditions.put("EQ_status", BuildingInfo.STATUS3);
			conditions.put("EQ_appId",appId);
			conditions.put("EQ_type",null == type ? BuildingInfo.TYPE1 : type);
			Response<ScrollPage<BuildingInfo>> rp = buildingInfoService.queryScrollPage(scrollPage, conditions);
			
			CommonListResult<BuildingInfoDto> result = new CommonListResult<BuildingInfoDto>();
			if(null != rp.getData()){
				result.setDataList(this.convert(rp.getData().getPageResults()));
			}
			resp.setData(result);
			resp.setCode(rp.getCode());
			resp.setMessage(rp.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(ErrorCodes.FAILURE);
		}
		return resp;
	}
	
	//转DTO
	private List<BuildingInfoDto> convert(List<BuildingInfo> buildingInfos){
		if(CollectionUtils.isEmpty(buildingInfos)) return null;
		List<BuildingInfoDto> data = Lists.newArrayList();
		for(BuildingInfo building : buildingInfos){
			BuildingInfoDto dto = new BuildingInfoDto();
			BeanUtils.copyProperties(building, dto);
			dto.setDetailUrl(String.format(PropertiesConfig.getString(PropertyKey.BUILDING_H5URL),building.getId()));
			data.add(dto);
		}
		return data;
	}
	
	/**
	 * Title:/building/detail/1.html
	 * <p>Description:置业详情</p>
	 * @author fuxiaofeng on 2016年10月12日
	 * @param request
	 * @param id 置业ID
	 * @return 返回 置业详情BuildingInfoDto
	 */
	@RequestMapping(value="detail/{id}")
	@ResponseBody
	public Response<BuildingInfoDto> buildingDetail(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
	
		Response<BuildingInfoDto> rp = Response.newInstance();
        try {
        	response.setHeader("Access-Control-Allow-Origin", "*");
			rp =  buildingInfoService.buildingDetail(id);
			BuildingInfoDto dto = rp.getData();
			if(dto.getStatus().byteValue() != BuildingInfo.STATUS3.byteValue()){
				rp.setData(null);
				rp.setMessage("已下线或者删除");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			rp.setCode(ErrorCodes.FAILURE);
		}	
	   return rp;
	}
}
