package com.cqliving.cloud.controller.module.config;

import java.util.Date;
import java.util.LinkedHashMap;
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
import com.cqliving.cloud.online.app.domain.AppVersion;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.dto.AppVersionDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.app.service.AppVersionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;


@Controller
@RequestMapping(value = "/module/version")
public class AppVersionController extends CommonController {

    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppInfoService appInfoService;
    //列表
    @RequestMapping(value ="app_version_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage) {
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("publish_time", false);
        sortMap.put("vesion_no", false);
        
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        //查询正常的app版本信息
        searchMap.put("EQ_status", AppVersion.STATUS3);
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
            //过滤App的处理
            userDate(user, appList, searchMap);
        }
        
        PageInfo<AppVersionDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", appVersionService.queryByPage(pageInfo, searchMap, sortMap).getData());
        map.put("allTypes", AppVersion.allTypes);
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/cfg/app_version_list_page";
        }else{
        	return "tiles.module.cfg.app_version_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="app_version_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }else{
            AppVersion appVersion = new AppVersion();
            appVersion.setAppId(user.getAppId());
            map.put("item", appVersion);
        }
        
//        map.put("TYPE0", AppVersion.TYPE0);
        map.put("allTypes", AppVersion.allTypes);
        return "tiles.module.cfg.app_version_detail";
    }


    //增加-保存
    @RequestMapping(value ="app_version_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void>  postAdd(HttpServletRequest request, Map<String, Object> map,
                                AppVersion appVersion){
        //主键
        appVersion.setId(null);
        //创建时间
        appVersion.setCreateTime(new Date());
        //更新时间
        appVersion.setUpdateTime(appVersion.getCreateTime());
        appVersion.setStatus(AppVersion.STATUS3);
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //创建人ID
            appVersion.setCreatorId(user.getUserId());
            //创建人名称
            appVersion.setCreator(user.getNickname());
            //更新人ID
            appVersion.setUpdatorId(user.getUserId());
            //updator
            appVersion.setUpdator(user.getNickname());
        }
        appVersion.setAppId(null==appVersion.getAppId()?user.getAppId():appVersion.getAppId());
        Response<Void> res = appVersionService.save(appVersion);
        if(res.getCode() < 0){
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="app_version_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        AppVersion appVersion = appVersionService.get(id).getData();
        if(appVersion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        
      //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }        
        map.put("item", appVersion);
//        map.put("TYPE0", AppVersion.TYPE0);
        map.put("allTypes", AppVersion.allTypes);
        return "tiles.module.cfg.app_version_detail";
    }

    //修改-保存
    @RequestMapping(value ="app_version_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                AppVersion appVersion){
        Response<Void> res = Response.newInstance();
        if(appVersion==null || appVersion.getId()==null){
          //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppVersion sourceAppVersion = appVersionService.get(appVersion.getId()).getData();
            if(sourceAppVersion==null){
              //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            appVersion.setCreateTime(sourceAppVersion.getCreateTime());
            appVersion.setCreator(sourceAppVersion.getCreator());
            appVersion.setCreatorId(sourceAppVersion.getCreatorId());
            appVersion.setUpdateTime(new Date());
            appVersion.setStatus(sourceAppVersion.getStatus());
            SessionUser user = SessionFace.getSessionUser(request);
//            appVersion.setVesionNo(sourceAppVersion.getVesionNo());
            if(null!=user){
                //更新人ID
                appVersion.setUpdatorId(user.getUserId());
                //updator
                appVersion.setUpdator(user.getNickname());
            }
            appVersion.setAppId(null==appVersion.getAppId()?user.getAppId():appVersion.getAppId());
            appVersionService.update(appVersion);
        }catch (Exception ex){
            logger.error("Save Method (Update) AppVersion Error : " + appVersion.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("修改成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="app_version_view")
    public String show(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value = "id") Long id){
        AppVersionDto appVersion = appVersionService.getById(id).getData();
        if(appVersion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appVersion);
        map.put("allTypes", AppVersion.allTypes);
        return "tiles.module.cfg.app_version_view";
    }
    
    //删除
    @RequestMapping(value ="app_version_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appVersionService.deleteLogic(id);
        return res;
    }

    //批量删除
    @RequestMapping(value ="delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appVersionService.deleteLogic(ids);
        return res;
    }
}