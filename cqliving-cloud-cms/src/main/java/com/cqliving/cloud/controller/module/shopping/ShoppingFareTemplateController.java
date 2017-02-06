package com.cqliving.cloud.controller.module.shopping;

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

import com.cqliving.basic.dto.OptionDto;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shopfare")
public class ShoppingFareTemplateController extends CommonController {

    @Autowired
    private ShoppingFareTemplateService shoppingFareTemplateService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private BasicService basicService;
    
    public void init(HttpServletRequest request){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	request.setAttribute("allApps", appInfoService.getBySysUser(sessionUser.getUsertype(),sessionUser.getUserId()).getData());
    	request.setAttribute("allStatuss", ShoppingFareTemplate.allStatuss);
    	request.setAttribute("allDetailStatuss", ShoppingFareTemplateDetail.allStatuss);
    	request.setAttribute("allIsDefaults",ShoppingFareTemplateDetail.allIsDefaults);
    }
    
    //列表
    @RequestMapping(value ="shopfare_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	//初始化数据
    	this.init(request);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        PageInfo<ShoppingFareTemplate> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ShoppingFareTemplate.STATUS99);//排除逻辑删除状态
        if(null == searchMap.get("EQ_appId")){
        	List<AppInfoDto> allApps = (List<AppInfoDto>) request.getAttribute("allApps");
        	searchMap.put("EQ_appId",allApps.get(0).getId());
        }
        request.setAttribute("defaultAppId",searchMap.get("EQ_appId"));
        map.put("pageInfo", shoppingFareTemplateService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shopfare/shopfare_list_page";
        }else{
        	return "tiles.module.shopfare.shopfare_list";
        }
    }

    //增加-查看
    @RequestMapping(value ={"shopfare_add","shopfare_copy"}, method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long id){
	    	//初始化数据
	    	this.init(request);
	    	ShoppingFareTemplate template = null;
	    	if(null != id){
	    		template = shoppingFareTemplateService.get(id).getData();
	    		if(this.isCopy(request))template.setId(null);
	    		request.setAttribute("item",template);
	    		request.setAttribute("defaultAppId",template.getAppId());
	    	}else{
	    		List<AppInfoDto> allApps = (List<AppInfoDto>) request.getAttribute("allApps");
	    		request.setAttribute("defaultAppId",allApps.get(0).getId());
	    	}
	    	request.setAttribute("itemJson",JsonMapper.nonEmptyMapper().toJson(template));
	    	//区域
	    	List<OptionDto> optionDtos = basicService.findAreaRegion();
	    	request.setAttribute("optionDtosJson",JsonMapper.nonEmptyMapper().toJson(optionDtos));
	    	return getReturnUrl(request,map,"tiles.module.shopfare.shopfare_detail");
    }

    private boolean isCopy(HttpServletRequest request){
	    	String uri = request.getRequestURI();
	    	if(uri.contains("shopfare_copy"))return true;
	    	return false;
    }
    
    //增加-保存
    @RequestMapping(value ={"shopfare_add","shopfare_copy"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,String fareTemplate){
    	
	    	JsonMapper jm = new JsonMapper();
	    	ShoppingFareTemplate shoppingFareTemplate = jm.fromJson(fareTemplate, ShoppingFareTemplate.class);
	    	
	    	SessionUser sessionUser = SessionFace.getSessionUser(request);
	    	Date now = Dates.now();
	    	if(null == shoppingFareTemplate.getId()){
	    		shoppingFareTemplate.setCreateTime(now);
	    		shoppingFareTemplate.setCreator(sessionUser.getNickname());
	    		shoppingFareTemplate.setCreatorId(sessionUser.getUserId());
	    		shoppingFareTemplate.setStatus(ShoppingFareTemplate.STATUS1);
	    	}
	    	shoppingFareTemplate.setUpdateTime(now);
	    	shoppingFareTemplate.setUpdator(sessionUser.getNickname());
	    	shoppingFareTemplate.setUpdatorId(sessionUser.getUserId());
    	
        Response<Void> res = shoppingFareTemplateService.saveOrUpdate(shoppingFareTemplate);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //删除
    @RequestMapping(value ="shopfare_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shoppingFareTemplateService.deleteLogic(id);
        return res;
    }
    
    //发布
    @RequestMapping(value ="shopfare_online", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shoppingFareTemplateService.updateStatus(ShoppingFareTemplate.STATUS3,id);
        return res;
    }
    
    //下线
    @RequestMapping(value ="shopfare_offline", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shoppingFareTemplateService.updateStatus(ShoppingFareTemplate.STATUS88,id);
        return res;
    }
}
