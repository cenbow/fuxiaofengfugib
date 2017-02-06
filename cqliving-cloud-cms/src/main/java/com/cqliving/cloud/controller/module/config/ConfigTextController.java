package com.cqliving.cloud.controller.module.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.service.ConfigTextService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/config_text")
public class ConfigTextController extends CommonController {

    @Autowired
    private ConfigTextService configTextService;
    @Autowired
    private AppInfoService appInfoService;
    //列表
    @RequestMapping(value ="config_text_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_publishTime", required=false) Date search_GTE_publishTime
        ,@RequestParam(value="search_LT_publishTime", required=false) Date search_LT_publishTime
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
        ,@RequestParam(value="search_GTE_updateTime", required=false) Date search_GTE_updateTime
        ,@RequestParam(value="search_LT_updateTime", required=false) Date search_LT_updateTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        //默认时间范围3个月
    	search_LT_publishTime = search_LT_publishTime != null ?search_LT_publishTime: Calendar.getInstance().getTime();
        map.put("search_LT_publishTime", search_LT_publishTime);
        searchMap.put("LT_publishTime", DateUtils.truncate(DateUtils.addDays(search_LT_publishTime, 1), Calendar.DATE));
        search_GTE_publishTime = search_GTE_publishTime != null ? search_GTE_publishTime:DateUtils.addMonths(search_LT_publishTime, -3);
        map.put("search_GTE_publishTime", search_GTE_publishTime);
        searchMap.put("GTE_publishTime", search_GTE_publishTime);
        //默认时间范围3个月
    	search_LT_createTime = search_LT_createTime != null ?search_LT_createTime: Calendar.getInstance().getTime();
        map.put("search_LT_createTime", search_LT_createTime);
        searchMap.put("LT_createTime", DateUtils.truncate(DateUtils.addDays(search_LT_createTime, 1), Calendar.DATE));
        search_GTE_createTime = search_GTE_createTime != null ? search_GTE_createTime:DateUtils.addMonths(search_LT_createTime, -3);
        map.put("search_GTE_createTime", search_GTE_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        //默认时间范围3个月
    	search_LT_updateTime = search_LT_updateTime != null ?search_LT_updateTime: Calendar.getInstance().getTime();
        map.put("search_LT_updateTime", search_LT_updateTime);
        searchMap.put("LT_updateTime", DateUtils.truncate(DateUtils.addDays(search_LT_updateTime, 1), Calendar.DATE));
        search_GTE_updateTime = search_GTE_updateTime != null ? search_GTE_updateTime:DateUtils.addMonths(search_LT_updateTime, -3);
        map.put("search_GTE_updateTime", search_GTE_updateTime);
        searchMap.put("GTE_updateTime", search_GTE_updateTime);
		
        PageInfo<ConfigText> pageInfo = getPageInfo(request);
        map.put("pageInfo", configTextService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", ConfigText.allTypes);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config_text/config_text_list_page";
        }else{
        	return "tiles.module.config.text.config_text_list";
        }
    }
    
    private void initData(HttpServletRequest request,Long appId){
    	
    	SessionUser sessionUser= SessionFace.getSessionUser(request);
    	Long userAppId = sessionUser.getAppId();
    	if(null == userAppId){
    		List<AppInfo> appInfo = appInfoService.getAll().getData();
    		request.setAttribute("appInfos",appInfo);
    		if(null == appId)appId = appInfo.get(0).getId();
    	}else{
    		List<AppInfo> appInfo = Lists.newArrayList();
    		if(null == appId)appId = userAppId;
    		appInfo.add(appInfoService.get(userAppId).getData());
    		request.setAttribute("appInfos",appInfo);
    	}
    	request.setAttribute("defaultAppId",appId);
    	request.setAttribute("item",configTextService.getByAppId(appId, ConfigText.TYPE1).getData());
    	request.setAttribute("dateNow",Dates.now());
    	
    }
    
    //增加-查看
    @RequestMapping(value ="config_text_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long appId){
    	
    	initData(request,appId);
    	
    	return getReturnUrl(request,map,"tiles.module.config.config_text_detail");
    }

    //保存
    @RequestMapping(value ="config_text_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigText configText){
        Response<Void> res = Response.newInstance();
        try{
        	
        	SessionUser sessionUser = SessionFace.getSessionUser(request);
        	Date now = Dates.now();
        	Long userId = sessionUser.getUserId();
        	String nickName = sessionUser.getNickname();
        	ConfigText sourceConfigText = null;
        	if(null != configText.getId()){
        		sourceConfigText = configTextService.get(configText.getId()).getData();
        	}else{
        		sourceConfigText = new ConfigText();
        		sourceConfigText.setCreateTime(now);
        		sourceConfigText.setCreator(nickName);
        		sourceConfigText.setCreatorId(userId);
        		sourceConfigText.setUpdateTime(now);
        		sourceConfigText.setUpdator(nickName);
        		sourceConfigText.setUpdatorId(userId);
        		sourceConfigText.setType(ConfigText.TYPE1);
        	}
            if(sourceConfigText==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            //客户端_ID
            sourceConfigText.setAppId(configText.getAppId());
            //标题
            sourceConfigText.setTitle(configText.getTitle());
            //发布时间
            sourceConfigText.setPublishTime(configText.getPublishTime());
            //内容
            sourceConfigText.setContent(configText.getContent());
            res= configTextService.save(sourceConfigText);
            configText = sourceConfigText;
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigText Error : " + configText.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="config_text_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigText configText = configTextService.get(id).getData();
        if(configText==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configText);
        return getReturnUrl(request,map,"tiles.module.config.text.config_text_view");
    }

    //删除
    @RequestMapping(value ="config_text_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = configTextService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="config_text_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = configTextService.delete(ids);
        return res;
    }
}
