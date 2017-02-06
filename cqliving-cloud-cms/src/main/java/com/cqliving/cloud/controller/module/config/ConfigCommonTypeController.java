package com.cqliving.cloud.controller.module.config;

import java.util.Date;
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
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigCommonType;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

/**
 * Title:sourceType：业务类型{7:话题,8:便民,9:热线}
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月26日
 */
@Controller
@RequestMapping(value = "/module/config/{sourceType}")
public class ConfigCommonTypeController extends CommonController {

    @Autowired
    private ConfigCommonTypeService configCommonTypeService;
    @Autowired
    private AppInfoService appInfoService;

    /**
     * Title:分类列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param sourceType
     * @param isAjaxPage
     * @param sourceType：业务类型{7:话题,8:便民,9:热线}
     * @return
     */
    @RequestMapping(value ="config_common_type_list")
    public String list(HttpServletRequest request, Map<String, Object> map, @PathVariable("sourceType") Byte sourceType,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
    	map.put("sourceType", sourceType);
    	map.put("allSourceTypes", ConfigCommonType.allSourceTypes);
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        
        PageInfo<ConfigCommonType> pageInfo = getPageInfo(request);
        if(StringUtils.isBlank(request.getParameter("search_EQ_appId")) && CollectionUtils.isNotEmpty(appList) && appList.size() > 0){
         	searchMap.put("EQ_appId", appList.get(0).getId());
        }
        searchMap.put("EQ_sourceType", sourceType);
        searchMap.put("NOTEQ_status", ConfigCommonType.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", configCommonTypeService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allSourceTypes", ConfigCommonType.allSourceTypes);
        map.put("allStatuss", ConfigCommonType.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/config_common_type_list_page";
        }else{
        	return "tiles.module.config.config_common_type_list";
        }
    }

    /**
     * Title:增加-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param sourceType：业务类型{7:话题,8:便民,9:热线}
     * @return
     */
    @RequestMapping(value ="config_common_type_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map, @PathVariable("sourceType") Byte sourceType){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
    	map.put("sourceType", sourceType);
    	map.put("allSourceTypes", ConfigCommonType.allSourceTypes);
    	return getReturnUrl(request,map,"tiles.module.config.config_common_type_detail");
    }


    /**
     * Title:增加-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param configCommonType
     * @param sourceType：业务类型{7:话题,8:便民,9:热线}
     * @return
     */
    @RequestMapping(value ="config_common_type_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ConfigCommonType configCommonType, @PathVariable("sourceType") Byte sourceType){
    	Response<Void> res = Response.newInstance();
    	if(sourceType == null || !sourceType.equals(configCommonType.getSourceType())){
    		res.setCode(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode());
    		res.setMessage(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    		return res;
    	}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Date now = new Date();
        configCommonType.setId(null);
        if(configCommonType.getSortNo() == null){
        	configCommonType.setSortNo(0);
        }
        configCommonType.setStatus(ConfigCommonType.STATUS3);
        configCommonType.setCreateTime(now);
        configCommonType.setCreator(sessionUser.getNickname());
        configCommonType.setCreatorId(sessionUser.getUserId());
        configCommonType.setUpdateTime(now);
        configCommonType.setUpdator(sessionUser.getNickname());
        configCommonType.setUpdatorId(sessionUser.getUserId());
        
        res = configCommonTypeService.save(configCommonType);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param id
     * @param sourceType：业务类型{7:话题,8:便民,9:热线}
     * @return
     */
    @RequestMapping(value ="config_common_type_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id, @PathVariable("sourceType") Byte sourceType){
        ConfigCommonType configCommonType = configCommonTypeService.get(id).getData();
        if(configCommonType==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configCommonType);
        return getReturnUrl(request,map,"tiles.module.config.config_common_type_detail");
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param configCommonType
     * @param sourceType：业务类型{7:话题,8:便民,9:热线}
     * @return
     */
    @RequestMapping(value ="config_common_type_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigCommonType configCommonType, @PathVariable("sourceType") Byte sourceType){
        Response<Void> res = Response.newInstance();
        if(configCommonType==null || configCommonType.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ConfigCommonType sourceConfigCommonType = configCommonTypeService.get(configCommonType.getId()).getData();
            if(sourceConfigCommonType==null || !sourceConfigCommonType.getSourceType().equals(sourceType)){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            Date now = new Date();
            //分类名称
            sourceConfigCommonType.setName(configCommonType.getName());
            //分类图标
            sourceConfigCommonType.setImageUrl(configCommonType.getImageUrl());
            //排序号
            sourceConfigCommonType.setSortNo(configCommonType.getSortNo());
            //更新时间
            sourceConfigCommonType.setUpdateTime(now);
            //更新人ID
            sourceConfigCommonType.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceConfigCommonType.setUpdator(sessionUser.getNickname());
            
            res= configCommonTypeService.save(sourceConfigCommonType);
            configCommonType = sourceConfigCommonType;
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigCommonType Error : " + configCommonType.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="common/config_common_type_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id, @PathVariable("sourceType") Byte sourceType){
        ConfigCommonType configCommonType = configCommonTypeService.get(id).getData();
        if(configCommonType==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configCommonType);
        AppInfo appInfo = appInfoService.get(configCommonType.getAppId()).getData();
        if(appInfo != null){
        	map.put("appName", appInfo.getName());
        }
        map.put("allSourceTypes", ConfigCommonType.allSourceTypes);
        map.put("sourceType", sourceType);
        return getReturnUrl(request,map,"tiles.module.config.config_common_type_view");
    }

    /**
     * Title:删除、批量删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param id
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="config_common_type_delete", method = RequestMethod.POST)
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	if(id == null && ids == null){
    		return new Response<Void>(ErrorCodes.FAILURE, "请选择要删除的记录。");
    	}
    	if(id != null){
    		ids = new Long[]{id};
    	}
        Response<Void> res = configCommonTypeService.deleteLogic(ids);
        return res;
    }
    
    /**
     * Title:排序
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月26日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="config_common_type_sort", method = RequestMethod.POST)
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam Integer sortNo){
    	Response<Void> res = configCommonTypeService.updateSort(id, sortNo);
    	return res;
    }
    
    /**
     * Title:根据appId获取分类列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月27日
     * @param request
     * @param appId
     * @param sourceType
     * @return
     */
    @ResponseBody
    @RequestMapping(value="common/getByApp", method=RequestMethod.POST)
    public Response<List<ConfigCommonType>> getByApp(HttpServletRequest request, @RequestParam Long appId, @PathVariable("sourceType") Byte sourceType){
    	Response<List<ConfigCommonType>> res = configCommonTypeService.getBySourceType(appId, sourceType);
    	return res;
    }
    
    /**
     * Title:根据appId获取分类列表(热线分类专用)
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月29日
     * @param request
     * @param map
     * @param appId
     * @param isList
     * @param sourceType
     * @return
     */
    @RequestMapping(value ="common/getByAppPage")
    public String loadTypeSelect(HttpServletRequest request, Map<String, Object> map,Long appId, Boolean isList, @PathVariable("sourceType") Byte sourceType){
        Response<List<ConfigCommonType>> res = configCommonTypeService.getBySourceType(appId, sourceType);
        map.put("typeList", res.getData());
        map.put("isList", isList==null?true:false);
        return "/module/config/type_select";
    }
}
