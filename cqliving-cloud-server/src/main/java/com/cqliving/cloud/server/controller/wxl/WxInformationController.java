package com.cqliving.cloud.server.controller.wxl;

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

import com.alibaba.fastjson.JSON;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.InformationUtil;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.dto.InformationDto;
import com.cqliving.cloud.online.info.service.InfoClassifyService;
import com.cqliving.cloud.online.info.service.InformationService;
import com.cqliving.cloud.online.interfacc.dto.NewsWxlResult;
import com.cqliving.cloud.online.interfacc.dto.WxInformationData;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;


/**
 * Title:微信小程序新闻接口相关
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月13日
 */
@Controller
@RequestMapping(value = "wxl")
public class WxInformationController {
    
    private static final Logger logger = LoggerFactory.getLogger(WxInformationController.class);
    
    @Autowired
    private InfoClassifyService infoClassifyService;
    @Autowired
    private InformationService informationService;

    /**
     * Title:新闻详情
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月13日
     * @param request
     * @param appId
     * @param id info_classify表的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"nd"}, method = RequestMethod.POST)
    public Response<WxInformationData> getNewsDetail(HttpServletRequest request, @RequestParam Long appId, @RequestParam Long id){
        logger.debug(String.format("微信小程序新闻类容获取接口：appId=%d, id=%d", appId, id));
        Response<WxInformationData> rs = Response.newInstance();
        //验证参数的有效性
        if(appId == null || id == null){
            rs.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
            rs.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
            return rs;
        }
        InformationDto informationDto = informationService.findDetail(id).getData();
        if(null == informationDto || !InfoClassify.STATUS3.equals(informationDto.getStatus())){
            rs.setCode(-123);
            rs.setMessage("不存咋或已经被删除");
            return rs;
        }
        //获取新闻的浏览量和回复量   数据库的数量+redis缓存中的数量
        informationService.setViewAndReplyCount(informationDto);
        //增加浏览量
        informationService.addViewCount(id, informationDto.getId());
        
        WxInformationData data = new WxInformationData();
        data.setContent(informationDto.getContent());
        data.setInfoClassifyId(informationDto.getClassifyId());
        data.setTitle(informationDto.getTitle());
        data.setSource(StringUtils.isBlank(informationDto.getInfoSource()) ? "" : informationDto.getInfoSource());
        data.setPubDate(DateUtil.convertTimeToFormatHore1(informationDto.getOnlineTime().getTime(), "MM-dd HH:mm"));
        data.setViewCount(InformationUtil.handleViewCount(informationDto.getViewCount()));
        data.setContextType(informationDto.getContextType());
        data.setAppResource(informationDto.getAppResource());
        data.setContent(data.getContent().replaceAll("\n", " "));
        data.setSynopsis(informationDto.getSynopsis());
        rs.setData(data);
        logger.debug(JSON.toJSONString(rs));
        return rs;
    }
    
    /**
     * <p>Description:</p>
     * @author Tangtao on 2017年1月13日
     * @param request
     * @param appId
     * @param sessionId
     * @param token
     * @param isCarousel
     * @param columnId
     * @param lastId
     * @param lastSortNo
     * @param lastOnlineTime
     * @param onlyWechat
     * @return
     */
    @ResponseBody
	@RequestMapping(value = {"news"}, method = {RequestMethod.POST})
	public Response<NewsWxlResult> getNews(HttpServletRequest request, 
			@RequestParam Long appId, 
			String sessionId, 
			String token, 
			@RequestParam Boolean isCarousel, 
			@RequestParam Long columnId, 
			Long lastId, 
			Integer lastSortNo, 
			String lastOnlineTime,
			boolean onlyWechat) {
		logger.debug("===================== 调用获取资讯列表接口 =====================>" + 
				String.format("\n<请求参数：appId=%d, sessionId=%s, token=%s, isCarousel=%s, columnId=%s, lastId=%s, lastSortNo=%s, lastOnlineTime=%s, onlyWechat=%s", 
						appId, sessionId, token, isCarousel, columnId, lastId, lastSortNo, lastOnlineTime, onlyWechat));
		Response<NewsWxlResult> response = Response.newInstance();
		//检查参数的必要性
		if (appId == null || columnId == null || isCarousel == null) {
			response.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
			response.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
			logger.error("调用获取资讯列表接口异常：" + response);
			return response;
		}
		response = infoClassifyService.getWxlNewsByPage(appId, columnId, isCarousel, lastId, lastSortNo, lastOnlineTime, onlyWechat);
		logger.debug("调用获取资讯列表接口结果：" + response);
		return response;
	}
    
}
