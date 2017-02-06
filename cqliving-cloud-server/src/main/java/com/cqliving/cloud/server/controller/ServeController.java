package com.cqliving.cloud.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.cloud.online.county.service.CountyHousesService;
import com.cqliving.cloud.online.county.service.CountyService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * 服务接口
 * <p>Title:ServeController </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2017年1月6日上午11:27:44
 *
 */
@Controller
@RequestMapping(value = "/serve")
public class ServeController {
    
    private static final Logger logger = LoggerFactory.getLogger(ServeController.class);
    @Autowired
    private CountyHousesService countyHousesService;
    @Autowired
    private CountyService countyService;
    
    /**
     * 获取区县信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午1:15:17
     */
    @ResponseBody
    @RequestMapping(value = {"/county/getCounty"}, method = {RequestMethod.POST})
    public Response<List<County>> getCounty(
            HttpServletRequest request,
            HttpServletResponse res,
            String token,String sessionId){
        res.setHeader("Access-Control-Allow-Origin", "*");
        String parameters = String.format("\n<请求参数：token=%s, sessionId=%s", token,sessionId);
        logger.debug("===================== 调用获取区县信息接口 =====================>" + parameters);
        return countyService.queryList(token, sessionId);
    }
    
    /**
     * 获取区县楼盘信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午1:15:17
     */
    @ResponseBody
    @RequestMapping(value = {"/county/getHouses"}, method = {RequestMethod.POST})
    public Response<CommonListResult<CountyHouses>> getHouses(
            HttpServletRequest request,HttpServletResponse ServletResponse,
            String token,String sessionId,Long countyId, String name,Long lastId,Integer pageSize){
        ServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String parameters = String.format("\n<请求参数：token=%s, sessionId=%s ,countyId=%d ,name=%s, lastId=%d, pageSize=%d", token,sessionId,countyId,name,lastId,pageSize);
        Response<CommonListResult<CountyHouses>> response = Response.newInstance();
        logger.debug("===================== 滚动分页获取区县楼盘信息接口 =====================>" + parameters);
        //查询数据
        ScrollPage<CountyHouses> scrollPage = new ScrollPage<CountyHouses>();
        scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
        scrollPage.setPageSize(null==pageSize?10:pageSize);
        Map<String, Object> conditions = Maps.newHashMap();
        if(null!=countyId){
            conditions.put("EQ_countyId", countyId);
        }
        conditions.put("EQ_status", CountyHouses.STATUS3);
        if (StringUtils.isNotBlank(name)) {
            conditions.put("LIKE_name", name);
        }
        Response<ScrollPage<CountyHouses>> pageResponse = countyHousesService.getScrollPage(scrollPage, conditions, token, sessionId);
        if(pageResponse.getCode()<0){
            response.setCode(pageResponse.getCode());
            response.setMessage(pageResponse.getMessage());
            logger.error("滚动分页获取区县楼盘信息失败：" + response);
            return response;
        }
        List<CountyHouses> dataList = pageResponse.getData().getPageResults();
        CommonListResult<CountyHouses> res = new CommonListResult<CountyHouses>();
        res.setDataList(dataList);
        response.setData(res);
        
        logger.debug("滚动分页获取区县楼盘信息接口结果：" + response);
        return response;
    }
}
