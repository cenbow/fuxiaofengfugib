package com.cqliving.cloud.controller.module.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.cloud.online.config.service.ConfigCommonTypeService;
import com.cqliving.cloud.online.config.service.ConfigConvenienceService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/cfg")
public class ConfigConvenienceController extends CommonController {

    @Autowired
    private ConfigConvenienceService configConvenienceService;
    @Autowired
    private AppInfoService appInfoService;
	@Autowired
	private ConfigCommonTypeService configCommonTypeService;

    //列表
    @RequestMapping(value ="config_convenience_list")
    public String list(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        if(sessionUser.getAppId() != null){
        	searchMap.put("EQ_appId", sessionUser.getAppId());
        }
        PageInfo<ConfigConvenienceDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ConfigConvenience.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", configConvenienceService.queryForPage(pageInfo, searchMap, sortMap).getData());
        map.put("allStatuss", ConfigConvenience.allStatuss);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/config_convenience_list_page";
        }else{
        	List<ConfigCommonType> configCommonTypeList = null;
        	if(appList != null && appList.size() == 1){
        		Long appId = sessionUser.getAppId();
        		configCommonTypeList = configCommonTypeService.getBySourceType(appId, ConfigCommonType.SOURCETYPE8).getData();
        	}
        	map.put("configCommonTypeList", configCommonTypeList);
        	return "tiles.module.cfg.config_convenience_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="config_convenience_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
        if(appList != null && appList.size() < 2){
        	 List<ConfigCommonType> typeList =  configCommonTypeService.getBySourceType(sessionUser.getAppId() != null ? sessionUser.getAppId() : appList.get(0).getId(), ConfigCommonType.SOURCETYPE8).getData();
             map.put("typeList", typeList);
        }
    	return getReturnUrl(request,map,"tiles.module.cfg.config_convenience_detail");
    }

    //增加-保存
    @ResponseBody
    @RequestMapping(value ="config_convenience_add", method = RequestMethod.POST)
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ConfigConvenience configConvenience){
        //ID
        configConvenience.setId(null);
        Date now = new Date();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        configConvenience.setSortNo(configConvenience.getSortNo() == null ? 0 : configConvenience.getSortNo());
        configConvenience.setCreateTime(now);
        configConvenience.setCreator(sessionUser.getNickname());
        configConvenience.setCreatorId(sessionUser.getUserId());
        configConvenience.setUpdateTime(now);
        configConvenience.setUpdator(sessionUser.getNickname());
        configConvenience.setUpdatorId(sessionUser.getUserId());
        configConvenience.setStatus(ConfigConvenience.STATUS3);
        Response<Void> res = configConvenienceService.save(configConvenience);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="config_convenience_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigConvenience configConvenience = configConvenienceService.get(id).getData();
        if(configConvenience==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        List<ConfigCommonType> typeList =  configCommonTypeService.getBySourceType(configConvenience.getAppId(), ConfigCommonType.SOURCETYPE8).getData();
        map.put("item", configConvenience);
        map.put("appList",appList);
        map.put("typeList", typeList);
        return getReturnUrl(request,map,"tiles.module.cfg.config_convenience_detail");
    }

    //修改-保存
    @ResponseBody
    @RequestMapping(value ="config_convenience_update", method = RequestMethod.POST)
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigConvenience configConvenience){
        Response<Void> res = Response.newInstance();
        if(configConvenience==null || configConvenience.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ConfigConvenience sourceConfigConvenience = configConvenienceService.get(configConvenience.getId()).getData();
            if(sourceConfigConvenience==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            Date now = new Date();
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            //客户端_ID
            sourceConfigConvenience.setAppId(configConvenience.getAppId());
            //热线类型ID，config_life_type表主键
            sourceConfigConvenience.setConvenienceTypeId(configConvenience.getConvenienceTypeId());
            //便民名称
            sourceConfigConvenience.setName(configConvenience.getName());
            //便民链接
            sourceConfigConvenience.setLinkUrl(configConvenience.getLinkUrl());
            //便民图标48*48px地址
            sourceConfigConvenience.setIconMinUrl(configConvenience.getIconMinUrl());
            //便民图标72*72px地址
            sourceConfigConvenience.setIconMaxUrl(configConvenience.getIconMaxUrl());
            //排序号
            sourceConfigConvenience.setSortNo(configConvenience.getSortNo());
            //更新时间
            sourceConfigConvenience.setUpdateTime(now);
            //更新人ID
            sourceConfigConvenience.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceConfigConvenience.setUpdator(sessionUser.getNickname());
            res= configConvenienceService.save(sourceConfigConvenience);
            configConvenience = sourceConfigConvenience;
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigConvenience Error : " + configConvenience.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="common/config_convenience_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigConvenience configConvenience = configConvenienceService.get(id).getData();
        if(configConvenience==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configConvenience);
        AppInfo appInfo = appInfoService.get(configConvenience.getAppId()).getData();
        map.put("appName", appInfo.getName());
        ConfigCommonType configCommonType = null;
        if(configConvenience.getConvenienceTypeId() != null){
        	configCommonType = configCommonTypeService.get(configConvenience.getConvenienceTypeId()).getData();
        }
        map.put("convenienceTypeName", configCommonType != null ? configCommonType.getName() : "");
        return getReturnUrl(request,map,"tiles.module.cfg.config_convenience_view");
    }

    //删除
    @ResponseBody
    @RequestMapping(value ="config_convenience_delete", method = RequestMethod.POST)
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id", required=false) Long id, @RequestParam(value="ids[]", required=false) Long[] ids){
    	if(id == null && ids == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> res = null;
    	if(id != null){
    		res = configConvenienceService.deleteLogic(id);
    	}else if(ids != null){
    		res = configConvenienceService.deleteLogic(ids);
    	}
        return res;
    }
    
    //排序
    @ResponseBody
    @RequestMapping(value ="config_convenience_sort", method = RequestMethod.POST)
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam Integer sortNo){
    	Response<Void> res = configConvenienceService.updateSort(id, sortNo);
    	return res;
    }
}
