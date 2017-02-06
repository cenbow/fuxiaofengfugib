package com.cqliving.cloud.server.controller.wxl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.cloud.online.config.service.RecommendAppService;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.RecommendAppData;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.dao.support.ScrollPageOrder;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 微信小程序操作App相关信息接口
 * <p>Title:WxlAppController </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2017年1月13日下午2:02:50
 *
 */
@Controller
@RequestMapping(value = "/wxl")
public class WxlAppController {
    
    private static final Logger logger = LoggerFactory.getLogger(WxlAppController.class);
    
    @Autowired
    private RecommendAppService recommendAppService;
    
    /**
     * 获取区县列表接口（滚动分页）
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月13日下午2:05:29
     */
    @ResponseBody
    @RequestMapping(value = {"getApps"}, method = {RequestMethod.POST})
    public Response<CommonListResult<RecommendAppData>> getRecommedApps(HttpServletRequest request, 
            @RequestParam Long appId, 
            String sessionId, 
            String token, 
            Integer lastSortNo, 
            Long lastAppId,
            Long lastId) {
        logger.debug("===================== 调用获取区县列表接口 =====================>"
                + String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, lastSortNo=%d, lastAppId=%d, lastId=%d", appId, sessionId, token, lastSortNo, lastAppId, lastId));
        Response<CommonListResult<RecommendAppData>> response = Response.newInstance();
        //检查参数的必要性
        if (appId == null) {
            response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            logger.error("调用获取区县列表接口异常：" + response);
            return response;
        }
        
        //查询数据
        ScrollPage<RecommendAppDto> scrollPage = new ScrollPage<RecommendAppDto>();
        scrollPage.addScrollPageOrder(new ScrollPageOrder("sort_no", ScrollPage.ASC, lastSortNo));
        scrollPage.addScrollPageOrder(new ScrollPageOrder("recommend_app_id", ScrollPage.ASC, lastAppId));
        scrollPage.addScrollPageOrder(new ScrollPageOrder("id", ScrollPage.DESC, lastId));
        scrollPage.setPageSize(10);
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("EQ_appId", appId);
        conditions.put("EQ_status", RecommendApp.STATUS3);
        Response<ScrollPage<RecommendAppDto>> pageResponse = recommendAppService.queryForScrollPage(scrollPage, conditions);
        List<RecommendAppDto> dtos = pageResponse.getData().getPageResults();
        //组装数据
        CommonListResult<RecommendAppData> data = new CommonListResult<RecommendAppData>();
        List<RecommendAppData> dataList = Lists.newArrayList();
        RecommendAppData obj;
        for (RecommendAppDto dto : dtos) {
            obj = new RecommendAppData();
            obj.setAppIcon(dto.getAppIcon());
            obj.setAppId(dto.getRecommendAppId());
            obj.setAppName(dto.getRecommendAppName());
            obj.setColumnsId(dto.getColumnsId());
            obj.setDownloadUrl(dto.getDownloadUrl());
            obj.setId(dto.getId());
            obj.setIsCarousel(RecommendApp.IS_CAROUSEL_1.equals(dto.getIsCarousel()) ? true : false);
            obj.setSortNo(dto.getSortNo());
            dataList.add(obj);
        }
        data.setDataList(dataList);
        response.setData(data);
        logger.debug("调用获取区县列表接口结果：" + response);
        return response;
    }
}
