package com.cqliving.cloud.controller.module.info;

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
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.cloud.online.info.service.InfoLableService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/lable")
public class InfoLableController extends CommonController {

    @Autowired
    private InfoLableService infoLableService;
    
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="info_lable_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //数据权限处理
        String searchAppid = (String)searchMap.get("EQ_appId");
        if(StringUtils.isBlank(searchAppid)){
            userDate(user, appList, searchMap);
        }
        map.put("allSourceTypes", InfoLable.allSourceTypes);
        sortMap.put("id", false);
        PageInfo<InfoLableDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", infoLableService.queryPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/info/info_lable_list_page";
        }else{
        	return "tiles.module.info.info_lable_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="info_lable_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,InfoLable lable){
        //处理app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("item", lable);
        map.put("allSourceTypes", InfoLable.allSourceTypes);
        map.put("action", "/module/lable/info_lable_add.html");
        //return "/module/info/info_lable_detail";
        return getReturnUrl(request,map,"tiles.module.info.info_lable_detail");
    }


    //增加-保存
    @RequestMapping(value ="info_lable_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,InfoLable infoLable){
        //ID
        infoLable.setId(null);
        infoLable.setAppId(null==infoLable.getAppId()?SessionFace.getSessionUser(request).getAppId():infoLable.getAppId());
        Response<Void> res = infoLableService.save(infoLable);
        if(res.getCode() < 0){
            return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="info_lable_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        InfoLableDto infoLable = infoLableService.getById(id).getData();
        if(infoLable==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", infoLable);
        map.put("allSourceTypes", InfoLable.allSourceTypes);
        map.put("action", "/module/lable/info_lable_update.html");
        //return "/module/info/info_lable_detail";
        return getReturnUrl(request,map,"tiles.module.info.info_lable_detail");
    }

    //修改-保存
    @RequestMapping(value ="info_lable_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,InfoLable infoLable){
        Response<Void> res = Response.newInstance();
        if(infoLable==null || infoLable.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            InfoLable sourceInfoLable = infoLableService.get(infoLable.getId()).getData();
            if(sourceInfoLable==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceInfoLable.setAppId(null==infoLable.getAppId()?sourceInfoLable.getAppId():infoLable.getAppId());
            //名称
            sourceInfoLable.setName(infoLable.getName());
            sourceInfoLable.setSourceType(null==infoLable.getSourceType()?sourceInfoLable.getSourceType():infoLable.getSourceType());
            res= infoLableService.save(sourceInfoLable);
            if(res.getCode() < 0){
                return new Response<Void>(-1,StringUtils.isNotBlank(res.getMessage())?res.getMessage():(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE)));
            }
            infoLable = sourceInfoLable;
        }catch (Exception ex){
            logger.error("Save Method (Update) InfoLable Error : " + infoLable.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="info_lable_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        InfoLableDto infoLable = infoLableService.getById(id).getData();
        if(infoLable==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", infoLable);
        map.put("allSourceTypes", InfoLable.allSourceTypes);
        //return "/module/info/info_lable_view";
        return getReturnUrl(request,map,"tiles.module.info.info_lable_view");
    }

    //删除
    @RequestMapping(value ="info_lable_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = infoLableService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="info_lable_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = infoLableService.delete(ids);
        return res;
    }
}
