package com.cqliving.cloud.controller.module.config;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.cloud.online.config.service.RecommendInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title: 推荐到首页的列表管理
 * 		sourceType：业务类型{7:话题}
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年8月1日
 */
@Controller
@RequestMapping(value = "/module/config/{sourceType}")
public class RecommendInfoController extends CommonController {

    @Autowired
    private RecommendInfoService recommendInfoService;
    @Autowired
    private AppInfoService appInfoService;

    /**
     * Title：列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月1日
     * @param request
     * @param map
     * @param isAjaxPage
     * @param search_GTE_createTime
     * @param search_LT_createTime
     * @param search_GTE_updateTime
     * @param search_LT_updateTime
     * @return
     */
    @RequestMapping(value ="recommend_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map, @PathVariable("sourceType") Byte sourceType, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        searchMap.put("EQ_sourceType", sourceType);
        if(StringUtils.isBlank(request.getParameter("search_EQ_appId")) && CollectionUtils.isNotEmpty(appList) && appList.size() > 0){
         	searchMap.put("EQ_appId", appList.get(0).getId());
        }
        searchMap.put("EQ_sourceType", sourceType);
        searchMap.put("NOTEQ_status", RecommendInfo.STATUS99);//排除逻辑删除状态
        
        PageInfo<RecommendInfoDto> pageInfo = getPageInfo(request);
        
        map.put("sourceType", sourceType);
        map.put("pageInfo", recommendInfoService.queryForPage(pageInfo, searchMap, sortMap, sourceType).getData());
        map.put("appList",appList);
        map.put("allSourceTypes", RecommendInfo.allSourceTypes);
        map.put("allStatuss", RecommendInfo.allStatuss);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/recommend_info_list_page";
        }else{
        	return "tiles.module.config.recommend_info_list";
        }
    }

    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月1日
     * @param request
     * @param map
     * @param id
     * @param sourceType
     * @return
     */
    @RequestMapping(value ="common/recommend_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id, @PathVariable("sourceType") Byte sourceType){
    	RecommendInfoDto recommendInfoDto = recommendInfoService.getDetail(id, sourceType).getData();
        if(recommendInfoDto==null){
            //没有记录
        	map.put("alertinfo", Constant.Application.COMMON_DANGER_ALERTINFO);
    		map.put("msg", Constant.I18nMessage.RECORD_NOT_FOUND);
    		return getReturnUrl(request,map,"tiles.error.500");
        }
        AppInfo appInfo = appInfoService.get(recommendInfoDto.getAppId()).getData();
        if(appInfo != null){
        	map.put("appInfoName", appInfo.getName());
        }
        map.put("item", recommendInfoDto);
        return getReturnUrl(request,map,"tiles.module.config.recommend_info_view");
    }

    /**
     * Title:删除、批量删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年8月2日
     * @param request
     * @param map
     * @param id
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="recommend_info_delete", method = RequestMethod.POST)
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	Response<Void> res = Response.newInstance();
    	if(id != null){//单个删除
    		res = recommendInfoService.deleteLogic(id);
    	}else if(ids != null){//批量删除
    		res = recommendInfoService.deleteLogic(ids);
    	}
        return res;
    }

	/**
	 * Title:发布、批量发布
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月2日
	 * @param request
	 * @param map
	 * @param id
	 * @param ids
	 * @param status
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value ="recommend_info_publish", method = RequestMethod.POST)
    public Response<Void> publish(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids, @RequestParam Byte status){
        Response<Void> res = Response.newInstance();
        if(!RecommendInfo.allStatuss.containsKey(status)){
        	res.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
        	res.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        	return res;
        }
        if(id != null){//单个发布
        	res = recommendInfoService.updateStatus(status, id);
    	}else if(ids != null){//批量发布
        	res = recommendInfoService.updateStatus(status, ids);
    	}
        return res;
    }
    
    @ResponseBody
    @RequestMapping(value ="recommend_info_sort", method = RequestMethod.POST)
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam Integer sortNo){
    	Response<Void> res = recommendInfoService.updateSort(id, sortNo);
    	return res;
    }
}
