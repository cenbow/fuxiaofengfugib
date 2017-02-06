package com.cqliving.cloud.controller.module.config;

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
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppTempletService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/apptemplet")
public class AppTempletController extends CommonController {

    @Autowired
    private AppTempletService appTempletService;
    @Autowired
    private AppInfoService appInfoService;
    //列表
    @RequestMapping(value ="app_templet_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        if(StringUtils.isBlank(searchAppid)){
            //处理APP下拉框
            SessionUser user = SessionFace.getSessionUser(request);
            List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
            if(null!=appList && appList.size()>1){
                map.put("appList", appList);
            }
            userDate(user, appList, searchMap);
        }
        
        PageInfo<AppTemplet> pageInfo = getPageInfo(request);
        map.put("pageInfo", appTempletService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/app_templet_list_page";
        }else{
        	return "tiles.module.cfg.app_templet_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="app_templet_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,AppTemplet appTemplet){
        
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("allTypes", AppTemplet.allTypes);
        return "tiles.module.cfg.app_templet_detail";
    }


    //增加-保存
    @RequestMapping(value ="app_templet_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AppTemplet appTemplet){
        //ID
        appTemplet.setId(null);
        appTemplet.setAppId(null==appTemplet.getAppId()?SessionFace.getSessionUser(request).getAppId():appTemplet.getAppId());
        Response<Void> res = appTempletService.save(appTemplet);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="app_templet_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppTemplet appTemplet = appTempletService.get(id).getData();
        if(appTemplet==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("item", appTemplet);
        map.put("allTypes", AppTemplet.allTypes);
        return "tiles.module.cfg.app_templet_detail";
    }

    //修改-保存
    @RequestMapping(value ="app_templet_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AppTemplet appTemplet){
        Response<Void> res = Response.newInstance();
        if(appTemplet==null || appTemplet.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppTemplet sourceAppTemplet = appTempletService.get(appTemplet.getId()).getData();
            if(sourceAppTemplet==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceAppTemplet.setAppId(null==appTemplet.getAppId()?sourceAppTemplet.getAppId():appTemplet.getAppId());
            //模板CODE
            sourceAppTemplet.setTempletCode(appTemplet.getTempletCode());
            //模板图片
            sourceAppTemplet.setImageUrl(appTemplet.getImageUrl());
            sourceAppTemplet.setName(appTemplet.getName());
            sourceAppTemplet.setTempletDesc(appTemplet.getTempletDesc());
            sourceAppTemplet.setType(appTemplet.getType());
            res= appTempletService.save(sourceAppTemplet);
            appTemplet = sourceAppTemplet;
        }catch (Exception ex){
            logger.error("Save Method (Update) AppTemplet Error : " + appTemplet.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("修改成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="app_templet_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppTemplet appTemplet = appTempletService.get(id).getData();
        if(appTemplet==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("item", appTemplet);
        map.put("allTypes", AppTemplet.allTypes);
        return "tiles.module.cfg.app_templet_view";
    }

    //删除
    @RequestMapping(value ="app_templet_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appTempletService.deleteTemplet(new Long []{id});
        return res;
    }

	//批量删除
    @RequestMapping(value ="app_templet_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appTempletService.deleteTemplet(ids);
        return res;
    }
}
