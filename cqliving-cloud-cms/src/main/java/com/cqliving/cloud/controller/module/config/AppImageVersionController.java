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
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppImageVersion;
import com.cqliving.cloud.online.app.dto.AppImageVersionDto;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppImageVersionService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/appimgversion")
public class AppImageVersionController extends CommonController {

    @Autowired
    private AppImageVersionService appImageVersionService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="app_image_version_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        //处理APP下拉框
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
          //过滤App的处理
          userDate(user, appList, searchMap);
        }
        searchMap.put("NOTEQ_status", AppImageVersion.STATUS99);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
		
        PageInfo<AppImageVersionDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", appImageVersionService.queryPage(pageInfo, searchMap, sortMap).getData());
        map.put("allUseStatus",AppImageVersion.allUseStatus);
        map.put("allTypes",AppImageVersion.allTypes);
        //停用
        map.put("USESTATUS0",AppImageVersion.USESTATUS0);
        //启用
        map.put("USESTATUS1",AppImageVersion.USESTATUS1);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/app_image_version_list_page";
        }else{
        	return "tiles.module.cfg.app_image_version_list";
        }
    }

    //增加-查看
	@RequestMapping(value ="app_image_version_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,AppImageVersionDto imgVersion){
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        
        if(null==imgVersion){
            imgVersion = new AppImageVersionDto();
        }
        if(null==imgVersion.getAppId()){
            if(appList.size()>0){
                imgVersion.setAppId(appList.get(0).getId());
            }else{
                imgVersion.setAppId(user.getAppId());
            }
        }
        
        map.put("allTypes", AppImageVersion.allTypes);
        map.put("item", imgVersion);
        map.put("appId", user.getAppId());
        // return "/module/cfg/app_image_version_detail";
        return getReturnUrl(request,map,"tiles.module.cfg.app_image_version_detail");
    }


    //增加-保存
    @RequestMapping(value ="app_image_version_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AppImageVersion appImageVersion,String loadingUrl,String linkUrl,Integer showTime){
        //创建时间
        appImageVersion.setCreateTime(new Date());
        //更新时间
        appImageVersion.setUpdateTime(appImageVersion.getCreateTime());
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //创建人ID
            appImageVersion.setCreatorId(user.getUserId());
            //创建人名称
            appImageVersion.setCreator(user.getNickname());
            //更新人ID
            appImageVersion.setUpdatorId(user.getUserId());
            //updator
            appImageVersion.setUpdator(user.getNickname());
        }
        appImageVersion.setId(null);
        appImageVersion.setAppId(null==appImageVersion.getAppId()?user.getAppId():appImageVersion.getAppId());
        appImageVersion.setUseStatus(AppImageVersion.USESTATUS0);
        Response<Void> res = appImageVersionService.saveImageVersion(appImageVersion, loadingUrl, linkUrl,showTime);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="app_image_version_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppImageVersionDto appImageVersion = appImageVersionService.getById(id).getData();
        if(appImageVersion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appImageVersion);
        map.put("allTypes", AppImageVersion.allTypes);
        SessionUser user = SessionFace.getSessionUser(request);
        map.put("appId", user.getAppId());
        return getReturnUrl(request,map,"tiles.module.cfg.app_image_version_detail");
    }

    //修改-保存
    @RequestMapping(value ="app_image_version_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AppImageVersion appImageVersion,String loadingUrl,String linkUrl,Integer showTime){
        Response<Void> res = Response.newInstance();
        if(appImageVersion==null || appImageVersion.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppImageVersion sourceAppImageVersion = appImageVersionService.get(appImageVersion.getId()).getData();
            if(sourceAppImageVersion==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            //更新时间
            sourceAppImageVersion.setUpdateTime(new Date());
            SessionUser user = SessionFace.getSessionUser(request);
            if(null!=user){
                //更新人ID
                sourceAppImageVersion.setUpdatorId(user.getUserId());
                //updator
                sourceAppImageVersion.setUpdator(user.getNickname());
            }

            //标题
            sourceAppImageVersion.setTitle(appImageVersion.getTitle());
            //客户端类型
            sourceAppImageVersion.setType(appImageVersion.getType());
            //appId
            sourceAppImageVersion.setAppId(null==appImageVersion.getAppId()?sourceAppImageVersion.getAppId():appImageVersion.getAppId());
            res= appImageVersionService.saveImageVersion(sourceAppImageVersion, loadingUrl, linkUrl,showTime);
            appImageVersion = sourceAppImageVersion;
        }catch (Exception ex){
            logger.error("Save Method (Update) AppImageVersion Error : " + appImageVersion.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="app_image_version_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppImageVersionDto appImageVersion = appImageVersionService.getById(id).getData();
        if(appImageVersion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("allTypes", AppImageVersion.allTypes);
        map.put("item", appImageVersion);
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
//        return "/module/cfg/app_image_version_view";
        return getReturnUrl(request,map,"tiles.module.cfg.app_image_version_view");
    }

    //删除
    @RequestMapping(value ="app_image_version_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appImageVersionService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="app_image_version_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appImageVersionService.delete(ids);
        return res;
    }
    
    //启用
    @RequestMapping(value ="start_using")
    @ResponseBody
    public Response<Void> startUsing(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appImageVersionService.startUsing(id);
        return res;
    }
    
    //启用
    @RequestMapping(value ="non_use")
    @ResponseBody
    public Response<Void> nonUse(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appImageVersionService.nonUse(id);
        return res;
    }
}
